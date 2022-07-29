package mff.administracion.dao;

import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Pedido;

public interface IPedidoDAO extends CrudRepository<Pedido, Integer> {

}
