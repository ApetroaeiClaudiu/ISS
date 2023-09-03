package com.claudiu.proiect;


import com.claudiu.proiect.domain.*;
import com.claudiu.proto.client.IClient;
import com.claudiu.proto.client.IServer;
import com.claudiu.proiect.service.AdminService;
import com.claudiu.proiect.service.MedicamentService;
import com.claudiu.proiect.service.PersonalMedicalFarmacieService;
import com.claudiu.proiect.service.PersonalMedicalSectieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServerImpl implements IServer {
    private MedicamentService medicamentService;
    private AdminService adminService;
    private PersonalMedicalFarmacieService personalMedicalFarmacieService;
    private PersonalMedicalSectieService personalMedicalSectieService;
    private Map<String, IClient> loggedClients;

    @Autowired
    public ServerImpl(MedicamentService medicamentService, AdminService adminService, PersonalMedicalFarmacieService personalMedicalFarmacieService, PersonalMedicalSectieService personalMedicalSectieService) {
        this.medicamentService = medicamentService;
        this.adminService = adminService;
        this.personalMedicalFarmacieService = personalMedicalFarmacieService;
        this.personalMedicalSectieService = personalMedicalSectieService;
        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public String login(String username, String password, Rol rol,IClient client) throws Exception {
        if (rol == Rol.admin) {
            Admin user = adminService.findByUsernameAndPassword(username,password);
            if(user!=null){
                String id = user.getId() + user.getUsername() + user.getPassword();
                if (loggedClients.get(id) != null)
                    throw new Exception("Utilizator deja logat !");
                loggedClients.put(id, client);
                return id;
            }
            else{
                throw new Exception("Informatii gresite !");
            }
        }
        if (rol == Rol.personalFarmacie) {
            PersonalMedicalFarmacie user= personalMedicalFarmacieService.findByUsernameAndPassword(username,password);
            if(user!=null){
                String id = user.getId() + user.getUsername() + user.getPassword();
                if (loggedClients.get(id) != null)
                    throw new Exception("Utilizator deja logat !");
                loggedClients.put(id, client);
                return  id;
            }
            else {
                throw new Exception("Informatii gresite !");
            }
        }
        if (rol == Rol.personalSectie) {
            PersonalMedicalSectie user = personalMedicalSectieService.findByUsernameAndPassword(username, password);
            if(user!=null){
                String id = user.getId() + user.getUsername() + user.getPassword();
                if (loggedClients.get(id) != null)
                    throw new Exception("User already logged in.");
                loggedClients.put(id, client);
                return  id;
            }
            else{
                throw new Exception("Informatii gresite !");
            }
        }
        throw new Exception("Informatii incorecte !");
    }

    @Override
    public void logout(String id,IClient client) throws Exception {
        IClient localClient = loggedClients.remove(id);
        if(localClient == null){
            throw new Exception("Utilizatorul nu e logat !");
        }
    }

    @Override
    public void adaugareMedicament(String nume, float pret, String detalii) throws Exception {
        medicamentService.save(new Medicament(nume,pret,detalii));
    }

    @Override
    public void stergereMedicament(int id) throws Exception {
        medicamentService.deleteById(id);
    }

    @Override
    public void modificareMedicament(int id, String nume, float pret, String detalii) throws Exception {
        medicamentService.save(new Medicament(id,nume,pret,detalii));
    }

    @Override
    public List<Medicament> getMedicamente() throws Exception {
        return medicamentService.findAll();
    }

    @Override
    public void trimiteComanda(String id, int idmed, int cantitate) throws Exception {
        for(String key : loggedClients.keySet()){
            if(key.contains("sectie")){

            }
        }
    }
}
