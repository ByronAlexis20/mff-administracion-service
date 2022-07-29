package mff.administracion.service;

import java.util.List;

import mff.administracion.entity.Categoria;

public interface ICategoriaService {

	public List<Categoria> buscarTodasCategoriasActivas();
	
	public Categoria grabar(Categoria cat);
	
	
}
