package mff.administracion.service;

import java.util.List;

import mff.administracion.dao.ProductosDTO;
import mff.administracion.entity.Producto;

public interface IProductoService {

	public List<Producto> buscarProductosActivosDTO();
	
	public Producto grabarProducto(ProductosDTO pr);
}