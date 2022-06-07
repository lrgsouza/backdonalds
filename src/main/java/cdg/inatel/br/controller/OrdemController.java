package cdg.inatel.br.controller;

import cdg.inatel.br.dao.AdicionalDao;
import cdg.inatel.br.dao.OrdemDao;
import cdg.inatel.br.model.Adicional;
import cdg.inatel.br.model.Ordem;
import cdg.inatel.br.view.MainView;

import java.util.ArrayList;

public class OrdemController {
    public void realizarOrdens(Long pedido_id){
        var ordens = new MainView().getOrdens(pedido_id);

        for (Ordem ordem:
                ordens) {
            new OrdemDao().save(ordem);
        }
    }

    public ArrayList<Ordem> getByPedidoId(Long pedido_id){
        var ordens = (ArrayList<Ordem>) new OrdemDao().getByPedidoId(pedido_id);

        for (Ordem ordem:
             ordens) {
            ArrayList<Adicional> adicionais = new AdicionalController().getByOrdemId(ordem.getId());
            ordem.setAdicionais(adicionais);
        }
        return ordens;
    }

    public ArrayList<Adicional> getAdicionaisByOrdemId(Long ordem_id) {
        return (ArrayList<Adicional>) new AdicionalDao().getByOrdemId(ordem_id);
    }
}
