package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ordem {
    private Long id;
    private Long pedido_id;
    private Integer quantidade;
    private Long produto_id;
    private String observacoes;


}
