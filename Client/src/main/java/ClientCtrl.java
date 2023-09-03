import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.domain.Rol;
import com.claudiu.proto.client.IClient;
import com.claudiu.proto.client.IServer;

import java.util.List;

public class ClientCtrl implements IClient {
    private IServer server;
    private String id;
    private ListaComenzi comenzi;
    private ListaSectii listaFarmacii;

    public ClientCtrl(IServer server) {
        this.server = server;
        this.id = null;
        comenzi = new ListaComenzi();
        listaFarmacii = new ListaSectii();
    }


    public void logout() {
        try {
            server.logout(id, this);
            this.id = null;
        } catch (Exception e) {
            System.out.println("Eroare la deconectare "+e);
        }
    }

    //cand se logheaza un user, daca userul e sectie,
    //cautam toate farmaciile logate si le adaugam aici

    public void login(String username, String pass, Rol rol) throws Exception {
        String idLogin = server.login(username,pass,rol,this);
        String[] farmacii = server.getFarmacii();
        if(idLogin.contains("sectie")){
            for(String f :farmacii){
                listaFarmacii.sectieLoggedIn(f);
            }
        }
        this.id = idLogin;
    }

    public void adaugaMedicament(String nume,float pret,String detalii) throws Exception {
        server.adaugareMedicament(nume,pret,detalii);
    }
    public void stergeMedicament(int id) throws Exception {
        server.stergereMedicament(id);
    }
    public void modificaMedicament(int id,String nume,float pret,String detalii) throws Exception {
        server.modificareMedicament(id,nume,pret,detalii);
    }

    public List<Medicament> getMedicamente() throws Exception {
        return server.getMedicamente();
    }

    public void trimiteComanda(int idmed, int cantitate) throws Exception {
        messagesModel.newMessage(user.getId(), txtMsg);
        User sender=new User(user.getId());
        User receiver=new User((String)friendsModel.getElementAt(indexFriend));
        Message message=new Message(sender,txtMsg,receiver);
        server.sendMessage(message);
        listaFarmacii.

        server.trimiteComanda(this.id,idmed,cantitate);
    }

    public void comandaNoua(Comanda comanda){
        comenzi.comandaNoua(comanda.getIdClient(),comanda.getIdMed(),comanda.getCantitate());
    }
    public void sectieLoggedIn(String id) throws Exception {
        sectii.sectieLoggedIn(id);
    }
    public void sectieLoggedOut(String id) throws Exception {
        sectii.sectieLoggedOut(id);
    }
}
