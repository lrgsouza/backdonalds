package cdg.inatel.br.view;

import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.model.Pedido;
import cdg.inatel.br.model.Usuario;

import java.util.Scanner;

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

    public void viewnf() {
        PedidoDao pedidoDao = new PedidoDao();
        var pedidos = pedidoDao.getAll();

        for (
                Pedido p :
                pedidos) {
            System.out.println(p.getNome());
            System.out.println(p.getId());
            System.out.println("================================");
        }
    }
}
