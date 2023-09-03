package com.claudiu.proiect.service;

import com.claudiu.proiect.domain.PersonalMedicalFarmacie;
import com.claudiu.proiect.domain.PersonalMedicalSectie;

import java.util.List;

public interface PersonalMedicalSectieService {
    public void save(PersonalMedicalSectie personal) ;

    public void delete(PersonalMedicalSectie personal) ;

    public PersonalMedicalSectie findById(int id);

    public List<PersonalMedicalSectie> findAll() ;
    public PersonalMedicalSectie findByUsernameAndPassword(String username,String password);
}
