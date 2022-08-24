package mff.administracion.dto;

public class DashboardDTO {

	private Double recaudacionDiaria;
	private Integer totalPedidosRealizados;
	private Integer totalPedidosPendientes;
	private Integer totalClientes;
	private String masVendido;
	private Integer cantidadMasVendido;
	private String menosVendido;
	private Integer cantidadMenosVendido;

	public DashboardDTO() {
		super();
	}

	public Double getRecaudacionDiaria() {
		return recaudacionDiaria;
	}

	public void setRecaudacionDiaria(Double recaudacionDiaria) {
		this.recaudacionDiaria = recaudacionDiaria;
	}

	public Integer getTotalPedidosRealizados() {
		return totalPedidosRealizados;
	}

	public void setTotalPedidosRealizados(Integer totalPedidosRealizados) {
		this.totalPedidosRealizados = totalPedidosRealizados;
	}

	public Integer getTotalPedidosPendientes() {
		return totalPedidosPendientes;
	}

	public void setTotalPedidosPendientes(Integer totalPedidosPendientes) {
		this.totalPedidosPendientes = totalPedidosPendientes;
	}

	public Integer getTotalClientes() {
		return totalClientes;
	}

	public void setTotalClientes(Integer totalClientes) {
		this.totalClientes = totalClientes;
	}

	public String getMasVendido() {
		return masVendido;
	}

	public void setMasVendido(String masVendido) {
		this.masVendido = masVendido;
	}

	public Integer getCantidadMasVendido() {
		return cantidadMasVendido;
	}

	public void setCantidadMasVendido(Integer cantidadMasVendido) {
		this.cantidadMasVendido = cantidadMasVendido;
	}

	public String getMenosVendido() {
		return menosVendido;
	}

	public void setMenosVendido(String menosVendido) {
		this.menosVendido = menosVendido;
	}

	public Integer getCantidadMenosVendido() {
		return cantidadMenosVendido;
	}

	public void setCantidadMenosVendido(Integer cantidadMenosVendido) {
		this.cantidadMenosVendido = cantidadMenosVendido;
	}

}
