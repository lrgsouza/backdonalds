package cdg.inatel.br.dao;

import cdg.inatel.br.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends Database implements BaseDao<Funcionario> {


    @Override
    public Funcionario get(Long id) {
        connect();

        Funcionario funcionario = new Funcionario();
        String sql = "SELECT * FROM funcionario WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                funcionario.setId(result.getLong("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setTelefone(result.getString("telefone"));
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> getAll() {
        connect();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId(result.getLong("id"));


                funcionarios.add(funcionario);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return funcionarios;
    }

    @Override
    public void save(Funcionario funcionario) {
        connect();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "INSERT INTO funcionario VALUES" +
                "(?, ?, ?, ?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, funcionario.getId());


            pst.execute();

            System.out.println("Executado com sucesso");

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(Funcionario funcionario) {
        connect();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        String sql = "UPDATE funcionario SET " +
                "nome = ?, codigo = ?, retirado =?, finalizado = ?, pago =? " +
                "WHERE id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;

            pst.setLong(++i, funcionario.getId());

            pst.execute();

            System.out.println("Executado com sucesso");

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Funcionario funcionario) {
        connect();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

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
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
