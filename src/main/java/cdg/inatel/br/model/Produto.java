package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Produto {
    private Long id;
    private String nome;
    private Double valor;
    private String descricao;
    private int produto_tipo_id;

    private Produto(Long id, String nome, Double valor, String descricao, int produto_tipo_id) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.produto_tipo_id = produto_tipo_id;
    }

    public static Produto getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String nome = result.getString("nome");
        Double valor = result.getDouble("valor");
        String descricao = result.getString("descricao");
        int produto_tipo_id = result.getInt("produto_tipo_id");
        return new Produto(id, nome, valor, descricao, produto_tipo_id);
    }
}
