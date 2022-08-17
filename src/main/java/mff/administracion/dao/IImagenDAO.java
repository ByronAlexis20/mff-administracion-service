package mff.administracion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Imagen;

public interface IImagenDAO extends CrudRepository<Imagen, Integer> {
	
	@Query("Select i from Imagen i where i.producto.idProducto = ?1 ")
	public List<Imagen> buscarPorIdProducto(Integer idProducto);
	
}
