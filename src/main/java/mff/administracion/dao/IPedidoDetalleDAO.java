package mff.administracion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mff.administracion.entity.PedidoDetalle;

public interface IPedidoDetalleDAO extends CrudRepository<PedidoDetalle, Integer> {

	@Query("select p "
			+ "from PedidoDetalle p where p.producto.idProducto = ?1 and p.estado = 'A' and p.pedido.estado_pedido = 'P'")
	public List<PedidoDetalle> buscarPedidoPorProducto(Integer idProducto);
	
	@Query("select p "
			+ "from PedidoDetalle p where p.pedido.idPedido = ?1 and p.estado = 'A' ")
	public List<PedidoDetalle> buscarPedidoPorPedido(Integer id);
	
	@Query(value="select p.id_producto, p.nombre, sum(d.cantidad) as cantidad "
			+ "from tbl_pedido_detalle d, tbl_producto p, tbl_pedido ped "
			+ "where d.id_producto = p.id_producto and d.id_pedido = ped.id_pedido "
			+ "and YEAR(ped.fecha) = :anio and MONTH(ped.fecha) = :mes "
			+ "group by p.id_producto,p.nombre "
			+ "order by cantidad desc ",nativeQuery=true)
	public List<Object[]> buscarMasVendidoMenosVendido(@Param("anio") Integer anio, @Param("mes") Integer mes);
	
}
