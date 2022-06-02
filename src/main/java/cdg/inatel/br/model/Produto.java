package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    private Long id;
    private String nome;
    private Double valor;
    private String descricao;

}
