package com.claudiu.proto.client;

import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.domain.Rol;
import protocol.Protocol;

import java.util.List;

public class ProtoUtils {
    public static protocol.Protocol.Request createLoginRequest(String username, String password, Rol rol){
        protocol.Protocol.Request request= protocol.Protocol.Request.newBuilder().setType(Protocol.Request.Type.Login)
                .setUsername(username)
                .setPassword(password)
                .setRol(rol.toString()).build();
        return request;

    }
    public static protocol.Protocol.Request createLogoutRequest(String id){
        protocol.Protocol.Request request= protocol.Protocol.Request.newBuilder().setType(Protocol.Request.Type.Logout)
                .setId(id).build();
        return request;

    }

    public static Protocol.Request createAdaugareMedicamentRequest(String nume,float pret,String detalii){
        Protocol.Request request = Protocol.Request.newBuilder()
                .setType(Protocol.Request.Type.AdaugareMedicament)
                .setNumeMedicament(nume)
                .setPretMedicament(pret)
                .setDetaliiMedicament(detalii).build();
        return request;
    }

    public static Protocol.Request createStergereMedicamentRequest(int id){
        Protocol.Request request = Protocol.Request.newBuilder()
                .setType(Protocol.Request.Type.StergereMedicament)
                .setIdMedicament(id).build();
        return request;
    }

    public static Protocol.Request createModificareMedicamentRequest(int id,String nume,float pret,String detalii){
        Protocol.Request request = Protocol.Request.newBuilder()
                .setType(Protocol.Request.Type.ModificareMedicament)
                .setIdMedicament(id)
                .setNumeMedicament(nume)
                .setPretMedicament(pret)
                .setDetaliiMedicament(detalii).build();
        return request;
    }

    public static Protocol.Request createAfisareMedicamenteRequest (){
        Protocol.Request request= Protocol.Request.newBuilder()
                .setType(Protocol.Request.Type.AfisareMedicamente).build();
        return request;
    }


    public static Protocol.Response createOkResponse (){
        Protocol.Response response= Protocol.Response.newBuilder()
                .setType(Protocol.Response.Type.Ok).build();
        return response;
    }
    public static Protocol.Response createOkLoginResponse (String id){
        Protocol.Response response= Protocol.Response.newBuilder()
                .setType(Protocol.Response.Type.Ok)
                .setUsername(id).build();
        return response;
    }

    public static Protocol.Response createErrorResponse(String text){
        Protocol.Response response=Protocol.Response.newBuilder()
                .setType(Protocol.Response.Type.Error)
                .setError(text).build();
        return response;
    }

    public static Protocol.Response createListaMedicamenteResponse(List<Medicament> medicamente){
        Protocol.Response.Builder response= Protocol.Response.newBuilder()
                .setType(Protocol.Response.Type.ListaMedicamente);
        for(Medicament med : medicamente){
            Protocol.Medicament medDTO = Protocol.Medicament.newBuilder().setId(med.getId()).setNume(med.getNume()).setPret(med.getPret()).setDetalii(med.getDetalii()).build();
            response.addMedicamente(medDTO);
        }
        return response.build();
    }

    public static String getError(Protocol.Response response){
        String errorMessage=response.getError();
        return errorMessage;
    }
    public  static  String getId(Protocol.Request request){
        return request.getId();
    }
    public  static  String getUsername(Protocol.Request request){
        return request.getUsername();
    }
    public  static  String getPassword(Protocol.Request request){
        return request.getPassword();
    }
    public  static  Rol getRol(Protocol.Request request){
        return Rol.valueOf(request.getRol());
    }


    public static int getIdMedicament(Protocol.Request request){
        return request.getIdMedicament();
    }
    public static String getNumeMedicament(Protocol.Request request){
        return request.getNumeMedicament();
    }
    public static float getPret(Protocol.Request request){
        return request.getPretMedicament();
    }
    public static String getDetalii(Protocol.Request request){
        return request.getDetaliiMedicament();
    }


    public static String getUser(Protocol.Response response){
        return response.getUsername();
    }

    public static Medicament[] getMedicamente(Protocol.Response response){
        Medicament[] medicamente = new Medicament[response.getMedicamenteCount()];
        for(int i=0;i<response.getMedicamenteCount();i++){
            Protocol.Medicament med = response.getMedicamente(i);
            Medicament medDTO = new Medicament(med.getId(),med.getNume(),med.getPret(),med.getDetalii());
            medicamente[i] = medDTO;
        }
        return medicamente;
    }
}