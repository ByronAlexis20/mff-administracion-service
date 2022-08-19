package mff.administracion.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido", columnDefinition = "int4")
	private Integer idPedido;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "latitud")
	private Double latitud;
	
	@Column(name = "longitud")
	private Double longitud;
	
	@Column(name = "total")
	private Double total;

	@Column(name = "estado_pedido")
	private String estado_pedido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechacCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_eliminacion")
	private Date fechaEliminacion;

	@Column(name = "usu_creacion")
	private Integer usuCreacion;

	@Column(name = "usu_modificacion")
	private Integer usuModificacion;

	@Column(name = "usu_eliminacion")
	private Integer usuEliminacion;

	@Column(name = "estado")
	private String estado;

	public Pedido() {
		super();
	}

	public Pedido(Integer idPedido, Cliente cliente, Date fecha, Double latitud, Double longitud, Double total,
			String estado_pedido, Date fechacCreacion, Date fechaModificacion, Date fechaEliminacion,
			Integer usuCreacion, Integer usuModificacion, Integer usuEliminacion, String estado) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.fecha = fecha;
		this.latitud = latitud;
		this.longitud = longitud;
		this.total = total;
		this.estado_pedido = estado_pedido;
		this.fechacCreacion = fechacCreacion;
		this.fechaModificacion = fechaModificacion;
		this.fechaEliminacion = fechaEliminacion;
		this.usuCreacion = usuCreacion;
		this.usuModificacion = usuModificacion;
		this.usuEliminacion = usuEliminacion;
		this.estado = estado;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(String estado_pedido) {
		this.estado_pedido = estado_pedido;
	}

	public Date getFechacCreacion() {
		return fechacCreacion;
	}

	public void setFechacCreacion(Date fechacCreacion) {
		this.fechacCreacion = fechacCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public Integer getUsuCreacion() {
		return usuCreacion;
	}

	public void setUsuCreacion(Integer usuCreacion) {
		this.usuCreacion = usuCreacion;
	}

	public Integer getUsuModificacion() {
		return usuModificacion;
	}

	public void setUsuModificacion(Integer usuModificacion) {
		this.usuModificacion = usuModificacion;
	}

	public Integer getUsuEliminacion() {
		return usuEliminacion;
	}

	public void setUsuEliminacion(Integer usuEliminacion) {
		this.usuEliminacion = usuEliminacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
