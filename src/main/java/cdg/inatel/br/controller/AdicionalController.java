package cdg.inatel.br.controller;

import cdg.inatel.br.dao.AdicionalDao;
import cdg.inatel.br.model.Adicional;

import java.util.ArrayList;

public class AdicionalController {
    public ArrayList<Adicional> getByOrdemId(Long ordem_id){
        return new AdicionalDao().getByOrdemId(ordem_id);
    }
}
