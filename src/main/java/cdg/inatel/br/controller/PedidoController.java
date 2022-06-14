package cdg.inatel.br.controller;

import cdg.inatel.br.dao.OrdemDao;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.model.Ordem;
import cdg.inatel.br.model.Pedido;
import cdg.inatel.br.view.MainView;

import java.util.ArrayList;
import java.util.Scanner;

public class PedidoController {
    Scanner input = new Scanner(System.in);

    public Pedido efetuarPedido(){
        //criando pedido
        Pedido pedido = Pedido.getUserInput();

        //criando ordens com o id do pedido
        OrdemController.realizarOrdens(pedido.getId());

        //mostrando pedido
        mostrarPedido(pedido.getId());

        return pedido;
    }

    public void mostrarPedido(Long pedido_id){
        Pedido pedido = new PedidoDao().get(pedido_id);
        ArrayList<Ordem> ordens = new OrdemController().getByPedidoId(pedido_id);

        new MainView().showPedido(pedido, ordens);

    }

}
