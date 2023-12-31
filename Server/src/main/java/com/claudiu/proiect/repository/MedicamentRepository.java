package com.claudiu.proiect.repository;


import com.claudiu.proiect.domain.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {
}
