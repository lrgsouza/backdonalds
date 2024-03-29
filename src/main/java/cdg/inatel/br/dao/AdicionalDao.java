package cdg.inatel.br.dao;

import cdg.inatel.br.model.Adicional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdicionalDao extends Database implements BaseDao<Adicional> {

    @Override
    public Adicional get(Long id) {
        connect();

        Adicional adicional = null;
        String sql = "SELECT * FROM adicional WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                adicional = Adicional.getByResult(result);
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return adicional;
    }

    @Override
    public ArrayList<Adicional> getAll() {
        connect();

        ArrayList<Adicional> adicionais = new ArrayList<>();
        String sql = "SELECT * FROM adicional;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Adicional adicional = Adicional.getByResult(result);

                adicionais.add(adicional);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return adicionais;
    }

    public ArrayList<Adicional> getByOrdemId(Long ordem_id) {
        connect();

        ArrayList<Adicional> adicionais = new ArrayList<>();
        String sql = "SELECT * FROM adicional a " +
                "INNER JOIN ordem_has_adicional oa " +
                "   ON oa.adicional_id = a.id " +
                "INNER JOIN ordem o " +
                "   ON o.id = oa.ordem_id " +
                "WHERE o.id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, ordem_id);

            result = pst.executeQuery();
            while(result.next()){
                Adicional adicional = Adicional.getByResult(result);

                adicionais.add(adicional);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
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
            closeAllSql();
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
            closeAllSql();
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
            closeAllSql();
        }
    }
}
