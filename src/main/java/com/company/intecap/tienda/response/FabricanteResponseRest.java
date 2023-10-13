package com.company.intecap.tienda.response;

public class FabricanteResponseRest extends ResponseRest{
    private FabricanteResponse fabricanteResponse = new FabricanteResponse();

    public FabricanteResponse getFabricanteResponse(){

        return fabricanteResponse;

    }

    public void setFabricanteResponse(FabricanteResponse fabricanteResponse){
        this.fabricanteResponse = fabricanteResponse;
    }
}
