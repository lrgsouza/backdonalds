package cdg.inatel.br.model;

import cdg.inatel.br.dao.PedidoDao;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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

    private Pedido(Long id, String nome, Boolean finalizado, Boolean retirado, Boolean pago) {
        this.id = id;
        this.nome = nome;
        this.finalizado = finalizado;
        this.retirado = retirado;
        this.pago = pago;
    }

    public static Pedido getUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Nome do cliente: ");
        String nome = input.nextLine();
        Pedido pedido = new Pedido(nome);
        PedidoDao.saveReturning(pedido);
        return pedido;
    }

    public static Pedido getByResult(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String nome = result.getString("nome");
        Boolean finalizado = result.getBoolean("finalizado");
        Boolean retirado = result.getBoolean("retirado");
        Boolean pago = result.getBoolean("pago");
        return new Pedido(id, nome, finalizado, retirado, pago);
    }

}
