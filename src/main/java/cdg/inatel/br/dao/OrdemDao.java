package cdg.inatel.br.dao;

import cdg.inatel.br.model.Ordem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemDao extends Database implements BaseDao<Ordem> {

    @Override
    public Ordem get(Long id) {
        connect();

        Ordem ordem = new Ordem();
        String sql = "SELECT * FROM ordem WHERE idordem = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {

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
        return ordem;
    }

    @Override
    public List<Ordem> getAll() {
        connect();

        ArrayList<Ordem> ordens = new ArrayList<>();
        String sql = "SELECT * FROM ordem;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Ordem ordem = new Ordem();


                ordens.add(ordem);
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
        return ordens;
    }

    @Override
    public void save(Ordem ordem) {
        connect();

        ArrayList<Ordem> ordens = new ArrayList<>();
        String sql = "INSERT INTO ordem VALUES" +
                "(?, ?, ?, ?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;


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
    public void update(Ordem ordem) {
        connect();

        ArrayList<Ordem> ordens = new ArrayList<>();

        String sql = "UPDATE ordem SET " +
                "nomecliente = ?, codigo = ?, retirado =?, finalizado = ?, pago =? " +
                "WHERE idordem = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;


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
    public void delete(Ordem ordem) {
        connect();

        ArrayList<Ordem> ordens = new ArrayList<>();

        String sql = "DELETE FROM ordem WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);


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
