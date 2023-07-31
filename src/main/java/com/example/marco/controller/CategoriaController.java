package com.example.marco.controller;

import javax.persistence.EntityManager;

import com.example.marco.DAO.CategoriaDAO;
import com.example.marco.model.Categoria;
import com.example.marco.util.JPAUtil;

public class CategoriaController {
    
     private EntityManager em = JPAUtil.getEntityManeger();
     CategoriaDAO c = new CategoriaDAO(em);

     public CategoriaController(){
     }

     public Categoria buscarPorID(int i) {
        Categoria p = c.buscarPorId(i);
        return p;
    }
}
