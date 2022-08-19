package mff.administracion.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.administracion.dao.IClienteDAO;
import mff.administracion.dao.IPedidoDAO;
import mff.administracion.dao.IPedidoDetalleDAO;
import mff.administracion.dao.IProductoDAO;
import mff.administracion.dto.PedidoClienteDTO;
import mff.administracion.entity.Cliente;
import mff.administracion.entity.Pedido;
import mff.administracion.entity.PedidoDetalle;
import mff.administracion.entity.Producto;
import mff.administracion.service.IPedidoService;

@Service
public class PedidoImpl implements IPedidoService {

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private IPedidoDAO pedidoDAO;
	
	@Autowired
	private IPedidoDetalleDAO pedidoDetalleDAO;
	
	@Autowired
	private IProductoDAO productoDAO;
	
	@Override
	@Transactional
	public boolean grabarPedido(Integer idCliente, List<PedidoClienteDTO> listaPedido) {
		Boolean bandera = false;
		Cliente cl = this.clienteDAO.buscarPorId(idCliente);
		Pedido pedido = new Pedido();
		pedido.setCliente(cl);
		pedido.setEstado("A");
		pedido.setEstado_pedido("P");//estado del pedido pendiente
		pedido.setFecha(new Date());
		pedido.setIdPedido(null);
		pedido = this.pedidoDAO.save(pedido);
		
		double total = 0.0;
		for(PedidoClienteDTO dto : listaPedido) {
			Producto prod = this.productoDAO.buscarProductoPorId(dto.getIdProducto());
			PedidoDetalle pd = new PedidoDetalle();
			pd.setCantidad(dto.getCantidad());
			pd.setEstado("A");
			pd.setIdPedidoDetalle(null);
			pd.setPedido(pedido);
			pd.setPrecioUnitario(prod.getPrecio());
			pd.setProducto(prod);
			pd.setTotal(prod.getPrecio() * dto.getCantidad());
			total = total + (prod.getPrecio() * dto.getCantidad());
			pd = this.pedidoDetalleDAO.save(pd);
		}
		pedido.setTotal(total);
		bandera = true;
		return bandera;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PedidoDetalle> buscarPedidoPorProducto(Integer idProducto) {
		return this.pedidoDetalleDAO.buscarPedidoPorProducto(idProducto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pedido> buscarPedido() {
		return this.pedidoDAO.buscarPedidoPendiente();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PedidoDetalle> buscarPedidoPorPedido(Integer id) {
		return this.pedidoDetalleDAO.buscarPedidoPorPedido(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pedido buscarPedidoPorId(Integer id) {
		return this.pedidoDAO.buscarPedidoPorId(id);
	}

	@Override
	@Transactional
	public Pedido grabar(Pedido ped) {
		return this.pedidoDAO.save(ped);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pedido> buscarPedidoAtendido() {
		return this.pedidoDAO.buscarPedidoAtendido();
	}

}
