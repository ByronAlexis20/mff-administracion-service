package mff.administracion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pedido_detalle")
public class PedidoDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido_detalle", columnDefinition = "int4")
	private Integer idPedidoDetalle;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "precio_unitario")
	private Double precioUnitario;

	@Column(name = "total")
	private Double total;

	@Column(name = "estado")
	private String estado;

	public PedidoDetalle() {
		super();
	}

	public PedidoDetalle(Integer idPedidoDetalle, Pedido pedido, Producto producto, Integer cantidad,
			Double precioUnitario, Double total, String estado) {
		super();
		this.idPedidoDetalle = idPedidoDetalle;
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.total = total;
		this.estado = estado;
	}

	public Integer getIdPedidoDetalle() {
		return idPedidoDetalle;
	}

	public void setIdPedidoDetalle(Integer idPedidoDetalle) {
		this.idPedidoDetalle = idPedidoDetalle;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
