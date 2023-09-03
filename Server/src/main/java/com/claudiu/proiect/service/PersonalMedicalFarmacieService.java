package com.claudiu.proiect.service;

import com.claudiu.proiect.domain.Admin;
import com.claudiu.proiect.domain.PersonalMedicalFarmacie;

import java.util.List;

public interface PersonalMedicalFarmacieService {
    public void save(PersonalMedicalFarmacie personal) ;

    public void delete(PersonalMedicalFarmacie personal) ;

    public PersonalMedicalFarmacie findById(int id);

    public List<PersonalMedicalFarmacie> findAll() ;

    public PersonalMedicalFarmacie findByUsernameAndPassword(String username,String password);
}
