package com.company.intecap.tienda.service;

import com.company.intecap.tienda.model.Articulo;
import com.company.intecap.tienda.model.Fabricante;
import com.company.intecap.tienda.response.ArticuloResponseRest;
import com.company.intecap.tienda.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {
    public ResponseEntity<ArticuloResponseRest> buscarArticulo();//Metodo traer todas la categorias

    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id);//Metodo buscar categoria por id

    public ResponseEntity<ArticuloResponseRest> crear(Articulo articulo);//Metodo para crear categoria

    public ResponseEntity<ArticuloResponseRest> actualizar(Articulo articulo, Long id);

    public ResponseEntity<ArticuloResponseRest> eliminar(Long id);
}
