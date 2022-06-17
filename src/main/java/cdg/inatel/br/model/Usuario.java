package cdg.inatel.br.model;

import cdg.inatel.br.dao.FuncionarioDao;
import cdg.inatel.br.dao.UsuarioDao;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

@Data
public class Usuario {
    private Long id;
    private Long funcionario_id;
    private String login;
    private String senha;
    private Funcionario funcionario;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public static Usuario login(){
        Scanner input = new Scanner(System.in);
        System.out.println("===========SYSTEM LOGIN===========");
        System.out.print("Login: ");
        String login = input.next();
        System.out.print("Password: ");
        String senha = input.next();
        System.out.println("==================================");

        Usuario usuario = new Usuario(login, senha);

        usuario = UsuarioDao.getLogin(usuario);

        if(usuario != null){
            System.out.println("Login efetuado com sucesso!");
            usuario.setFuncionario(new FuncionarioDao().get(usuario.getFuncionario_id()));
        }else{
            System.out.println("Usuario ou senha errado, tente novamente:");
            usuario = login();
        }
        return usuario;
    }

    public Usuario(Long id, Long funcionario_id, String login, String senha) {
        this.id = id;
        this.funcionario_id = funcionario_id;
        this.login = login;
        this.senha = senha;
    }

    public static Usuario getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        Long funcionario_id = result.getLong("funcionario_id");
        String login = result.getString("login");
        String senha = result.getString("senha");
        return new Usuario(id, funcionario_id, login, senha);
    }



}
