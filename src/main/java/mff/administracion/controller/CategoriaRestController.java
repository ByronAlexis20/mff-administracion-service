package mff.administracion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.administracion.entity.Categoria;
import mff.administracion.service.ICategoriaService;
import mff.administracion.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-administracion/categoria")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping(value = "/buscarActivos")
	public ResponseEntity<?> buscarActivos() {
		List<Categoria> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = categoriaService.buscarTodasCategoriasActivas();
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categoria>>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarActivosMasTodos")
	public ResponseEntity<?> buscarActivosMasTodos() {
		List<Categoria> data = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			Categoria cat = new Categoria();
			cat.setIdCategoria(0);
			cat.setCategoria("--- Todos ---");
			data.add(cat);
			data.addAll(categoriaService.buscarTodasCategoriasActivas());
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categoria>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Categoria cat, BindingResult result) {
		Categoria data = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			
			data = this.categoriaService.grabar(cat);
		} catch (DataAccessException e) {
			response.put("mensaje", DatosSesionUtil.mensajeErrorGrabar);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", DatosSesionUtil.mensajeOkGrabar);
		response.put("usuario", data);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
