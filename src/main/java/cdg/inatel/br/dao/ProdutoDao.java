package cdg.inatel.br.dao;

import cdg.inatel.br.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends Database implements BaseDao<Produto> {

    @Override
    public Produto get(Long id) {
        connect();

        Produto produto = new Produto();
        String sql = "SELECT * FROM produto WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                produto.setId(result.getLong("id"));
                produto.setNome(result.getString("nome"));
                produto.setValor(result.getDouble("valor"));
                produto.setDescricao(result.getString("descricao"));

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
        return produto;
    }

    @Override
    public List<Produto> getAll() {
        connect();

        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Produto produto = new Produto();
                produto.setId(result.getLong("id"));
                produto.setNome(result.getString("nome"));
                produto.setValor(result.getDouble("valor"));
                produto.setDescricao(result.getString("descricao"));

                produtos.add(produto);
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
        return produtos;
    }

    @Override
    public void save(Produto produto) {
        connect();

        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "INSERT INTO produto VALUES" +
                "(?, ?, ?, ?, ?, ?);";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setLong(++i, produto.getId());
            pst.setString(++i, produto.getNome());
            pst.setDouble(++i, produto.getValor());
            pst.setString(++i, produto.getDescricao());

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
    public void update(Produto produto) {
        connect();

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "UPDATE produto SET " +
                "nome = ?, codigo = ?, retirado =?, finalizado = ?, pago =? " +
                "WHERE id = ?;";

        try {
            pst = connection.prepareStatement(sql);
            int i = 0;
            pst.setString(++i, produto.getNome());
            pst.setDouble(++i, produto.getValor());
            pst.setString(++i, produto.getDescricao());
            pst.setLong(++i, produto.getId());

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
    public void delete(Produto produto) {
        connect();

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, produto.getId());

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
