package mff.administracion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_imagen")
public class Imagen implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen", columnDefinition = "int4")
	private Integer idImagen;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@Lob
	@Column(name = "imagen")
	private byte[] imagen;

	@Column(name = "estado")
	private String estado;

	public Imagen() {
		super();
	}

	public Imagen(Integer idImagen, Producto producto, byte[] imagen, String estado) {
		super();
		this.idImagen = idImagen;
		this.producto = producto;
		this.imagen = imagen;
		this.estado = estado;
	}

	public Integer getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
