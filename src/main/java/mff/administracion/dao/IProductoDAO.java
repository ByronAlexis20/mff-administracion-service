package mff.administracion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Integer> {

	@Query("select p "
			+ "from Producto p where p.idProducto = ?1 ")
	public Producto buscarProductoPorId(Integer idProducto);
	
	@Query("select p "
			+ "from Producto p where p.estado = 'A' order by p.categoria.idCategoria")
	public List<Producto> buscarProductosActivosDTO();
	
	@Query("select p "
			+ "from Producto p where p.estado = 'A' and p.categoria.idCategoria = ?1 ")
	public List<Producto> buscarProductosActivosDTOPorCategoria(Integer idCategoria);
	
	@Query("select p "
			+ "from Producto p where p.estado = 'A' and p.codigo = ?1 ")
	public List<Producto> buscarPorCodigo(String codigo);
	
}
