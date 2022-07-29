package mff.administracion.dao;

import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Imagen;

public interface IImagenDAO extends CrudRepository<Imagen, Integer> {

}
