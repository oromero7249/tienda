package com.company.intecap.tienda.response;

import com.company.intecap.tienda.model.Fabricante;

import java.util.List;

public class FabricanteResponse {

    private List<Fabricante> fabricantes;

    public List<Fabricante> getFabricantes(){
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes){
        this.fabricantes = fabricantes;
    }

}
