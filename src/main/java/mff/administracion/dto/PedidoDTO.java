package mff.administracion.dto;

public class PedidoDTO {

	private Integer idPedido;
	private String nombreCliente;
	private String fecha;
	private String cantidad;
	private Double total;
	private String producto;
	private Double precioUnitario;
	private Integer catidadProducto;
	private Double totalProducto;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getCatidadProducto() {
		return catidadProducto;
	}

	public void setCatidadProducto(Integer catidadProducto) {
		this.catidadProducto = catidadProducto;
	}

	public Double getTotalProducto() {
		return totalProducto;
	}

	public void setTotalProducto(Double totalProducto) {
		this.totalProducto = totalProducto;
	}

}
