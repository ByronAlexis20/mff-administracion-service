package mff.administracion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.administracion.entity.Categoria;

public interface ICategoriaDAO extends CrudRepository<Categoria, Integer> {

	@Query("Select c from Categoria c where c.estado = 'A'")
	public List<Categoria> buscarCategoriasActivos();
	
}
