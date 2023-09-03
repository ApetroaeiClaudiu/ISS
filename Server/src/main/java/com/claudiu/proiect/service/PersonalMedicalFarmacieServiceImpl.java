package com.claudiu.proiect.service;


import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.domain.PersonalMedicalFarmacie;
import com.claudiu.proiect.repository.MedicamentRepository;
import com.claudiu.proiect.repository.PersonalMedicalFarmacieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalMedicalFarmacieServiceImpl implements  PersonalMedicalFarmacieService{
    private PersonalMedicalFarmacieRepository repo;

    @Autowired
    public PersonalMedicalFarmacieServiceImpl(PersonalMedicalFarmacieRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(PersonalMedicalFarmacie personal) {
        repo.save(personal);
    }

    @Override
    public void delete(PersonalMedicalFarmacie personal) {
        repo.delete(personal);
    }
    @Override
    public PersonalMedicalFarmacie findById(int id) {
        return repo.findById(id).orElse(null);
    }
    @Override
    public List<PersonalMedicalFarmacie> findAll() {
        return repo.findAll();
    }

    @Override
    public PersonalMedicalFarmacie findByUsernameAndPassword(String username, String password) {
        for(PersonalMedicalFarmacie personalFarmacie : findAll()) {
            if (personalFarmacie.getUsername().equals(username) && personalFarmacie.getPassword().equals(password)) {
                return personalFarmacie;
            }
        }
        return null;
    }
}
