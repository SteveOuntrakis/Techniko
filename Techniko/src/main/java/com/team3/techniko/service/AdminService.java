package com.team3.techniko.service;

import com.team3.techniko.model.Admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AdminService {

    private EntityManagerFactory entityManagerFactory;

    public AdminService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
    }

    public void saveAdmin(Admin admin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Admin getAdminById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Admin admin = entityManager.find(Admin.class, id);
        entityManager.close();
        return admin;
    }

    public List<Admin> getAllAdmins() {
       return null;
    }

    public void updateAdmin(Admin admin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(admin);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteAdmin(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Admin admin = entityManager.find(Admin.class, id);
        if (admin != null) {
            entityManager.remove(admin);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}