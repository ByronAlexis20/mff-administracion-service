package mff.administracion.dto;

public class ProductoAppDTO {
	private String id;
	private byte[] image;
	private String title;
	private Double price;
	private String descripcion;
	private Integer stock;

	public ProductoAppDTO() {
		super();
	}

	public ProductoAppDTO(String id, byte[] image, String title, Double price) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
