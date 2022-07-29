package mff.administracion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.dto.ProductoDTO;
import mff.administracion.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Integer> {

	@Query("select p "
			+ "from Producto p where p.estado = 'A' ")
	public List<Producto> buscarProductosActivosDTO();
	
}