package com.example.marco;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.example.marco.controller.ClienteController;
import com.example.marco.controller.PedidoController;
import com.example.marco.controller.ProdutoController;
import com.example.marco.model.ItemPedido;
import com.example.marco.model.Pedido;
import com.example.marco.model.Produto;

public class App {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Bem Vindo a Lojinha");
        int op;
        boolean menuP = true;

        while (menuP) {
            System.out.println("""
                    Escolha o menu desejado
                    1 - Produto
                    2 - Cliente
                    3 - Pedido
                    0 - Sair
                    """);
            op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 0:
                    System.out.println("Ate logo");
                    menuP = false;
                    break;
                case 1:
                    menuProduto(scan);
                    break;
                case 2:
                    menuCliente(scan);
                    break;
                case 3:
                    menuPedido(scan);
                    break;
            }
        }

        scan.close();
    }

    private static void menuProduto(Scanner scan) {
        int op;
        boolean menu = true;
        ProdutoController pc = new ProdutoController();
        while (menu) {
            System.out.println("""
                        \nEscolha a opção:\n
                        1 - Cadastrar Produto
                        2 - Listar todos os Produtos
                        3 - Listar Produtos por Nome
                        4 - Listar Produtos por ID
                        5 - Alterar Produto
                        6 - Excluir Produto
                        0 - Voltar
                    """);
            op = scan.nextInt();
            scan.nextLine();
            switch (op) {
                case 0:
                    menu = false;
                    break;
                case 1:
                    System.out.println("Digite o nome do Produto");
                    String nome = scan.nextLine();
                    System.out.println(("Digite a descrição do Produto"));
                    String descricao = scan.nextLine();
                    System.out.println("Digite o valor do Produto");
                    BigDecimal valor = scan.nextBigDecimal();
                    pc.cadastrarProduto(nome, descricao, valor);
                    System.out.println("Produto cadastrado com sucesso");
                    break;
                case 2:
                    pc.buscarTodos();
                    break;
                case 3:
                    System.out.println("Digite o nome do produto que deseja pesquisar: ");
                    String nomeP = scan.nextLine();
                    List<Produto> pp = pc.buscarPorNome(nomeP);
                    if (pp != null) {
                        pp.forEach(p -> System.out.println(pp));
                    } else {
                        System.out.println("Nenhum produto encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("Digite a ID do produto que deseja pesquisar: ");
                    int i = scan.nextInt();
                    scan.nextLine();
                    pc.buscarPorID(i);
                    break;
                case 5:
                    System.out.println("Digite a ID do produto que deseja alterar: ");
                    int ii = scan.nextInt();
                    scan.nextLine();
                    pc.alterar(ii, scan);
                    System.out.println("Informações atualizadas");
                    break;
                case 6:
                    System.out.println("Digite ID do produto que deseja excluir: ");
                    int iD = scan.nextInt();
                    scan.nextLine();
                    pc.deletar(iD);
                    break;
                default:
                    System.out.println("Opção inexistente");
                    return;
            }
        }
    }

    private static void menuCliente(Scanner scan) {
        int op;
        boolean menu = true;
        ClienteController cC = new ClienteController();
        while (menu) {
            System.out.println("""
                    \nMenu Cliente\n
                    1 - Cadastrar Cliente
                    2 - Consultar Cliente
                    3 - Consultar Cliente por Nome
                    4 - Consultar Cliente por ID
                    5 - Atualizar Cliente
                    6 - Deletar Cliente
                    0 - Voltar
                    """);
            op = scan.nextInt();
            scan.nextLine();
            switch (op) {
                case 0:
                    menu = false;
                    break;
                case 1:
                    System.out.println("Digite o nome do Cliente: ");
                    String nome = scan.nextLine();
                    System.out.println("Digite o cpf do Cliente: ");
                    String cpf = scan.nextLine();
                    cC.cadastrarCliente(nome, cpf);
                    System.out.println("Cliente Cadastrado com Sucesso");
                    break;
                case 2:
                    cC.buscarTodos();
                    break;
                case 3:
                    System.out.println("Digite o nome do cliente: ");
                    String name = scan.nextLine();
                    cC.buscarPorNome(name);
                    break;
                case 4:
                    System.out.println("Digite a Id do cliente que deseja buscar: ");
                    int i = scan.nextInt();
                    scan.nextLine();
                    cC.buscarPorID(i);
                    break;
                case 5:
                    System.out.println("Digite a Id do cliente que deseja Atualizar: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    cC.alterar(id, scan);
                    System.out.println("Informações Atualizadas");
                    break;
                case 6:
                    System.out.println("Digite a Id do cliente que deseja Deletar: ");
                    int idD = scan.nextInt();
                    scan.nextLine();
                    cC.deletar(idD);
                    System.out.println("Cliente deletado");
                    break;
                default:
                    System.out.println("Opção inexistente");
                    return;
            }
        }
        cC.fecharConexao();
    }

    private static void menuPedido(Scanner scan) {
        int op;
        boolean menu = true;
        PedidoController pC = new PedidoController();
        ProdutoController pController = new ProdutoController();
        while (menu) {
            System.out.println("""
                        \nEscolha a opção:\n
                        1 - Realizar Pedido
                        2 - Listar todos os Pedidos
                        3 - Listar por ID os Pedidos
                        4 - Excluir Pedidos
                        0 - Voltar
                    """);
            op = scan.nextInt();
            scan.nextLine();
            switch (op) {
                case 0:
                    menu = false;
                    break;
                case 1:
                    System.out.println("Insira a Id do Cliente que deseja efetuar uma venda");
                    int id = scan.nextInt();
                    scan.nextLine();

                    Pedido pedido = new Pedido(new ClienteController().buscarPorID(id));
                    int op2;

                    do {
                        System.out.println("--------------------------");

                        pController.buscarTodos();

                        System.out.println("--------------------------");

                        System.out.println("Digite a Id do produto que deseja comprar");
                        int idProduto = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Digite a quantidade do produto que deseja comprar");
                        int qntdProduto = scan.nextInt();
                        scan.nextLine();
                        pedido.adicionarItem(new ItemPedido(qntdProduto, pedido, pController.buscarPorID(idProduto)));
                        System.out.println("--------------------------");
                        System.out.println("""
                                Quer adicionar mais um?
                                1 - Sim
                                2 - Não
                                """);
                        op2 = scan.nextInt();
                        scan.nextLine();
                    } while (op2 != 2);

                    pC.cadastrarPedido(pedido);
                    break;
                case 2:
                    pC.buscarTodos();
                    break;
                case 3:
                    System.out.println("Digite a ID da Venda que deseja verificar");
                    int idVenda = scan.nextInt();
                    scan.nextLine();
                    pC.buscarPorID(idVenda);
                    break;
                case 4:
                    System.out.println("Digite a ID da Venda que deseja excluir");
                    int idVenda2 = scan.nextInt();
                    pC.deletar(idVenda2);
                    System.out.println("Venda deletada");
                    break;
                default:
                    System.out.println("Opção inexistente");
                    return;
            }
        }
    }
}
