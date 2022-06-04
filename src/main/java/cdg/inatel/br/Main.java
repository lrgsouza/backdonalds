package cdg.inatel.br;

import cdg.inatel.br.controller.UsuarioController;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.dao.TruncateDao;
import cdg.inatel.br.model.Pedido;
import cdg.inatel.br.model.Usuario;

public class Main {
    public static void main(String[] args) {

        //call login
        Usuario usuario = new UsuarioController().login();
        System.out.println("Seja bem vindo " + usuario.getFuncionario().getNome());

        //call menu




        if (false) {
            PedidoDao pedidoDao = new PedidoDao();

            var pedido = new Pedido(1L, "Chibiruibi", "654987", false, false, true);
            pedidoDao.save(pedido);

            System.out.println("===============================================");

            var pedidos = pedidoDao.getAll();

            for (Pedido p :
                    pedidos) {
                System.out.println(p.getNome());
                System.out.println(p.getId());
                System.out.println("================================");
            }
        }


    }
}
