package cdg.inatel.br.view;

import cdg.inatel.br.controller.PedidoController;
import cdg.inatel.br.dao.AdicionalDao;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.dao.ProdutoDao;
import cdg.inatel.br.model.*;

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
                System.exit(0);
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
            if (idPedido != 0) {
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
            if (idPedido != 0) {
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
            if (idPedido != 0){
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
            if (idPedido != 0) {
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
            if (idPedido != 0){
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
        System.out.println("Total pedido (R$"+String.format("%.2f", valorTotal)+")");
        //System.out.println("================================");
    }

    public static double showOrdem(Ordem ordem) {

        System.out.println("================================");
        Produto produto = new ProdutoDao().get(ordem.getProduto_id());
        double valorTotal = (produto.getValor() * ordem.getQuantidade());

        System.out.println("      " + ordem.getQuantidade() + " " +
                produto.getNome() + "(R$" + String.format("%.2f", (produto.getValor() * ordem.getQuantidade())) + ")");

        for (Adicional adicional:
                ordem.getAdicionais()) {
            System.out.println("            + " + adicional.getNome() + "(R$" + String.format("%.2f", adicional.getValor()) + ")");
            valorTotal += (ordem.getQuantidade() * adicional.getValor());
        }

        if(!ordem.getObservacoes().equals(""))
            System.out.println("      Obs: " + ordem.getObservacoes());
        System.out.println("      Total (R$" + String.format("%.2f", valorTotal) + ")");

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
            System.out.println(p.getId()+". "+p.getNome()+"(R$"+String.format("%.2f", p.getValor())+")");
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
            System.out.println(a.getId()+". "+a.getNome()+"(R$"+String.format("%.2f", a.getValor())+")");
        }
        System.out.println("================================");
    }

    public ArrayList<Ordem> getOrdens(Long pedido_id){
        Scanner input = new Scanner(System.in);
        var ordens = new ArrayList<Ordem>();
        System.out.println("Escolha um produto: ");


        boolean novaOrdem;
        boolean novoAdd;

        do{
            allProdutos();
            Ordem ordem = Ordem.getUserInput(pedido_id);

            //checa se é comida
            var produto = new ProdutoDao().get(ordem.getProduto_id());
            if (produto.getProduto_tipo_id() == 1){
                //adicionando adicionais
                System.out.println("Deseja adicional? (y/n): ");
                novoAdd = input.next().equals("y");
                var adicionais = new ArrayList<Adicional>();

                while (novoAdd){
                    allAdicionais();
                    System.out.print("Adicional: ");
                    adicionais.add(Adicional.inputId(input.nextLong()));
                    System.out.println("Mais algum adicional? (y/n): ");
                    novoAdd = input.next().equals("y");
                }
                ordem.setAdicionais(adicionais);
            }

            ordens.add(ordem);

            System.out.print("Deseja incluir nova ordem? (y/n): ");

            novaOrdem = input.next().equals("y");

        } while (novaOrdem);

        return ordens;
    }
}
