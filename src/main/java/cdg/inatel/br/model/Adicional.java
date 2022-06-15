package cdg.inatel.br.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class Adicional {
    private Long id;
    private String nome;
    private Double valor;

    private Adicional(Long id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public static Adicional getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String nome = result.getString("nome");
        Double valor = result.getDouble("valor");
        return new Adicional(id, nome, valor);
    }
}
