package cdg.inatel.br.view;

import cdg.inatel.br.Main;
import cdg.inatel.br.controller.PedidoController;
import cdg.inatel.br.dao.AdicionalDao;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.dao.ProdutoDao;
import cdg.inatel.br.model.*;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainView {
    public static void mainMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("===========SYSTEM MENU===========");
        System.out.println("1 - EFETUAR PEDIDO");
        System.out.println("2 - VER PEDIDOS FINALIZADOS");
        System.out.println("3 - VER PEDIDOS (TODOS)");
        System.out.println("4 - VER PEDIDOS (COZINHA)");
        System.out.println("5 - VER PEDIDOS (BALCÃO)");
        System.out.println("6 - CANCELAR PEDIDO");
        System.out.println("0 - SAIR");
        System.out.println("==================================");
        System.out.print("=> ");
        int opcao = input.nextInt();
        switch (opcao) {
            case 1 -> {
                PedidoController.efetuarPedido();
                mainMenu();
            }
            case 2 -> {
                allPedidosFinalizados();
                mainMenu();
            }
            case 3 -> {
                //CAIXA
                allPedidosEmAndamento();
                mainMenu();
            }
            case 4 -> {
                //COZINHA
                allPedidosCozinha();
                mainMenu();
            }
            case 5 -> {
                //BALCÃO
                allPedidosBalcao();
                mainMenu();
            }
            case 6-> {
                //CANCELAR
                cancelarPedido();
                mainMenu();
            }
            case 0 -> {
                System.out.print("Saindo");
                waiting();
            }
            default -> System.out.println("Entrada invalida!");

        }

    }

    public static void waiting(){
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println();
    }

    public static void allPedidosFinalizados() {
        PedidoDao pedidoDao = new PedidoDao();
        Scanner input = new Scanner(System.in);
        var pedidos = pedidoDao.getAllFinalizados();
        if (pedidos.isEmpty()){
            System.out.println("Não há pedidos finalizados");
            mainMenu();
        }
        else {
            System.out.println("========= Pedidos Finalizados: =============");
            for (
                    Pedido p :
                    pedidos) {
                System.out.println("Pedido no " + p.getId() + " - " + p.getNome());
            }
            System.out.println("================================");
            System.out.print("Insira o ID do pedido para mais detalhes, ou digite 0 para voltar ao menu principal: ");
            long idPedido = input.nextLong();
            if (idPedido == 0) {
                mainMenu();
            } else {
                PedidoController.mostrarPedido(idPedido);
            }
        }
    }

    public static void allPedidosEmAndamento() {
        PedidoDao pedidoDao = new PedidoDao();
        Scanner input = new Scanner(System.in);
        var pedidos = pedidoDao.getAllEmAndamento();
        if(pedidos.isEmpty()){
            System.out.println("Não há pedidos em andamento");
            mainMenu();
        }else {
            System.out.println("========= Pedidos em andamento: =============");
            for (
                    Pedido p :
                    pedidos) {
                System.out.println("Pedido no " + p.getId() + " - " + p.getNome());
            }
            System.out.println("================================");
            System.out.print("Insira o ID do pedido para mais detalhes, ou digite 0 para voltar ao menu principal: ");
            long idPedido = input.nextLong();
            if (idPedido == 0) {
                mainMenu();
            } else {
                PedidoController.mostrarPedido(idPedido);
            }
        }
    }

    public static void allPedidosCozinha() {
        PedidoDao pedidoDao = new PedidoDao();
        Scanner input = new Scanner(System.in);
        var pedidos = pedidoDao.getAllCozinha();
        if(pedidos.isEmpty()){
            System.out.println("Não há pedidos para finalizar");
            mainMenu();
        }else {
            System.out.println("========= Pedidos em andamento (COZINHA): =============");
            for (
                    Pedido p :
                    pedidos) {
                System.out.println("Pedido no " + p.getId() + " - " + p.getNome());
            }
            System.out.println("================================");
            System.out.print("Insira o ID do pedido para finaliza-lo, ou digite 0 para voltar ao menu principal: ");
            long idPedido = input.nextLong();
            if (idPedido == 0){
                mainMenu();
            }else{
                //atualizando finalizado
                Pedido pedido = pedidoDao.get(idPedido);
                pedido.setFinalizado(true);
                pedidoDao.update(pedido);
                allPedidosCozinha();
            }
        }

    }

    public static void allPedidosBalcao() {
        PedidoDao pedidoDao = new PedidoDao();
        Scanner input = new Scanner(System.in);
        var pedidos = pedidoDao.getAllBalcao();
        if(pedidos.isEmpty()){
            System.out.println("Não há pedidos para retirada");
            mainMenu();
        }
        else {
            System.out.println("========= Pedidos em andamento (BALCAO): =============");
            for (
                    Pedido p :
                    pedidos) {
                System.out.println("Pedido no " + p.getId() + " - " + p.getNome());
            }
            System.out.println("================================");
            System.out.print("Insira o ID do pedido para retirada, ou digite 0 para voltar ao menu principal: ");
            long idPedido = input.nextLong();
            if (idPedido == 0) {
                mainMenu();
            } else {
                //atualizando retirado
                Pedido pedido = pedidoDao.get(idPedido);
                pedido.setRetirado(true);
                pedidoDao.update(pedido);
                allPedidosBalcao();
            }
        }
    }

    public static void cancelarPedido() {
        PedidoDao pedidoDao = new PedidoDao();
        Scanner input = new Scanner(System.in);
        var pedidos = pedidoDao.getAllCozinha();
        if(pedidos.isEmpty()){
            System.out.println("Não há pedidos para cancelar");
            mainMenu();
        }else {
            System.out.println("========= Pedidos ainda não finalizados: =============");
            for (
                    Pedido p :
                    pedidos) {
                System.out.println("Pedido no " + p.getId() + " - " + p.getNome());
            }
            System.out.println("================================");
            System.out.print("Insira o ID do pedido para cancela-lo, ou digite 0 para voltar ao menu principal: ");
            long idPedido = input.nextLong();
            if (idPedido == 0){
                mainMenu();
            }else{
                //atualizando finalizado
                Pedido pedido = pedidoDao.get(idPedido);
                pedidoDao.delete(pedido);
                mainMenu();
            }
        }

    }

    public static void showPedido(Pedido pedido, ArrayList<Ordem> ordens) {
        double valorTotal = 0d;
        System.out.println("================================");
        System.out.println("Pedido no " + pedido.getId() + " - " + pedido.getNome() + ":");

        for (Ordem ordem:
                ordens) {
            valorTotal += showOrdem(ordem);
        }
        System.out.println("Total pedido (R$"+valorTotal+")");
        //System.out.println("================================");
    }

    public static double showOrdem(Ordem ordem) {

        System.out.println("================================");
        Produto produto = new ProdutoDao().get(ordem.getProduto_id());
        double valorTotal = (produto.getValor() * ordem.getQuantidade());

        System.out.println("      " + ordem.getQuantidade() + " " +
                produto.getNome() + "(R$" + (produto.getValor() * ordem.getQuantidade()) + ")");

        for (Adicional adicional:
                ordem.getAdicionais()) {
            System.out.println("            + " + adicional.getNome() + "(R$" + adicional.getValor() + ")");
            valorTotal += (ordem.getQuantidade() * adicional.getValor());
        }

        System.out.println("      Obs: " + ordem.getObservacoes());
        System.out.println("      Total (R$" + valorTotal + ")");

        System.out.println("================================");
        return valorTotal;
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
        Scanner input = new Scanner(System.in);
        var ordens = new ArrayList<Ordem>();
        System.out.println("Escolha um produto: ");


        Ordem ordem;

        boolean novaOrdem;
        boolean novoAdd;

        do{
            allProdutos();
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

            while (novoAdd){
                allAdicionais();
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
