package mff.administracion.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.administracion.dao.PedidosAtenderDTO;
import mff.administracion.dto.PedidoDTO;
import mff.administracion.dto.ProductoDTO;
import mff.administracion.dto.ProductosDTO;
import mff.administracion.entity.Imagen;
import mff.administracion.entity.Pedido;
import mff.administracion.entity.PedidoDetalle;
import mff.administracion.entity.Producto;
import mff.administracion.service.IImagenService;
import mff.administracion.service.IPedidoService;
import mff.administracion.service.IProductoService;
import mff.administracion.util.DatosSesionUtil;
import mff.administracion.util.FuncionesGenerales;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/mff-administracion/pedidocliente")
public class PedidosClienteRestController {

	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IImagenService imagenService;
	
	@GetMapping(value = "/buscarPedidosPorCategoria/{idcategoria}")
	public ResponseEntity<?> buscarActivos(@PathVariable Integer idcategoria) {
		List<PedidosAtenderDTO> result = new ArrayList<>();
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
					if(idcategoria == 0) {
						ProductoDTO pd = new ProductoDTO();
						pd.setCategoria(p.getCategoria().getCategoria());
						pd.setIdCategoria(p.getCategoria().getIdCategoria());
						data.add(pd);
					}else {
						if(idcategoria == p.getCategoria().getIdCategoria()) {
							ProductoDTO pd = new ProductoDTO();
							pd.setCategoria(p.getCategoria().getCategoria());
							pd.setIdCategoria(p.getCategoria().getIdCategoria());
							data.add(pd);
						}
					}
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
						pr.setCodigo(p.getCodigo());
						if(img.size() > 0)
							pr.setImagen(img.get(0).getImagen());
						lista.add(pr);
					}
				}
				dto.setProductos(lista);
			}
			
			//ahora armar el json de los pedidos
			for(ProductoDTO dto : data) {
				for(ProductosDTO prodDTO : dto.getProductos()) {
					PedidosAtenderDTO pedido = new PedidosAtenderDTO();
					List<PedidoDetalle> lista = this.pedidoService.buscarPedidoPorProducto(prodDTO.getIdProducto());
					pedido.setCategoria(dto.getCategoria());
					pedido.setIdCategoria(dto.getIdCategoria());
					pedido.setIdProducto(prodDTO.getIdProducto());
					pedido.setNombreProducto(prodDTO.getNombreProducto());
					pedido.setPrecio(prodDTO.getPrecio());
					pedido.setImagen(prodDTO.getImagen());
					pedido.setCantidadPedida(lista.size());
					result.add(pedido);
				}
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
		return new ResponseEntity<List<PedidosAtenderDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarOrdenesPorProducto/{id}")
	public ResponseEntity<?> buscarOrdenesPorProducto(@PathVariable Integer id) {
		List<PedidoDetalle> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = this.pedidoService.buscarPedidoPorProducto(id);
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PedidoDetalle>>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPedidos")
	public ResponseEntity<?> buscarOrdenes() {
		List<Pedido> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = this.pedidoService.buscarPedido();
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Pedido>>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPedidosDetalle/{id}")
	public ResponseEntity<?> buscarPedidosDetalle(@PathVariable Integer id) {
		List<PedidoDetalle> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = this.pedidoService.buscarPedidoPorPedido(id);
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PedidoDetalle>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/atender/{id}")
	public ResponseEntity<?> atender(@PathVariable Integer id)  {
		Map<String, Object> response = new HashMap<>();
		try {
			Pedido pedido = this.pedidoService.buscarPedidoPorId(id);
			pedido.setEstado_pedido("A");
			
			this.pedidoService.grabar(pedido);
		} catch (DataAccessException e) {
			response.put("mensaje", DatosSesionUtil.mensajeErrorGrabar);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Pedido generado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/imprimirReporteVentas")
	public ResponseEntity<byte[]> imprimirReporteVentas() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MMM/yyyy");
		
		List<PedidoDTO> dataFinal = new ArrayList<>();
		List<Pedido> listaPedidos = this.pedidoService.buscarPedidoAtendido(); 
		Integer cantidad = 0;
		for(Pedido prod : listaPedidos) {
			PedidoDTO dto = new PedidoDTO();
			List<PedidoDetalle> det = this.pedidoService.buscarPedidoPorPedido(prod.getIdPedido());
			cantidad = 0;
			for(PedidoDetalle d : det) {
				cantidad = cantidad + d.getCantidad();
			}
			dto.setCantidad(String.valueOf(cantidad));
			dto.setFecha(formatoFecha.format(prod.getFecha()));
			dto.setIdPedido(prod.getIdPedido());
			dto.setNombreCliente(prod.getCliente().getNombres() + " " + prod.getCliente().getApellidos());
			dto.setTotal(prod.getTotal());
			dataFinal.add(dto);
		}
		
		FuncionesGenerales obj = new FuncionesGenerales();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("empresa", "EL MARISCAL FAST FOOD");
		parameters.put("fecha", formatoFecha.format(new Date()));
		parameters.put("nombrereporte","Listado de ventas de pedidos");
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataFinal, false);
		
		byte[] bytes = obj.generarReportePDF("rptVentas", parameters, source);
		
		ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("reporteVentas" + ".pdf").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8").headers(headers)
				.body(bytes);
	}
}
