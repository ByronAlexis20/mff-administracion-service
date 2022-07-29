package mff.administracion.dao;

import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.PedidoDetalle;

public interface IPedidoDetalleDAO extends CrudRepository<PedidoDetalle, Integer> {

}
