package mff.administracion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.PedidoDetalle;

public interface IPedidoDetalleDAO extends CrudRepository<PedidoDetalle, Integer> {

	@Query("select p "
			+ "from PedidoDetalle p where p.producto.idProducto = ?1 and p.estado = 'A' and p.pedido.estado_pedido = 'P'")
	public List<PedidoDetalle> buscarPedidoPorProducto(Integer idProducto);
	
	@Query("select p "
			+ "from PedidoDetalle p where p.pedido.idPedido = ?1 and p.estado = 'A' ")
	public List<PedidoDetalle> buscarPedidoPorPedido(Integer id);
	
}
