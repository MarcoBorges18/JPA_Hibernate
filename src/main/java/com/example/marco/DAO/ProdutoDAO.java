package com.example.marco.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.marco.model.Produto;

public class ProdutoDAO {
	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(int i) {
		return em.find(Produto.class, i);
	}

	public List<Produto> buscarTodos(){
		String sql = "SELECT p FROM Produto p";
		return em.createQuery(sql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome){
		String sql = "SELECT p FROM Produto p WHERE p.nomeProduto = ?1";
		return em.createQuery(sql, Produto.class).setParameter(1, nome).getResultList();
	}

}
