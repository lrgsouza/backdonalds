package cdg.inatel.br.model;

import cdg.inatel.br.dao.AdicionalDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Ordem {
    private Long id;
    private Long pedido_id;
    private Integer quantidade;
    private Long produto_id;
    private String observacoes;
    private ArrayList<Adicional> adicionais = new ArrayList<>();

    private Ordem(Long id, Long pedido_id, Integer quantidade, Long produto_id, String observacoes) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.quantidade = quantidade;
        this.produto_id = produto_id;
        this.observacoes = observacoes;
    }

    public static Ordem getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        Long pedido_id = result.getLong("pedido_id");
        Integer quantidade = result.getInt("quantidade");
        Long produto_id = result.getLong("produto_id");
        String observacoes = result.getString("observacoes");
        return new Ordem(id, pedido_id, quantidade, produto_id, observacoes);
    }
}
