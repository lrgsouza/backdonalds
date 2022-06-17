package cdg.inatel.br.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Adicional {
    private Long id;
    private String nome;
    private Double valor;

    private Adicional(Long id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    private Adicional(Long id){
        this.id = id;
    }

    public static Adicional inputId(Long id){
        return new Adicional(id);
    }

    public static Adicional getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String nome = result.getString("nome");
        Double valor = result.getDouble("valor");
        return new Adicional(id, nome, valor);
    }
}
