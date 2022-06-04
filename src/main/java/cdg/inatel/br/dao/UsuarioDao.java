package cdg.inatel.br.dao;

import cdg.inatel.br.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends Database implements BaseDao<Usuario> {

    public Usuario getLogin(Usuario usuario) {
        connect();

        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setString(++i, usuario.getLogin());
            pst.setString(++i, usuario.getSenha());
            result = pst.executeQuery();

            if (!result.next()) {
                usuario = null;
            } else {
                usuario.setId(result.getLong("id"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setFuncionario_id(result.getLong("funcionario_id"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
                pst.close();
                result.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return usuario;
    }


    @Override
    public Usuario get(Long id) {
        connect();

        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                usuario.setId(result.getLong("id"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setFuncionario_id(result.getLong("funcionario_id"));
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
                result.close();
                statement.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return usuario;
    }

    @Override
    public List<Usuario> getAll() {
        connect();

        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Usuario usuario = new Usuario();
                usuario.setId(result.getLong("id"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setFuncionario_id(result.getLong("funcionario_id"));

                usuarios.add(usuario);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
                result.close();
                statement.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return usuarios;
    }

    @Override
    public void save(Usuario usuario) {
        connect();

        String sql = "INSERT INTO usuario VALUES" +
                "(?, ?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, usuario.getId());
            pst.setString(++i, usuario.getLogin());
            pst.setString(++i, usuario.getSenha());
            pst.setLong(++i, usuario.getFuncionario_id());

            pst.execute();

            System.out.println("Executado com sucesso");

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
                result.close();
                statement.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(Usuario usuario) {
        connect();

        String sql = "UPDATE usuario SET " +
                "login = ?, senha = ? " +
                "WHERE id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;

            pst.setString(++i, usuario.getLogin());
            pst.setString(++i, usuario.getSenha());
            pst.setLong(++i, usuario.getId());

            pst.execute();

            System.out.println("Executado com sucesso");

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
                result.close();
                statement.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Usuario usuario) {
        connect();

        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, usuario.getId());

            pst.execute();

            System.out.println("Deletado com sucesso");

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
                result.close();
                statement.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
