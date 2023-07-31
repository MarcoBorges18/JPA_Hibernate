package com.example.marco.DAO;

import javax.persistence.EntityManager;

import com.example.marco.model.Categoria;

public class CategoriaDAO {
    private EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}

	 public Categoria buscarPorId(int i) {
		return em.find(Categoria.class, i);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
}
