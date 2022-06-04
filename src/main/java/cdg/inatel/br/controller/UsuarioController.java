package cdg.inatel.br.controller;

import cdg.inatel.br.Main;
import cdg.inatel.br.dao.FuncionarioDao;
import cdg.inatel.br.dao.UsuarioDao;
import cdg.inatel.br.model.Usuario;
import cdg.inatel.br.view.CaixaView;
import cdg.inatel.br.view.MainView;

public class UsuarioController {
    MainView mainView = new MainView();
    UsuarioDao usuarioDao = new UsuarioDao();

    public Usuario login(){
        Usuario usuario = mainView.login();
        usuario = usuarioDao.getLogin(usuario);
        if(usuario != null){
            System.out.println("Login efetuado com sucesso!");
            usuario.setFuncionario(new FuncionarioDao().get(usuario.getFuncionario_id()));
            return usuario;
        }else{
            System.out.println("Usuario ou senha errado, tente novamente:");
            login();
        }
        return null;
    }


}
