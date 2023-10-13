package com.company.intecap.tienda.service;


import com.company.intecap.tienda.dao.IArticuloDao;
import com.company.intecap.tienda.model.Articulo;
import com.company.intecap.tienda.response.ArticuloResponseRest;
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
public class ArticuloServiceImpl implements IArticuloService {

    private static final Logger log = LoggerFactory.getLogger(ArticuloServiceImpl.class);

    //Log para mostrar en consola.
    @Autowired
    private IArticuloDao articuloDao;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticulo() {
        log.info("Inicio metodo ()");

        ArticuloResponseRest response = new ArticuloResponseRest();

        try{
            List<Articulo> articulos = (List<Articulo>) articuloDao.findAll();
            response.getArticuloResponse().setArticulos(articulos);

            response.setMetadata("respuesta ok","200","Respuesta exitosa");

        }catch (Exception e){
            response.setMetadata("Respueta no ok","500","Error al consultar ");
            log.error("Error al consultar  {}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id) {
        log.info("Inicio metodo buscarArticuloPorId()");
        ArticuloResponseRest response = new ArticuloResponseRest();

        List<Articulo> list = new ArrayList<>();
        try {
            Optional<Articulo> articulo = articuloDao.findById(id);

            if(articulo.isPresent()){
                list.add(articulo.get());
                response.getArticuloResponse().setArticulos(list);
            }else{
                log.error("Error al consultar : {}", id);
                response.setMetadata("Respuesta no ok","404"," no encontrado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al consultar : {}", e.getMessage());
            response.setMetadata("Respuesta no ok","500","Error al consultar ");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok","200","Respuesta exitosa");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional//Cuando es guardar, solo es transactional
    public ResponseEntity<ArticuloResponseRest> crear(Articulo articulo) {
        log.info("Inicio metodo ()");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();

        try {

            Articulo articuloGuardado = articuloDao.save(articulo);

            if(articuloGuardado != null){
                list.add(articuloGuardado);
                response.getArticuloResponse().setArticulos(list);
            }else{
                log.error("Error al guardar : {}", articulo.toString());
                response.setMetadata("Respuesta no ok","400","Error al guardar ");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("Error al guardar : {}",e.getMessage());
            response.setMetadata("Respuesta no ok","500","Error al  ");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok","200","Respueta exitosa");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> actualizar(Articulo articulo, Long id) {
        log.info("Inicio metodo actualizar()");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();

        try {
            Optional<Articulo> ArticuloBuscado = articuloDao.findById(id);

            if(ArticuloBuscado.isPresent()){
                ArticuloBuscado.get().setNombre(articulo.getNombre());
                ArticuloBuscado.get().setPrecio(articulo.getPrecio());
                ArticuloBuscado.get().setFabricante(articulo.getFabricante());

                Articulo libroAtualizado = articuloDao.save(ArticuloBuscado.get());

                if(libroAtualizado != null){
                    list.add(libroAtualizado);
                    response.getArticuloResponse().setArticulos(list);
                }else{
                    log.error("Error al actualizar : {}", articulo.toString());
                    response.setMetadata("Respuesta no ok"," 400","Error al actualizar ");
                    return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            log.error("Error al actualizar : {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500","Error al actualizar ");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Respuesta ok","200","Respuesta exitosa");
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar()");

        ArticuloResponseRest response = new ArticuloResponseRest();
        List<Articulo> list = new ArrayList<>();
        try {
            Optional<Articulo> ArticuloBuscado = articuloDao.findById(id);
            if(ArticuloBuscado.isPresent()){
                articuloDao.deleteById(id);
                response.setMetadata("Respuesta ok","200","eliminado");
            }else{
                log.error("Error al eliminar libro: {}",id);
                response.setMetadata("Respuesta no ok","404","no encontrado");
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar libro: {}", e.getMessage());
            response.setMetadata("Respuesta no ok"," 500","Error al eliminar");
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK);

    }

}
