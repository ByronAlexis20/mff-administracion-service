package mff.administracion.service;

import java.util.List;

import mff.administracion.dto.PedidoClienteDTO;
import mff.administracion.entity.Pedido;
import mff.administracion.entity.PedidoDetalle;

public interface IPedidoService {

	public boolean grabarPedido(Integer idCliente, List<PedidoClienteDTO> listaPedido);
	
	public List<PedidoDetalle> buscarPedidoPorProducto(Integer idProducto);
	
	public List<Pedido> buscarPedido();
	
	public List<PedidoDetalle> buscarPedidoPorPedido(Integer id);
	
	public Pedido buscarPedidoPorId(Integer id);
	
	public Pedido grabar(Pedido ped);
	
	public List<Pedido> buscarPedidoAtendido();
}
