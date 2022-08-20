package mff.administracion.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Pedido;

public interface IPedidoDAO extends CrudRepository<Pedido, Integer> {

	@Query("select p "
			+ "from Pedido p where p.estado = 'A' and p.estado_pedido = 'P'")
	public List<Pedido> buscarPedidoPendiente();
	
	@Query("select p "
			+ "from Pedido p where p.estado = 'A' and p.estado_pedido = 'A' and p.fecha = ?1 order by p.fecha")
	public List<Pedido> buscarPedidoAtendidoPorFecha(Date fecha);
	
	@Query("select p "
			+ "from Pedido p where p.estado = 'A' and p.estado_pedido = 'A' and p.fecha BETWEEN ?1 and ?2 order by p.fecha")
	public List<Pedido> buscarPedidoAtendidoPorRangoFecha(Date fechaInicio, Date fechaFin);
	
	@Query("select p "
			+ "from Pedido p where p.idPedido = ?1 ")
	public Pedido buscarPedidoPorId(Integer id);
}
