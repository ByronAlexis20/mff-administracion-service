package mff.administracion.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.administracion.dto.ProductoAppDTO;
import mff.administracion.dto.ProductoDTO;
import mff.administracion.dto.ProductosDTO;
import mff.administracion.entity.Imagen;
import mff.administracion.entity.Producto;
import mff.administracion.service.IImagenService;
import mff.administracion.service.IProductoService;
import mff.administracion.util.DatosSesionUtil;
import mff.administracion.util.FuncionesGenerales;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/mff-administracion/producto")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IImagenService imagenService;
	
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
						List<Imagen> img = this.imagenService.buscarImagenesPorProducto(p.getIdProducto());
						ProductosDTO pr = new ProductosDTO();
						pr.setIdProducto(p.getIdProducto());
						pr.setNombreProducto(p.getNombre());
						pr.setIdCategoria(p.getCategoria().getIdCategoria());
						pr.setPrecio(p.getPrecio());
						pr.setStock(p.getStock());
						pr.setEstado(p.getEstado());
						if(img.size() > 0)
							pr.setImagen(img.get(0).getImagen());
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
	

	@GetMapping(value = "/imprimirReporteProductos/{idCategoria}")
	public ResponseEntity<byte[]> imprimirReporteSRIenPDF(@PathVariable Integer idCategoria) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MMM/yyyy");
		
		List<ProductosDTO> dataFinal = new ArrayList<>();
		List<Producto> listaProductos = null; 
		if(idCategoria == 0)
			listaProductos = this.productoService.buscarProductosActivosDTO();
		else
			listaProductos = this.productoService.buscarProductosActivosDTOPorCategoria(idCategoria);
		
		
		for(Producto prod : listaProductos) {
			ProductosDTO dto = new ProductosDTO();
			dto.setCategoria(prod.getCategoria().getCategoria());
			dto.setEstado("A");
			dto.setIdCategoria(prod.getCategoria().getIdCategoria());
			dto.setIdProducto(prod.getIdProducto());
			dto.setNombreProducto(prod.getNombre());
			dto.setPrecio(prod.getPrecio());
			dto.setStock(prod.getStock());
			dataFinal.add(dto);
		}
		
		FuncionesGenerales obj = new FuncionesGenerales();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("empresa", "EL MARISCAL FAST FOOD");
		parameters.put("fecha", formatoFecha.format(new Date()));
		parameters.put("nombrereporte","Listado de productos por categoría");
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataFinal, false);
		
		byte[] bytes = obj.generarReportePDF("rptProductos", parameters, source);
		
		ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("reporteSRI" + ".pdf").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8").headers(headers)
				.body(bytes);
	}
	
	@GetMapping(value = "/buscarTodosActivos")
	public ResponseEntity<?> buscarTodosActivos() {
		List<ProductoAppDTO> data = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			List<Producto> listaProductos = productoService.buscarProductosActivosDTO();
			for(Producto prod : listaProductos) {
				ProductoAppDTO dto = new ProductoAppDTO();
				dto.setId(String.valueOf(prod.getIdProducto()));
				dto.setTitle(prod.getNombre());
				dto.setPrice(prod.getPrecio());
				List<Imagen> img = this.imagenService.buscarImagenesPorProducto(prod.getIdProducto());
				if(img.size() > 0)
					dto.setImage(img.get(0).getImagen());
				data.add(dto);
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
		return new ResponseEntity<List<ProductoAppDTO>>(data, HttpStatus.OK);
	}
}
