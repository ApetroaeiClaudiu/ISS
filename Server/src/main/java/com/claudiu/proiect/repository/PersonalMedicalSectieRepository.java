package com.claudiu.proiect.repository;

import com.claudiu.proiect.domain.PersonalMedicalSectie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalMedicalSectieRepository extends JpaRepository<PersonalMedicalSectie, Integer> {
}
