package com.example.marco.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import com.example.marco.model.Pedido;

public class PedidoDAO {
    
    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido p) {
		this.em.persist(p);
	}

    public List<Pedido> buscarTodos(){
        String sql = "SELECT p FROM Pedido p";
		return em.createQuery(sql, Pedido.class).getResultList();
    }

    public Pedido buscarPorId(int i) {
		return em.find(Pedido.class, i);
	}

    public List<Pedido> buscarPorNome(String nome){
		String sql = "SELECT c FROM Cliente c WHERE c.nome LIKE :code";
		return em.createQuery(sql, Pedido.class).setParameter("code", "%" + nome + "%").getResultList();
	}

    public void atualizar(Pedido p) {
		this.em.merge(p);
	}

	public void remover(Pedido p) {
		p = em.merge(p);
		this.em.remove(p);
	}

}
