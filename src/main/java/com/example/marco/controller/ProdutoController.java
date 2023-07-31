package com.example.marco.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.example.marco.DAO.ProdutoDAO;
import com.example.marco.model.Produto;
import com.example.marco.util.JPAUtil;

public class ProdutoController {

    public ProdutoController() {
    }

    public void cadastrarProduto(String nome, String descricao, BigDecimal preco) {

        CategoriaController c = new CategoriaController();
        Produto produto = new Produto(nome, descricao, preco, c.buscarPorID(1));

        EntityManager em = JPAUtil.getEntityManeger();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin(); // Começa uma transação

        produtoDAO.cadastrar(produto); // Entre no metodo para saber mais

        em.getTransaction().commit(); // Envia a persistencia para o banco
        em.close(); // Fecha a conexão com o banco
    }

    public Produto buscarPorID(int i) {
        EntityManager em = JPAUtil.getEntityManeger();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(i);
        System.out.println(p);
        return p;
    }

    public void buscarTodos() {
        EntityManager em = JPAUtil.getEntityManeger();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        List<Produto> todos = produtoDAO.buscarTodos();
        todos.forEach(p2 -> System.out.println(p2));
    }

    public List<Produto> buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManeger();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        List<Produto> pp = produtoDAO.buscarPorNome(nome);
        return pp;
    }

    public void deletar(int i) {
        EntityManager em = JPAUtil.getEntityManeger();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();

        Produto delete = buscarPorID(i);

        produtoDAO.remover(delete);
        em.getTransaction().commit();
        em.close();
    }

    public void alterar(int i, Scanner scan) {
        EntityManager em = JPAUtil.getEntityManeger();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();

        Produto p = buscarPorID(i);

        System.out.println("""
                    Selecione a opção que que deseja atualizar:
                    1 - Nome
                    2 - Descrição
                    3 - Preço
                    4 - Categoria
                """);
        int opA = scan.nextInt();
        scan.nextLine();
        switch (opA) {
            case 1:
                System.out.println("Novo nome: ");
                String newName = scan.nextLine();
                p.setNomeProduto(newName);
                break;
            case 2:
                System.out.println("Nova Descrição: ");
                String newDesc = scan.nextLine();
                p.setDescricao(newDesc);
            case 3:
                System.out.println("Novo Preço: ");
                BigDecimal newPreco = scan.nextBigDecimal();
                p.setPreco(newPreco);
                break;
            case 4:
                System.out.println("Em implemetação: ");
                break;
            default:
                System.out.println("Opção inexistente");
                return;
        }

        produtoDAO.atualizar(p);
        em.getTransaction().commit();
        em.close();
    }
}
