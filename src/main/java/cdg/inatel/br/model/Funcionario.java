package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Funcionario {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
}