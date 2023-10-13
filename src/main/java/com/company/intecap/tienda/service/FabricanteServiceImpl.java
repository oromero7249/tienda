package com.company.intecap.tienda.service;

import com.company.intecap.tienda.dao.IFabricanteDao;
import com.company.intecap.tienda.model.Fabricante;
import com.company.intecap.tienda.response.FabricanteResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FabricanteServiceImpl implements IFabricanteService{
    private static final Logger log = LoggerFactory.getLogger(FabricanteServiceImpl.class);
    //Log para mostrar en consola.
    @Autowired
    private IFabricanteDao fabricanteDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricante() { //Metodo buscar

        log.info("inicio metodo buscar()");
        FabricanteResponseRest response = new FabricanteResponseRest(); //instancia de la calse Categoira ResoponseRest

        try {
            List<Fabricante> fabricante = (List<Fabricante>) fabricanteDao.findAll();
            response.getFabricanteResponse().setFabricantes(fabricante);

            response.setMetadata("Respuesta ok","200!","Respuesta exitosa");

        }catch (Exception e){
            response.setMetadata("Respuesta no ok", "500", "Error al consultar");
            log.error("Error al consultar {}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarPorId(Long id) {//Metodo buscar por id
        log.info("inicio metodo buscarPorId()");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();//Lista categorias agregadas al Arraylist
        try {
            Optional<Fabricante> categoria = fabricanteDao.findById(id);//buscar una categoria por id
            if(categoria.isPresent()){//si la categoria existe, true.
                list.add(categoria.get());//Agregar la categoria a la lista
                response.getFabricanteResponse().setFabricantes(list);//Envia la lista
            }else{
                //Mensaje de error
                log.error("Error al consultar {}", id);
                response.setMetadata("Respues no ok", "404", "no encontrada");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al conslutar  {}",e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar ");
            return new ResponseEntity<FabricanteResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok","200", "Respuesta exitosa");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crear(Fabricante categoria) {
        log.info("inicio metodo crear() Fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();//Lista de categorias
        try {
            Fabricante categoriaGuardada = fabricanteDao.save(categoria);//guardar en la base de datos
            list.add(categoriaGuardada);//Agregarla a la lista
            if(categoriaGuardada != null){//si la categoriaGuardada no esta vacia
                list.add(categoriaGuardada);//agrega la categoria a la lsita
                response.getFabricanteResponse().setFabricantes(list);//setea la lista categorias
                response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
            }else{
                log.error("Error al crear categoria {}", categoria.toString());
                response.setMetadata("Respuesta no ok", "400", "Categoria no creada");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear categoria {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al crear categoria");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizar(Fabricante fabricante, Long id) {
        log.info("inicio metodo actualizar() Fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try {
            Optional<Fabricante> fabricanteBuscada = fabricanteDao.findById(id);//buscar la categoria por id
            if(fabricanteBuscada.isPresent()){//Si encuentra la categoria buscada por el id
                fabricanteBuscada.get().setNombre(fabricante.getNombre());//setea el nombre
                Fabricante fabricanteActualizada = fabricanteDao.save(fabricanteBuscada.get());//guarda la categoria
                if(fabricanteActualizada != null){//Si no esta vacia, es por que se guardo
                    list.add(fabricanteActualizada);//agregar la categoria a la lissta
                    response.getFabricanteResponse().setFabricantes(list);//setea la lista de categorias
                }else{
                    log.error("Error al actualizar  {}", fabricante.toString());
                    response.setMetadata("Respuesta no ok", "400", "no actualizada");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al actualizar  {}", fabricante.toString());
                response.setMetadata("Respuesta no ok", "400", " no actualizada");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al actualizar categoria{}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500","Error al actualizar categoria");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok", "200", "Categoria Actualizada");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminar(Long id) {
        log.info("inicio metodo eliminar() ");
        FabricanteResponseRest response = new FabricanteResponseRest();//instancia a CategoriaResponseRest
        try {
            Optional<Fabricante> fabricante = fabricanteDao.findById(id);//busca una categoria por id
            if(fabricante.isPresent()){//si la categoria existe
                fabricanteDao.delete(fabricante.get());//elimina la categoria por id
            }else{
                log.error("Error al eliminar  {}",id);
                response.setMetadata("Respuesta no ok", "400", " no eliminada");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar  {}", e.getMessage());
            response.setMetadata("Respueta no ok", "500", "Error al eliminar ");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok","200", " Eliminada");
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }
}
