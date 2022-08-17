package mff.administracion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mff.administracion.entity.Imagen;
import mff.administracion.entity.Producto;
import mff.administracion.entity.ResponseMessage;
import mff.administracion.service.IImagenService;
import mff.administracion.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-administracion/imagen")
public class ImagenRestController {

	@Autowired
	private IImagenService imagenService;
	
	@GetMapping(value = "/buscarImagenesPorProducto/{id}")
	public ResponseEntity<?> buscarImagenesPorProducto(@PathVariable Integer id) {
		List<Imagen> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = this.imagenService.buscarImagenesPorProducto(id);
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Imagen>>(data, HttpStatus.OK);
	}
	
	@PutMapping("/guardarImagenes/{idProducto}")
	public ResponseEntity<ResponseMessage> guardarImagenes(@PathVariable Integer idProducto, @RequestParam("file") MultipartFile[] files) {
		String message = "";
		try {
			for(MultipartFile file : files) {
				Imagen imagen = new Imagen();
				imagen.setProducto(new Producto(idProducto));
				imagen.setEstado("A");
				imagen.setIdImagen(null);
				imagen.setImagen(file.getBytes());
				this.imagenService.guardar(imagen);
			}
			message = "Imagenes Subida con Exito!!: ";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Ocurrio un problema al Subir Imagen!!: ";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
}
