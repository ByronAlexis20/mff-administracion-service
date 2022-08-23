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
@Table(name = "tbl_producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto", columnDefinition = "int4")
	private Integer idProducto;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "stock")
	private Integer stock;
	
	@Column(name = "codigo")
	private String codigo;

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
	
	@Column(name = "descripcion")
	private String descripcion;

	public Producto() {
		super();
	}

	public Producto(Integer idProducto) {
		super();
		this.idProducto = idProducto;
	}
	
	public Producto(Integer idProducto, Categoria categoria, String nombre, Double precio, Integer stock,
			Date fechacCreacion, Date fechaModificacion, Date fechaEliminacion, Integer usuCreacion,
			Integer usuModificacion, Integer usuEliminacion, String estado) {
		super();
		this.idProducto = idProducto;
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.fechacCreacion = fechacCreacion;
		this.fechaModificacion = fechaModificacion;
		this.fechaEliminacion = fechaEliminacion;
		this.usuCreacion = usuCreacion;
		this.usuModificacion = usuModificacion;
		this.usuEliminacion = usuEliminacion;
		this.estado = estado;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
