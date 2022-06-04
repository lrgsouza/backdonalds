package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Long id;
    private Long funcionario_id;
    private String login;
    private String senha;
    private Funcionario funcionario;
}
