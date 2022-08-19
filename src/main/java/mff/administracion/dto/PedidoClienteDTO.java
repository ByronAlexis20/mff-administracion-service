package mff.administracion.dto;

public class PedidoClienteDTO {
	private Integer idProducto;
	private Integer cantidad;
	
	public PedidoClienteDTO() {
		super();
	}

	public PedidoClienteDTO(Integer idProducto) {
		super();
		this.idProducto = idProducto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
