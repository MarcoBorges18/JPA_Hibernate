package com.example.marco.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.example.marco.DAO.ClienteDAO;
import com.example.marco.model.Cliente;
import com.example.marco.util.JPAUtil;

public class ClienteController {

    private EntityManager em = JPAUtil.getEntityManeger();
    private ClienteDAO cD = new ClienteDAO(em);

    public ClienteController() {
    }

    public void cadastrarCliente(String nome, String cpf) {
        Cliente cliente = new Cliente(nome, cpf);

        em.getTransaction().begin();
        cD.cadastrar(cliente);
        em.getTransaction().commit();
    }

    public void buscarTodos() {
        List<Cliente> todosC = cD.buscarTodos();
        todosC.forEach(c -> System.out.println(c));
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> cc = cD.buscarPorNome(nome);
        cc.forEach(c -> System.out.println(c));
        return cc;
    }

    public Cliente buscarPorID(int i) {
        Cliente c = cD.buscarPorId(i);
        System.out.println(c);
        return c;
    }

    public void deletar(int i) {
        em.getTransaction().begin();

        Cliente delete = buscarPorID(i);

        cD.remover(delete);
        em.getTransaction().commit();
    }

    public void alterar(int i, Scanner scan) {
        em.getTransaction().begin();

        Cliente c = buscarPorID(i);

        System.out.println("""
                    Selecione a opção que que deseja atualizar:
                    1 - Nome
                    2 - CPF
                """);
        int op = scan.nextInt();
        scan.nextLine();
        switch (op) {
            case 1:
                System.out.println("Novo nome: ");
                String newName = scan.nextLine();
                c.setNome(newName);
                break;
            case 2:
                System.out.println("Digite o novo cpf: ");
                String cpf = scan.nextLine();
                c.setCpf(cpf);
                break;
        }
        cD.atualizar(c);
        em.getTransaction().commit();
    }

    public void fecharConexao(){
        em.close();
    }
}
