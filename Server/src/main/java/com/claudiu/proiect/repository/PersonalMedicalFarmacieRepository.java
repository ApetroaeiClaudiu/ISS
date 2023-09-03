package com.claudiu.proiect.repository;

import com.claudiu.proiect.domain.PersonalMedicalFarmacie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalMedicalFarmacieRepository extends JpaRepository<PersonalMedicalFarmacie, Integer> {
}
