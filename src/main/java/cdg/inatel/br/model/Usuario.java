package cdg.inatel.br.model;

import cdg.inatel.br.dao.FuncionarioDao;
import cdg.inatel.br.dao.UsuarioDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Long id;
    private Long funcionario_id;
    private String login;
    private String senha;
    private Funcionario funcionario;

    public static Usuario login(){
        Scanner input = new Scanner(System.in);
        Usuario usuario = new Usuario();
        System.out.println("===========SYSTEM LOGIN===========");
        System.out.print("Login: ");
        usuario.setLogin(input.next());
        System.out.print("Password: ");
        usuario.setSenha(input.next());
        System.out.println("==================================");
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

    public static Usuario getByResult(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(result.getLong("id"));
        usuario.setLogin(result.getString("login"));
        usuario.setSenha(result.getString("senha"));
        usuario.setFuncionario_id(result.getLong("funcionario_id"));
        return usuario;
    }



}
