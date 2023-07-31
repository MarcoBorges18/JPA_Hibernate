package com.example.marco.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.marco.DAO.PedidoDAO;
import com.example.marco.model.Pedido;
import com.example.marco.util.JPAUtil;

public class PedidoController {

    private EntityManager em = JPAUtil.getEntityManeger();
    private PedidoDAO pD = new PedidoDAO(em);

    public PedidoController() {
    }

    public void cadastrarPedido(Pedido pedido) {
        em.getTransaction().begin();
        pD.cadastrar(pedido);
        em.getTransaction().commit();
    }

    public void buscarTodos() {
        List<Pedido> todosP = pD.buscarTodos();
        todosP.forEach(p -> System.out.println(p));
    }

    public List<Pedido> buscarPorNome(String nome) {
        List<Pedido> pp = pD.buscarPorNome(nome);
        pp.forEach(p -> System.out.println(p));
        return pp;
    }

    public Pedido buscarPorID(int i) {
        Pedido p = pD.buscarPorId(i);
        System.out.println(p);
        return p;
    }

    public void deletar(int i) {
        em.getTransaction().begin();

        Pedido delete = buscarPorID(i);

        pD.remover(delete);
        em.getTransaction().commit();
    }

    public void fecharConexao(){
        em.close();
    }
}
