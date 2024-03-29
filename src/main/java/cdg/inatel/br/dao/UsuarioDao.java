package cdg.inatel.br.dao;

import cdg.inatel.br.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends Database implements BaseDao<Usuario> {

    public static Usuario getLogin(Usuario usuario) {
        connect();

        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setString(++i, usuario.getLogin());
            pst.setString(++i, usuario.getSenha());
            result = pst.executeQuery();

            if (!result.next()) {
                return null;
            } else {
                usuario = Usuario.getByResult(result);
            }

        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return usuario;
    }


    @Override
    public Usuario get(Long id) {
        connect();

        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                usuario = Usuario.getByResult(result);
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return usuario;
    }

    @Override
    public ArrayList<Usuario> getAll() {
        connect();

        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Usuario usuario = Usuario.getByResult(result);

                usuarios.add(usuario);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
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
            closeAllSql();
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
            closeAllSql();
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
            closeAllSql();
        }
    }
}
