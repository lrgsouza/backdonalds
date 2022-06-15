package cdg.inatel.br.dao;

import cdg.inatel.br.model.Produto;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDao extends Database implements BaseDao<Produto> {

    @Override
    public Produto get(Long id) {
        connect();

        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ?;";

        try {
            int i = 0;
            pst = connection.prepareStatement(sql);
            pst.setLong(++i, id);
            result = pst.executeQuery();

            while (result.next()) {
                produto = Produto.getByResult(result);
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return produto;
    }

    @Override
    public ArrayList<Produto> getAll() {
        connect();

        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto;";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Produto produto = Produto.getByResult(result);

                produtos.add(produto);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            closeAllSql();
        }
        return produtos;
    }

    @Override
    public void save(Produto produto) {
        connect();

        String sql = "INSERT INTO produto VALUES" +
                "(?, ?, ?, ?);";

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
            closeAllSql();
        }
    }

    @Override
    public void update(Produto produto) {
        connect();

        String sql = "UPDATE produto SET " +
                "nome = ?, valor =?, descricao =? " +
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
            closeAllSql();
        }
    }

    @Override
    public void delete(Produto produto) {
        connect();

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
            closeAllSql();
        }
    }
}
