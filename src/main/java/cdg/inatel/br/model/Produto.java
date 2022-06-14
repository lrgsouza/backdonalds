package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    private Long id;
    private String nome;
    private Double valor;
    private String descricao;

    public static Produto getByResult(ResultSet result) throws SQLException {
        Produto produto = new Produto();
        produto.setId(result.getLong("id"));
        produto.setNome(result.getString("nome"));
        produto.setValor(result.getDouble("valor"));
        produto.setDescricao(result.getString("descricao"));
        return produto;
    }
}
