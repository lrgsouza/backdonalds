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
        Pedido pedido = new Pedido();
        System.out.print("Nome do cliente: ");
        pedido.setNome(input.next());
        pedido.setId(new PedidoDao().saveReturning(pedido));
        //criando ordens com o id do pedido

        var ordens = new MainView().getOrdens(pedido.getId());

        for (Ordem ordem:
             ordens) {
            new OrdemDao().save(ordem);
        }

        //mostrando pedido
        System.out.println("Aqui aparece o pedido:");
        new MainView().showPedido(pedido.getId());



        return null;
    }

}
