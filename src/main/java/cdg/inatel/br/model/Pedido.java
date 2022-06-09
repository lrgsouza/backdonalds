package cdg.inatel.br.model;

import cdg.inatel.br.dao.PedidoDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Scanner;

@NoArgsConstructor
@Data
public class Pedido {
    private Long id;
    private String nome;
    private Boolean finalizado;
    private Boolean retirado;
    private Boolean pago;

    private Pedido(String nome){
        id = null;
        this.nome = nome;
        finalizado = false;
        retirado = false;
        pago = false;
    }
    public static Pedido getUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Nome do cliente: ");
        String nome = input.nextLine();
        Pedido pedido = new Pedido(nome);
        PedidoDao.saveReturning(pedido);
        return pedido;
    }

}
