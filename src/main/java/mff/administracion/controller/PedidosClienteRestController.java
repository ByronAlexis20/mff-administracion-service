package mff.administracion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.administracion.dao.PedidosAtenderDTO;
import mff.administracion.dto.PedidoDetalleDTO;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
		List<PedidoDetalleDTO> data = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		try {
			List<PedidoDetalle> lista = this.pedidoService.buscarPedidoPorPedido(id);
			for(PedidoDetalle det : lista) {
				PedidoDetalleDTO dto = new PedidoDetalleDTO();
				dto.setCantidad(det.getCantidad());
				dto.setIdPedidoDetalle(det.getIdPedidoDetalle());
				dto.setPrecioUnitario(det.getPrecioUnitario());
				dto.setProducto(det.getProducto().getNombre());
				dto.setTotal(det.getTotal());
				List<Imagen> img = this.imagenService.buscarImagenesPorProducto(det.getProducto().getIdProducto());
				if(img.size() > 0)
					dto.setImagen(img.get(0).getImagen());
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
		return new ResponseEntity<List<PedidoDetalleDTO>>(data, HttpStatus.OK);
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
	
}
