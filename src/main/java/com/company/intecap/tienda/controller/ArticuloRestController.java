package com.company.intecap.tienda.controller;

import com.company.intecap.tienda.model.Articulo;
import com.company.intecap.tienda.response.ArticuloResponseRest;
import com.company.intecap.tienda.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class ArticuloRestController {

    @Autowired
    private IArticuloService service;

    @GetMapping("/libros")
    public ResponseEntity<ArticuloResponseRest> buscarArticulo(){
        return service.buscarArticulo();
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(@PathVariable Long id){
        return service.buscarArticuloPorId(id);
    }

    @PostMapping("/libros")
    public ResponseEntity<ArticuloResponseRest> crear(@RequestBody Articulo articulo){
        return service.crear(articulo);
    }

    @PutMapping("libros/{id}")
    public ResponseEntity<ArticuloResponseRest> actualizar(@RequestBody Articulo articulo, @PathVariable Long id){
        return service.actualizar(articulo, id);
    }

    @DeleteMapping("libros/{id}")
    public ResponseEntity<ArticuloResponseRest> eliminar(@PathVariable Long id){
        return service.eliminar(id);
    }

}
