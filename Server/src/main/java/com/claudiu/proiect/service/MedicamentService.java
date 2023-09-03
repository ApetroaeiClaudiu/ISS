package com.claudiu.proiect.service;

import com.claudiu.proiect.domain.Medicament;

import java.util.List;

public interface MedicamentService {

    public void save(Medicament med) ;

    public void delete(Medicament med) ;

    public void deleteById(int id);

    public Medicament findById(int id);

    public List<Medicament> findAll() ;
}
