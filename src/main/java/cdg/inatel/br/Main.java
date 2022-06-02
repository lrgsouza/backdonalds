package cdg.inatel.br;

import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.dao.TruncateDao;
import cdg.inatel.br.model.Pedido;

public class Main {
    public static void main(String[] args) {
        new TruncateDao().truncateTable("pedido");
        new TruncateDao().truncateTable("ordem");


        PedidoDao pedidoDao = new PedidoDao();

        var pedido = new Pedido(1l, "Chibiruibi", "654987", false, false, true);

        pedidoDao.save(pedido);

        System.out.println("-------------------");
        pedido = pedidoDao.get(100L);

        System.out.println(pedido.getNomecliente());
        System.out.println(pedido.getId());

        pedido.setNomecliente("Giovanna");

        pedidoDao.update(pedido);

        pedido = pedidoDao.get(100L);

        System.out.println(pedido.getNomecliente());
        System.out.println(pedido.getId());

        System.out.println("-------------------");

        pedido = pedidoDao.get(2L);

        System.out.println(pedido.getNomecliente());
        System.out.println(pedido.getId());

        System.out.println("-------------------");

        var pedidos = pedidoDao.getAll();

        for (Pedido p:
             pedidos) {
            System.out.println(p.getNomecliente());
            System.out.println(p.getId());
            System.out.println("-------------------");
        }



    }
}
