package cdg.inatel.br.controller;

import cdg.inatel.br.dao.OrdemDao;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.model.Ordem;
import cdg.inatel.br.model.Pedido;
import cdg.inatel.br.view.MainView;

import java.util.ArrayList;
import java.util.Scanner;

import static cdg.inatel.br.view.MainView.waiting;

public class PedidoController {
    Scanner input = new Scanner(System.in);

    public static void efetuarPedido(){
        //criando pedido
        Pedido pedido = Pedido.getUserInput();

        //criando ordens com o id do pedido
        OrdemController.realizarOrdens(pedido.getId());

        //pagando pedido
        System.out.println("Processando pagamento");
        waiting();
        System.out.println("\nTransação Autorizada!");
        pedido.setPago(true);
        new PedidoDao().update(pedido);

        //mostrando pedido
        mostrarPedido(pedido.getId());
    }

    public static void mostrarPedido(Long pedido_id){
        Pedido pedido = new PedidoDao().get(pedido_id);
        ArrayList<Ordem> ordens = new OrdemController().getByPedidoId(pedido_id);

        MainView.showPedido(pedido, ordens);

    }

}
