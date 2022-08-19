package mff.administracion.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Integer> {

	@Query("Select c from Cliente c where c.idCliente = ?1 ")
	public Cliente buscarPorId(Integer idCliente);
	
}
