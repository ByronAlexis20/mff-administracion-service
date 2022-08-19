package mff.administracion.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.administracion.dao.IProductoDAO;
import mff.administracion.dto.ProductosDTO;
import mff.administracion.entity.Categoria;
import mff.administracion.entity.Producto;
import mff.administracion.service.IProductoService;

@Service
public class ProductoImpl implements IProductoService {

	@Autowired
	private IProductoDAO productoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> buscarProductosActivosDTO() {
		return this.productoDAO.buscarProductosActivosDTO();
	}

	@Override
	@Transactional
	public Producto grabarProducto(ProductosDTO pr) {
		Producto prod = new Producto();
		Categoria cat = new Categoria();
		cat.setIdCategoria(pr.getIdCategoria());
		prod.setCategoria(cat);
		prod.setEstado(pr.getEstado());
		prod.setFechacCreacion(new Date());
		prod.setPrecio(pr.getPrecio());
		prod.setNombre(pr.getNombreProducto());
		prod.setStock(pr.getStock());
		prod.setIdProducto(pr.getIdProducto());
		prod.setCodigo(pr.getCodigo());
		return this.productoDAO.save(prod);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> buscarProductosActivosDTOPorCategoria(Integer idCategoria) {
		return this.productoDAO.buscarProductosActivosDTOPorCategoria(idCategoria);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean verificarCodigo(ProductosDTO pr) {
		boolean bandera = false;
		List<Producto> lista = this.productoDAO.buscarPorCodigo(pr.getCodigo());
		if(lista.size() > 0) {
			if(pr.getIdProducto() != null) {
				for(Producto prod : lista) {
					if(prod.getIdProducto() != pr.getIdProducto()) {
						bandera = true;
					}
				}
			}else {
				bandera = false;
			}
		}else {
			bandera = false;
		}
		return bandera;
	}

}
