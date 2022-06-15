package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Funcionario {
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private Funcionario(Long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public static Funcionario getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String nome = result.getString("nome");
        String email = result.getString("email");
        String telefone = result.getString("telefone");
        return new Funcionario(id, nome, email, telefone);
    }
}