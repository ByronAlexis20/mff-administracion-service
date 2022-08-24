package mff.administracion.service;

import java.util.Date;
import java.util.List;

import mff.administracion.dto.PedidoClienteDTO;
import mff.administracion.entity.Pedido;
import mff.administracion.entity.PedidoDetalle;

public interface IPedidoService {

	public boolean grabarPedido(Integer idCliente, List<PedidoClienteDTO> listaPedido, String direccion);
	
	public List<PedidoDetalle> buscarPedidoPorProducto(Integer idProducto);
	
	public List<Pedido> buscarPedido();
	
	public List<PedidoDetalle> buscarPedidoPorPedido(Integer id);
	
	public Pedido buscarPedidoPorId(Integer id);
	
	public Pedido grabar(Pedido ped);
	
	public List<Pedido> buscarPedidoAtendidoPorFecha(Date fecha);
	
	public List<Pedido> buscarPedidoAtendidoPorRangoFecha(Date fechaInicio, Date fechaFin);
	
	public List<Pedido> buscarPedidoPendientePorFecha(Date fecha);
	
	public List<Object[]> buscarMasVendidoMenosVendido(Integer anio, Integer mes);
}
