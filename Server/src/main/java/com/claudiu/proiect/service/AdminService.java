package com.claudiu.proiect.service;

import com.claudiu.proiect.domain.Admin;
import com.claudiu.proiect.domain.Medicament;

import java.util.List;

public interface AdminService {

    public void save(Admin admin) ;

    public void delete(Admin admin) ;

    public Admin findById(int id);

    public Admin findByUsernameAndPassword(String username,String password);

    public List<Admin> findAll() ;
}
