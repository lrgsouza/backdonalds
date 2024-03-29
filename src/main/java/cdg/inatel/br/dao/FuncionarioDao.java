package cdg.inatel.br.dao;

import cdg.inatel.br.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends Database implements BaseDao<Funcionario> {


    @Override
    public Funcionario get(Long id) {
        connect();

        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionario WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                funcionario = Funcionario.getByResult(result);
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return funcionario;
    }

    @Override
    public ArrayList<Funcionario> getAll() {
        connect();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Funcionario funcionario = Funcionario.getByResult(result);

                funcionarios.add(funcionario);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return funcionarios;
    }

    @Override
    public void save(Funcionario funcionario) {
        connect();

        String sql = "INSERT INTO funcionario VALUES" +
                "(?, ?, ?, ?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, funcionario.getId());
            pst.setString(++i, funcionario.getNome());
            pst.setString(++i, funcionario.getEmail());
            pst.setString(++i, funcionario.getTelefone());

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
    public void update(Funcionario funcionario) {
        connect();

        String sql = "UPDATE funcionario SET " +
                "nome = ?, email = ?, telefone =? " +
                "WHERE id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;

            pst.setString(++i, funcionario.getNome());
            pst.setString(++i, funcionario.getEmail());
            pst.setString(++i, funcionario.getTelefone());
            pst.setLong(++i, funcionario.getId());

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
    public void delete(Funcionario funcionario) {
        connect();

        String sql = "DELETE FROM funcionario WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, funcionario.getId());

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
