package cdg.inatel.br.view;

import cdg.inatel.br.Main;
import cdg.inatel.br.controller.PedidoController;
import cdg.inatel.br.dao.AdicionalDao;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.dao.ProdutoDao;
import cdg.inatel.br.model.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainView {
    Scanner input = new Scanner(System.in);
    public Usuario login(){
        Usuario usuario = new Usuario();
        System.out.println("===========SYSTEM LOGIN===========");
        System.out.print("Login: ");
        usuario.setLogin(input.next());
        System.out.print("Password: ");
        usuario.setSenha(input.next());
        System.out.println("==================================");
        return usuario;
    }

    public void mainMenu(){
        System.out.println("===========SYSTEM MENU===========");
        System.out.println("1 - EFETUAR PEDIDO");
        System.out.println("2 - CANCELAR PEDIDO");
        System.out.println("3 - VER PEDIDOS");
        System.out.println("4 - SAIR");
        System.out.println("==================================");
        System.out.print("=> ");
        int opcao = input.nextInt();
        switch (opcao){
            case 1:
                System.out.println("efetuando pedido");
                new PedidoController().efetuarPedido();
                break;
            case 2:
                System.out.println("cancelando pedido");
                break;
            case 3:
                System.out.println("vendo pedido");
                allPedidos();
                break;
            case 4:
                System.out.print("Saindo");
                for (int i = 0; i < 3; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print(".");
                }
                break;
            default:
                System.out.println("Entrada invalida!");
                mainMenu();
        }
    }

    public void allPedidos() {
        PedidoDao pedidoDao = new PedidoDao();
        var pedidos = pedidoDao.getAll();

        for (
                Pedido p :
                pedidos) {
            System.out.println("================================");
            System.out.println(p.getNome());
            System.out.println(p.getId());
            System.out.println("================================");
        }
    }

    public void showPedido(Long pedido_id) {
        PedidoDao pedidoDao = new PedidoDao();
        var pedido = pedidoDao.get(pedido_id);

        System.out.println("================================");
        System.out.println("Pedido no " + pedido.getId() + " - " + pedido.getNome() + ":");

        System.out.println("================================");
    }

    public void allProdutos() {
        ProdutoDao produtoDao = new ProdutoDao();
        var produtos = produtoDao.getAll();
        System.out.println("================================");
        for (
                Produto p :
                produtos) {
            System.out.println(p.getId()+". "+p.getNome()+"(R$"+p.getValor()+")");
            System.out.println("     "+p.getDescricao());
        }
        System.out.println("================================");
    }
    public void allAdicionais() {
        AdicionalDao adicionalDao = new AdicionalDao();
        var adicionais = adicionalDao.getAll();
        System.out.println("================================");
        for (
                Adicional a :
                adicionais) {
            System.out.println(a.getId()+". "+a.getNome()+"(R$"+a.getValor()+")");
        }
        System.out.println("================================");
    }

    public ArrayList<Ordem> getOrdens(Long pedido_id){
        var ordens = new ArrayList<Ordem>();
        System.out.println("Escolha um produto: ");
        allProdutos();

        Ordem ordem;

        boolean novaOrdem = true;
        boolean novoAdd = true;

        do{
            ordem = new Ordem();
            System.out.print("Produto: ");
            ordem.setProduto_id(input.nextLong());
            System.out.print("Quantidade: ");
            ordem.setQuantidade(input.nextInt());
            input.nextLine();
            System.out.print("Observacao: ");
            ordem.setObservacoes(input.nextLine());
            ordem.setPedido_id(pedido_id);

            //adicionando adicionais
            System.out.println("Deseja adicional? (y/n): ");
            novoAdd = input.next().equals("y");
            var adicionais = new ArrayList<Adicional>();

            if (novoAdd) {
                allAdicionais();
            }
            while (novoAdd){
                var adicional = new Adicional();
                System.out.print("Adicional: ");
                adicional.setId(input.nextLong());
                adicionais.add(adicional);
                System.out.println("Mais algum adicional? (y/n): ");
                novoAdd = input.next().equals("y");
             }
            ordem.setAdicionais(adicionais);
            ordens.add(ordem);

            System.out.print("Deseja incluir nova ordem? (y/n): ");

            novaOrdem = input.next().equals("y");

        } while (novaOrdem);

        return ordens;
    }
}
