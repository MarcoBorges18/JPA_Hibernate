package com.example.marco.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("lojinha"); //Cria uma Factory que puxa a lojinha do persistence

    public static EntityManager getEntityManeger(){
        return FACTORY.createEntityManager(); //Cria um EntityManeger com a factory criada anteriormente
    }

}
