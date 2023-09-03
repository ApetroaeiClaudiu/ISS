package com.claudiu.proiect.service;


import com.claudiu.proiect.domain.PersonalMedicalFarmacie;
import com.claudiu.proiect.domain.PersonalMedicalSectie;
import com.claudiu.proiect.repository.PersonalMedicalFarmacieRepository;
import com.claudiu.proiect.repository.PersonalMedicalSectieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalMedicalSectieServiceImpl implements  PersonalMedicalSectieService{
    private PersonalMedicalSectieRepository repo;

    @Autowired
    public PersonalMedicalSectieServiceImpl(PersonalMedicalSectieRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(PersonalMedicalSectie personal) {
        repo.save(personal);
    }

    @Override
    public void delete(PersonalMedicalSectie personal) {
        repo.delete(personal);
    }
    @Override
    public PersonalMedicalSectie findById(int id) {
        return repo.findById(id).orElse(null);
    }
    @Override
    public List<PersonalMedicalSectie> findAll() {
        return repo.findAll();
    }

    @Override
    public PersonalMedicalSectie findByUsernameAndPassword(String username, String password) {
        for(PersonalMedicalSectie personalSectie : findAll()) {
            if (personalSectie.getUsername().equals(username) && personalSectie.getPassword().equals(password)) {
                return personalSectie;
            }
        }
        return null;
    }
}
