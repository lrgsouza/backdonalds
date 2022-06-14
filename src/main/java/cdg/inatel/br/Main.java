package cdg.inatel.br;

import cdg.inatel.br.controller.UsuarioController;
import cdg.inatel.br.dao.FuncionarioDao;
import cdg.inatel.br.dao.PedidoDao;
import cdg.inatel.br.dao.TruncateDao;
import cdg.inatel.br.dao.UsuarioDao;
import cdg.inatel.br.model.Pedido;
import cdg.inatel.br.model.Usuario;
import cdg.inatel.br.view.MainView;

public class Main {
    public static void main(String[] args) {

        //call login
        Usuario usuario = new UsuarioController().login();
        //Usuario usuario = new UsuarioDao().get(1L);
        usuario.setFuncionario(new FuncionarioDao().get(usuario.getFuncionario_id()));
        /*===========================================================================*/
        System.out.println("Seja bem vindo " + usuario.getFuncionario().getNome());

        //call menu
        MainView.mainMenu();



    }
}
