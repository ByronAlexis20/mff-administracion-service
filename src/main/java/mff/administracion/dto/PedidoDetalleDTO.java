package mff.administracion.dto;

public class PedidoDetalleDTO {
	private Integer idPedidoDetalle;
	private String producto;
	private Integer cantidad;
	private Double precioUnitario;
	private Double total;
	private byte[] imagen;

	public PedidoDetalleDTO() {
		super();
	}

	public PedidoDetalleDTO(Integer idPedidoDetalle, String producto, Integer cantidad, Double precioUnitario,
			Double total, byte[] imagen) {
		super();
		this.idPedidoDetalle = idPedidoDetalle;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.total = total;
		this.imagen = imagen;
	}

	public Integer getIdPedidoDetalle() {
		return idPedidoDetalle;
	}

	public void setIdPedidoDetalle(Integer idPedidoDetalle) {
		this.idPedidoDetalle = idPedidoDetalle;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}
