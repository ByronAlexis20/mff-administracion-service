package mff.administracion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.administracion.dao.ICategoriaDAO;
import mff.administracion.entity.Categoria;
import mff.administracion.service.ICategoriaService;

@Service
public class CategoriaImpl implements ICategoriaService {

	@Autowired
	private ICategoriaDAO categoriaDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> buscarTodasCategoriasActivas() {
		return this.categoriaDAO.buscarCategoriasActivos();
	}

	@Override
	@Transactional
	public Categoria grabar(Categoria cat) {
		return this.categoriaDAO.save(cat);
	}
}
