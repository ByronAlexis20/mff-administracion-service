package mff.administracion.service;

import java.util.List;

import mff.administracion.entity.Imagen;

public interface IImagenService {

	public Imagen guardar(Imagen img);
	
	public List<Imagen> buscarImagenesPorProducto(Integer id);
	
}
