package cdg.inatel.br.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
    private Long id;
    private String nomecliente;
    private String codigo;
    private Boolean finalizado;
    private Boolean retirado;
    private Boolean pago;

}
