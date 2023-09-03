package com.claudiu.proiect.service;


import com.claudiu.proiect.domain.Admin;
import com.claudiu.proiect.repository.AdminRepository;
import com.claudiu.proiect.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository repo;

    @Autowired
    public AdminServiceImpl(AdminRepository repo) {
        this.repo = repo;
    }
    @Override
    public void save(Admin admin) {
        repo.save(admin);
    }

    @Override
    public void delete(Admin admin) {
        repo.delete(admin);
    }

    @Override
    public Admin findById(int id) {
            return repo.findById(id).orElse(null);
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        for(Admin admin : findAll()) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
         return null;
    }

    @Override
    public List<Admin> findAll() {
        return repo.findAll();
    }
}
