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

import mff.administracion.dao.ProductosDTO;
import mff.administracion.dto.ProductoDTO;
import mff.administracion.entity.Producto;
import mff.administracion.service.IProductoService;
import mff.administracion.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-administracion/producto")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping(value = "/buscarActivos")
	public ResponseEntity<?> buscarActivos() {
		List<ProductoDTO> data = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			Boolean bandera = false;
			List<Producto> prod = productoService.buscarProductosActivosDTO();
			for(Producto p : prod) {
				bandera = false;
				for(ProductoDTO dto : data) {
					if(dto.getIdCategoria() == p.getCategoria().getIdCategoria()) {
						bandera = true;
					}
				}
				if(bandera == false) {
					ProductoDTO pd = new ProductoDTO();
					pd.setCategoria(p.getCategoria().getCategoria());
					pd.setIdCategoria(p.getCategoria().getIdCategoria());
					data.add(pd);
				}
			}
			for(ProductoDTO dto : data) {
				List<ProductosDTO> lista = new ArrayList<>();
				for(Producto p : prod) {
					if(p.getCategoria().getIdCategoria() == dto.getIdCategoria()) {
						ProductosDTO pr = new ProductosDTO();
						pr.setIdProducto(p.getIdProducto());
						pr.setNombreProducto(p.getNombre());
						pr.setIdCategoria(p.getCategoria().getIdCategoria());
						pr.setPrecio(p.getPrecio());
						pr.setStock(p.getStock());
						pr.setEstado(p.getEstado());
						lista.add(pr);
					}
				}
				dto.setProductos(lista);
			}
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ProductoDTO>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/guardarProducto")
	public ResponseEntity<?> guardarProducto(@Valid @RequestBody ProductosDTO pr, BindingResult result) {
		Producto data = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			
			data = this.productoService.grabarProducto(pr);
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
