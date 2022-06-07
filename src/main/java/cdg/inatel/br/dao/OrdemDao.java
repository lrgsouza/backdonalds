package cdg.inatel.br.dao;

import cdg.inatel.br.model.Adicional;
import cdg.inatel.br.model.Ordem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemDao extends Database implements BaseDao<Ordem> {

    @Override
    public Ordem get(Long id) {
        connect();

        Ordem ordem = new Ordem();
        String sql = "SELECT * FROM ordem WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                ordem.setId(result.getLong("id"));
                ordem.setPedido_id(result.getLong("pedido_id"));
                ordem.setQuantidade(result.getInt("quantidade"));
                ordem.setProduto_id(result.getLong("produto_id"));
                ordem.setObservacoes(result.getString("observacoes"));
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return ordem;
    }

    @Override
    public ArrayList<Ordem> getAll() {
        connect();

        ArrayList<Ordem> ordens = new ArrayList<>();
        String sql = "SELECT * FROM ordem;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Ordem ordem = new Ordem();

                ordem.setId(result.getLong("id"));
                ordem.setPedido_id(result.getLong("pedido_id"));
                ordem.setQuantidade(result.getInt("quantidade"));
                ordem.setProduto_id(result.getLong("produto_id"));
                ordem.setObservacoes(result.getString("observacoes"));

                ordens.add(ordem);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return ordens;
    }

    public ArrayList<Ordem> getByPedidoId(Long pedido_id) {
        connect();

        ArrayList<Ordem> ordens = new ArrayList<>();
        String sql = "SELECT * FROM ordem " +
                "WHERE pedido_id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, pedido_id);

            result = pst.executeQuery();
            while(result.next()){
                Ordem ordem = new Ordem();
                ordem.setId(result.getLong("id"));
                ordem.setPedido_id(result.getLong("pedido_id"));
                ordem.setQuantidade(result.getInt("quantidade"));
                ordem.setProduto_id(result.getLong("produto_id"));
                ordem.setObservacoes(result.getString("observacoes"));

                ordens.add(ordem);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return ordens;
    }

    @Override
    public void save(Ordem ordem) {
        connect();

        String sql = "INSERT INTO ordem (pedido_id, quantidade, produto_id, observacoes) VALUES" +
                "(?, ?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, ordem.getPedido_id());
            pst.setInt(++i, ordem.getQuantidade());
            pst.setLong(++i, ordem.getProduto_id());
            pst.setString(++i, ordem.getObservacoes());

            pst.execute();

            System.out.println("Ordem adicionada com sucesso.");

            sql = "SELECT LAST_INSERT_ID() AS id;";

            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()){
                ordem.setId(result.getLong("id"));
            }
            //criando adicionais da ordem
            for (Adicional a:
                 ordem.getAdicionais()) {
                saveOrdemAdicional(ordem.getId(), a.getId());
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
    }

    public void saveOrdemAdicional(Long ordem_id, Long adicional_id) {
        connect();
        String sql = "INSERT INTO ordem_has_adicional (ordem_id, adicional_id) VALUES" +
                "(?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, ordem_id);
            pst.setLong(++i, adicional_id);

            pst.execute();

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
    }

    @Override
    public void update(Ordem ordem) {
        connect();

        String sql = "UPDATE ordem SET " +
                "pedido_id = ?, quantidade = ?, produto_id =?, observacoes = ? " +
                "WHERE idordem = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, ordem.getPedido_id());
            pst.setInt(++i, ordem.getQuantidade());
            pst.setLong(++i, ordem.getProduto_id());
            pst.setString(++i, ordem.getObservacoes());
            pst.setLong(++i, ordem.getId());

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
    public void delete(Ordem ordem) {
        connect();

        String sql = "DELETE FROM ordem WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, ordem.getId());

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
