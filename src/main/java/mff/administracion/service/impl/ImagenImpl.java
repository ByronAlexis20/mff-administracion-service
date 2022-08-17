package mff.administracion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.administracion.dao.IImagenDAO;
import mff.administracion.entity.Imagen;
import mff.administracion.service.IImagenService;

@Service
public class ImagenImpl implements IImagenService {

	@Autowired
	private IImagenDAO imagenDAO;
	
	@Override
	@Transactional
	public Imagen guardar(Imagen img) {
		return this.imagenDAO.save(img);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Imagen> buscarImagenesPorProducto(Integer id) {
		return this.imagenDAO.buscarPorIdProducto(id);
	}

}
