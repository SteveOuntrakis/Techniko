package com.team3.techniko;

public class Techniko {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Technikon");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //entityManager.persist(employee);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }}
