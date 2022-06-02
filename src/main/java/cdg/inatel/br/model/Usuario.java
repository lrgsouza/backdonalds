package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Long id;
    private String nome;
    private Integer permissao;
    private String login;
    private String senha;
}
