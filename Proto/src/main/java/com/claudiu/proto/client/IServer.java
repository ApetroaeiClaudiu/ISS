package com.claudiu.proto.client;

import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.domain.Rol;

import java.util.List;

public interface IServer {
    String login(String username, String password, Rol rol,IClient client) throws Exception;
    void logout(String id,IClient client) throws Exception;
    public void adaugareMedicament(String nume,float pret,String detalii) throws Exception;
    public void stergereMedicament(int id) throws Exception;
    public void modificareMedicament(int id,String nume,float pret,String detalii) throws Exception;
    public List<Medicament> getMedicamente()throws Exception;
    public void trimiteComanda(String id, int idmed, int cantitate) throws Exception;
}
