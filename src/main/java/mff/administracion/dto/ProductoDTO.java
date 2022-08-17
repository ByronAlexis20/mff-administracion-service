package mff.administracion.dto;

import java.io.Serializable;
import java.util.List;

public class ProductoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCategoria;
	private String categoria;
	private List<ProductosDTO> productos;

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(Integer idCategoria, String categoria, List<ProductosDTO> productos) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
		this.productos = productos;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<ProductosDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosDTO> productos) {
		this.productos = productos;
	}

	
}
