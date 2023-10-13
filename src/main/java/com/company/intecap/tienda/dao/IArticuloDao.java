package com.company.intecap.tienda.dao;

import com.company.intecap.tienda.model.Articulo;
import org.springframework.data.repository.CrudRepository;

public interface IArticuloDao extends CrudRepository<Articulo, Long> {

}
