package cdg.inatel.br.dao;

import cdg.inatel.br.model.Adicional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdicionalDao extends Database implements BaseDao<Adicional> {

    @Override
    public Adicional get(Long id) {
        connect();

        Adicional adicional = new Adicional();
        String sql = "SELECT * FROM adicional WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                adicional.setId(result.getLong("id"));
                adicional.setNome(result.getString("nome"));
                adicional.setValor(result.getDouble("valor"));
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
        return adicional;
    }

    @Override
    public List<Adicional> getAll() {
        connect();

        ArrayList<Adicional> adicionais = new ArrayList<>();
        String sql = "SELECT * FROM adicional;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Adicional adicional = new Adicional();
                adicional.setId(result.getLong("id"));
                adicional.setNome(result.getString("nome"));
                adicional.setValor(result.getDouble("valor"));

                adicionais.add(adicional);
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
        return adicionais;
    }

    @Override
    public void save(Adicional adicional) {
        connect();

        String sql = "INSERT INTO adicional VALUES " +
                "(?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, adicional.getId());
            pst.setString(++i, adicional.getNome());
            pst.setDouble(++i, adicional.getValor());

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
    public void update(Adicional adicional) {
        connect();

        String sql = "UPDATE adicional SET " +
                "nome = ?, valor =? " +
                "WHERE id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setString(++i, adicional.getNome());
            pst.setDouble(++i, adicional.getValor());
            pst.setLong(++i, adicional.getId());

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
    public void delete(Adicional adicional) {
        connect();

        String sql = "DELETE FROM adicional WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, adicional.getId());

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
