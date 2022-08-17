package mff.administracion.service;

import java.util.List;

import mff.administracion.dto.ProductosDTO;
import mff.administracion.entity.Producto;

public interface IProductoService {

	public List<Producto> buscarProductosActivosDTO();
	
	public List<Producto> buscarProductosActivosDTOPorCategoria(Integer idCategoria);
	
	public Producto grabarProducto(ProductosDTO pr);
}