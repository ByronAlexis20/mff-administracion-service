package mff.administracion.dao;

import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Integer> {

}
