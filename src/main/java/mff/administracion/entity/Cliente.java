package mff.administracion.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", columnDefinition = "int4")
	private Integer idCliente;

	@Column(name = "cedula")
	private String cedula;

	@Column(name = "celular")
	private String celular;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name = "correo")
	private String correo;

	@Column(name = "clave")
	private String clave;

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

	public Cliente() {
		super();
	}

	public Cliente(Integer idCliente, String cedula, String celular, String nombres, String apellidos,
			Date fechaNacimiento, String correo, String clave, Date fechacCreacion, Date fechaModificacion,
			Date fechaEliminacion, Integer usuCreacion, Integer usuModificacion, Integer usuEliminacion,
			String estado) {
		super();
		this.idCliente = idCliente;
		this.cedula = cedula;
		this.celular = celular;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.clave = clave;
		this.fechacCreacion = fechacCreacion;
		this.fechaModificacion = fechaModificacion;
		this.fechaEliminacion = fechaEliminacion;
		this.usuCreacion = usuCreacion;
		this.usuModificacion = usuModificacion;
		this.usuEliminacion = usuEliminacion;
		this.estado = estado;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
