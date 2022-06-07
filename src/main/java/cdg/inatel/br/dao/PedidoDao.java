package cdg.inatel.br.dao;

import cdg.inatel.br.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao extends Database implements BaseDao<Pedido> {

    @Override
    public Pedido get(Long id) {
        connect();

        Pedido pedido = new Pedido();
        String sql = "SELECT * FROM pedido WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                pedido.setId(result.getLong("id"));
                pedido.setNome(result.getString("nome"));
                pedido.setFinalizado(result.getBoolean("finalizado"));
                pedido.setRetirado(result.getBoolean("retirado"));
                pedido.setPago(result.getBoolean("pago"));
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return pedido;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        connect();

        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Pedido pedido = new Pedido();
                pedido.setId(result.getLong("id"));
                pedido.setNome(result.getString("nome"));
                pedido.setFinalizado(result.getBoolean("finalizado"));
                pedido.setRetirado(result.getBoolean("retirado"));
                pedido.setPago(result.getBoolean("pago"));

                pedidos.add(pedido);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return pedidos;
    }


    public Long saveReturning(Pedido pedido) {
        connect();

        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sql = "INSERT INTO pedido (nome) VALUES" +
                        "(?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setString(++i, pedido.getNome());
            pst.execute();

            sql = "SELECT LAST_INSERT_ID() AS id;";

            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()){
                pedido.setId(result.getLong("id"));
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return pedido.getId();
    }


    @Override
    public void save(Pedido pedido) {
        connect();

        String sql = "INSERT INTO pedido (nome) VALUES" +
                "(?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setString(++i, pedido.getNome());
            pst.execute();

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
    }

    @Override
    public void update(Pedido pedido) {
        connect();

        String sql = "UPDATE pedido SET " +
                "nome = ?, codigo = ?, retirado =?, finalizado = ?, pago =? " +
                "WHERE id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setString(++i, pedido.getNome());
            pst.setBoolean(++i, pedido.getRetirado());
            pst.setBoolean(++i, pedido.getFinalizado());
            pst.setBoolean(++i, pedido.getPago());
            pst.setLong(++i, pedido.getId());

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
    public void delete(Pedido pedido) {
        connect();

        String sql = "DELETE FROM pedido WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, pedido.getId());

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
