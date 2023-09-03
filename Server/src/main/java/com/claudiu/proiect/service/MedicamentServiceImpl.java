package com.claudiu.proiect.service;

import com.claudiu.proiect.domain.Medicament;
import com.claudiu.proiect.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentServiceImpl implements MedicamentService{

    private MedicamentRepository repo;

    @Autowired
    public MedicamentServiceImpl(MedicamentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Medicament med) {
        repo.save(med);
    }

    @Override
    public void delete(Medicament med) {
        repo.delete(med);
    }

    @Override
    public void deleteById(int id) {
        repo.delete(findById(id));
    }

    @Override
    public Medicament findById(int id) {
        return repo.findById(id).orElse(null);
    }
    @Override
    public List<Medicament> findAll() {
        return repo.findAll();
    }

}
