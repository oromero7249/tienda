package com.company.intecap.tienda.service;

import com.company.intecap.tienda.model.Fabricante;
import com.company.intecap.tienda.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {

    public ResponseEntity<FabricanteResponseRest> buscarFabricante();//Metodo traer todas la categorias

    public ResponseEntity<FabricanteResponseRest> buscarPorId(Long id);//Metodo buscar categoria por id

    public ResponseEntity<FabricanteResponseRest> crear(Fabricante fabricante);//Metodo para crear categoria

    public ResponseEntity<FabricanteResponseRest> actualizar(Fabricante fabricante, Long id);

    public ResponseEntity<FabricanteResponseRest> eliminar(Long id);
}
