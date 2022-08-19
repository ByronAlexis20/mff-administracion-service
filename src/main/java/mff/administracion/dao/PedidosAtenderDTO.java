package mff.administracion.dao;

public class PedidosAtenderDTO {

	private Integer idProducto;
	private String nombreProducto;
	private Double precio;
	private Integer idCategoria;
	private String categoria;
	private Integer cantidadPedida;
	private byte[] imagen;

	public PedidosAtenderDTO() {
		super();
	}

	public PedidosAtenderDTO(Integer idProducto, String nombreProducto, Double precio, Integer idCategoria,
			String categoria, Integer cantidadPedida) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.idCategoria = idCategoria;
		this.categoria = categoria;
		this.cantidadPedida = cantidadPedida;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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

	public Integer getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(Integer cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}
