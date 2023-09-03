package com.claudiu.proto.client;

import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.domain.Rol;
import protocol.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProtoProxy implements IServer {
    private String host;
    private int port;

    private IClient client;

    private InputStream input;
    private OutputStream output;
    private Socket connection;
    private BlockingQueue<protocol.Protocol.Response> qresponses;
    private volatile boolean finished;

    public ProtoProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Protocol.Response>();
    }



    @Override
    public String login(String username, String password, Rol rol, IClient client) throws Exception {
        initializeConnection();
        sendRequest(ProtoUtils.createLoginRequest(username,password,rol));
        Protocol.Response response=readResponse();
        if (response.getType()==Protocol.Response.Type.Ok){
            this.client=client;
            return ProtoUtils.getUser(response);
        }
        if (response.getType()==Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            closeConnection();
            throw new Exception(errorText);
        }
        return null;
    }

    @Override
    public void logout(String id, IClient client) throws Exception {
        sendRequest(ProtoUtils.createLogoutRequest(id));
        Protocol.Response response=readResponse();
        closeConnection();
        if (response.getType()==Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            throw new Exception(errorText);
        }
    }
    @Override
    public void adaugareMedicament(String nume,float pret,String detalii) throws Exception {
        sendRequest(ProtoUtils.createAdaugareMedicamentRequest(nume,pret,detalii));
        Protocol.Response response=readResponse();
        if (response.getType()==Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            throw new Exception(errorText);
        }
    }
    @Override
    public void stergereMedicament(int id) throws Exception {
        sendRequest(ProtoUtils.createStergereMedicamentRequest(id));
        Protocol.Response response=readResponse();
        if (response.getType()==Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            throw new Exception(errorText);
        }
    }
    @Override
    public void modificareMedicament(int id,String nume,float pret,String detalii) throws Exception {
        sendRequest(ProtoUtils.createModificareMedicamentRequest(id,nume,pret,detalii));
        Protocol.Response response=readResponse();
        if (response.getType()==Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            throw new Exception(errorText);
        }
    }
    @Override
    public List<Medicament> getMedicamente()throws Exception{
        sendRequest(ProtoUtils.createAfisareMedicamenteRequest());
        Protocol.Response response=readResponse();
        if (response.getType()==Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            throw new Exception(errorText);
        }
        Medicament[] medicamente = ProtoUtils.getMedicamente(response);
        List<Medicament> meds = new ArrayList<>();
        for(Medicament medicament : medicamente){
            meds.add(medicament);
        }
        return meds;
    }

    @Override
    public void trimiteComanda(String id, int idmed, int cantitate) throws Exception {
        sendRequest(ProtoUtils.createTrimiteComandaRequest());
        Protocol.Response response = readResponse();
        if(response.getType() == Protocol.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            throw new Exception(errorText);
        }
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(Protocol.Request request)throws Exception{
        try {
            System.out.println("Sending request ..."+request);
            request.writeDelimitedTo(output);
            output.flush();
            System.out.println("Request sent.");
        } catch (IOException e) {
            throw new Exception("Error sending object "+e);
        }

    }

    private Protocol.Response readResponse() throws Exception{
        Protocol.Response response=null;
        try{
            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    private void initializeConnection() throws Exception{
        try {
            connection=new Socket(host,port);
            output=connection.getOutputStream();
            input=connection.getInputStream();
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }
    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Protocol.Response response=Protocol.Response.parseDelimitedFrom(input);
                    System.out.println("response received "+response);
                    try {
                        qresponses.put(response);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }

//    private void handleUpdate(Protocol.Response updateResponse){
//        switch (updateResponse.getType()){
//            case Refresh:{
//                Game game = ProtoUtils.getGame(updateResponse);
//                try {
//                    client.refresh(game);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
//
//    }
//
//    private boolean isUpdateResponse(Protocol.Response.Type type){
//        switch (type){
//            case Refresh:  return true;
//        }
//        return false;
//    }
}
