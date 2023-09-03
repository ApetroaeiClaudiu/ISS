package com.claudiu.proiect.protoStuff;

import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.domain.Rol;
import com.claudiu.proto.client.IClient;
import com.claudiu.proto.client.IServer;
import com.claudiu.proto.client.ProtoUtils;
import protocol.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class ProtoWorker implements Runnable, IClient {
    private IServer server;
    private Socket connection;

    private InputStream input;
    private OutputStream output;
    private volatile boolean connected;

    public ProtoWorker(IServer server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try{
            output=connection.getOutputStream() ;
            input=connection.getInputStream();
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected){
            try {
                System.out.println("Waiting requests ...");
                protocol.Protocol.Request request = protocol.Protocol.Request.parseDelimitedFrom(input);
                System.out.println("Request received: "+request);
                protocol.Protocol.Response response=handleRequest(request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }


    private protocol.Protocol.Response handleRequest(protocol.Protocol.Request request){
        protocol.Protocol.Response response=null;
        switch (request.getType()){
            case TrimiteComanda:{
                String id = ProtoUtils.getId(request);
                int idmed = ProtoUtils.getIdMed(request);
                int cantitate = ProtoUtils.getCantitate(request);
                try{
                    server.trimiteComanda(id,idmed,cantitate);
                    return ProtoUtils.createOkResponse();
                }catch (Exception e){
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case Login:{
                String username = ProtoUtils.getUsername(request);
                String password = ProtoUtils.getPassword(request);
                Rol rol = ProtoUtils.getRol(request);
                try {
                    String user = server.login(username,password,rol, this);
                    return ProtoUtils.createOkLoginResponse(user);
                } catch (Exception e) {
                    connected=false;
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case Logout:{
                String id = ProtoUtils.getId(request);
                try {
                    server.logout(id, this);
                    connected=false;
                    return ProtoUtils.createOkResponse();

                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case AdaugareMedicament:{
                String nume = ProtoUtils.getNumeMedicament(request);
                float pret = ProtoUtils.getPret(request);
                String detalii = ProtoUtils.getDetalii(request);
                try {
                    server.adaugareMedicament(nume,pret,detalii);
                    return ProtoUtils.createOkResponse();
                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case StergereMedicament:{
                int id = ProtoUtils.getIdMedicament(request);
                try{
                    server.stergereMedicament(id);
                    return ProtoUtils.createOkResponse();
                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case ModificareMedicament:{
                int id = ProtoUtils.getIdMedicament(request);
                String nume = ProtoUtils.getNumeMedicament(request);
                float pret = ProtoUtils.getPret(request);
                String detalii = ProtoUtils.getDetalii(request);
                try {
                    server.modificareMedicament(id,nume,pret,detalii);
                    return ProtoUtils.createOkResponse();
                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
            case AfisareMedicamente:{
                try {
                    List<Medicament> medicamente = server.getMedicamente();
                    return ProtoUtils.createListaMedicamenteResponse(medicamente);
                } catch (Exception e) {
                    return ProtoUtils.createErrorResponse(e.getMessage());
                }
            }
        }
        return response;
    }

    private void sendResponse(Protocol.Response response) throws IOException{
        System.out.println("sending response "+response);
        response.writeDelimitedTo(output);
        output.flush();
    }

}
