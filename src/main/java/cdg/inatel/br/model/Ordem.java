package cdg.inatel.br.model;

import cdg.inatel.br.dao.AdicionalDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ordem {
    private Long id;
    private Long pedido_id;
    private Integer quantidade;
    private Long produto_id;
    private String observacoes;
    private ArrayList<Adicional> adicionais = new ArrayList<>();
}
