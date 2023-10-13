package com.company.intecap.tienda.controller;


import com.company.intecap.tienda.model.Fabricante;
import com.company.intecap.tienda.response.FabricanteResponseRest;
import com.company.intecap.tienda.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FabricanteRestController {

    @Autowired//inyecccion de dependencias
    private IFabricanteService service;

    @GetMapping("/fabricantes")//Manejar solicitudes HTTP Get, Metodo traer las categorias.
    public ResponseEntity<FabricanteResponseRest> buscarCategorias(){
        return service.buscarFabricante();
    }

    @GetMapping("/fabricantes/{id}") //Buscar categoria por medio del parametro "id"
    public ResponseEntity<FabricanteResponseRest> buscarPorId(@PathVariable Long id){
        //La anotacion @PathVariable es para indicar que se va a utilizar un parametro de tipo variable.
        return service.buscarPorId(id);
    }

    @PostMapping("/fabricantes")//Metodo para Agregar un nuevo dato.
    public ResponseEntity<FabricanteResponseRest> crear(@RequestBody Fabricante request){
        return service.crear(request);
    }

    @PutMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> actualizar(@RequestBody Fabricante request, @PathVariable Long id){
        return service.actualizar(request, id);
    }

    @DeleteMapping("fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> eliminar(@PathVariable Long id){
        return service.eliminar(id);
    }

}
