package com.example.marco.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.marco.model.Cliente;

public class ClienteDAO {
    
    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

    public List<Cliente> buscarTodos(){
        String sql = "SELECT c FROM Cliente c";
		return em.createQuery(sql, Cliente.class).getResultList();
    }

    public Cliente buscarPorId(int i) {
		return em.find(Cliente.class, i);
	}

    public List<Cliente> buscarPorNome(String nome){
		String sql = "SELECT c FROM Cliente c WHERE c.nome LIKE :code";
		return em.createQuery(sql, Cliente.class).setParameter("code", "%" + nome + "%").getResultList();
	}

    public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.remove(cliente);
	}

}
