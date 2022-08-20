package mff.administracion.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.administracion.dto.PedidoDTO;
import mff.administracion.dto.ProductosDTO;
import mff.administracion.entity.Pedido;
import mff.administracion.entity.PedidoDetalle;
import mff.administracion.entity.Producto;
import mff.administracion.service.IPedidoService;
import mff.administracion.service.IProductoService;
import mff.administracion.util.FuncionesGenerales;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/mff-administracion/reportes")
public class ReportesRestController {

	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IProductoService productoService;
	
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
	
	@GetMapping(value = "/imprimirReporteVentasPorFecha/{fecha}")
	public ResponseEntity<byte[]> imprimirReporteVentasPorFecha(@PathVariable Date fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MMM/yyyy");
		
		List<PedidoDTO> dataFinal = new ArrayList<>();
		List<Pedido> listaPedidos = this.pedidoService.buscarPedidoAtendidoPorFecha(fecha); 
		Integer cantidad = 0;
		for(Pedido prod : listaPedidos) {
			PedidoDTO dto = new PedidoDTO();
			List<PedidoDetalle> det = this.pedidoService.buscarPedidoPorPedido(prod.getIdPedido());
			cantidad = 0;
			for(PedidoDetalle d : det) {
				cantidad = cantidad + d.getCantidad();
			}
			for(PedidoDetalle d : det) {
				dto.setCantidad(String.valueOf(cantidad));
				dto.setFecha(formatoFecha.format(prod.getFecha()));
				dto.setIdPedido(prod.getIdPedido());
				dto.setNombreCliente(prod.getCliente().getNombres() + " " + prod.getCliente().getApellidos());
				dto.setTotal(prod.getTotal());
				dto.setCatidadProducto(d.getCantidad());
				dto.setPrecioUnitario(d.getPrecioUnitario());
				dto.setProducto(d.getProducto().getNombre());
				dto.setTotalProducto(d.getTotal());
				dataFinal.add(dto);
			}
		}
		
		FuncionesGenerales obj = new FuncionesGenerales();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("empresa", "EL MARISCAL FAST FOOD");
		parameters.put("fecha", formatoFecha.format(new Date()));
		parameters.put("nombrereporte","Listado de ventas de pedidos para la fecha: " + formatoFecha.format(fecha));
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataFinal, false);
		
		byte[] bytes = obj.generarReportePDF("rptVentas", parameters, source);
		
		ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("reporteVentas" + ".pdf").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8").headers(headers)
				.body(bytes);
	}
	
	@GetMapping(value = "/imprimirReporteVentasPorRangoFecha/{fechaInicio}/{fechaFin}")
	public ResponseEntity<byte[]> imprimirReporteVentasPorRangoFecha(@PathVariable Date fechaInicio, @PathVariable Date fechaFin) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MMM/yyyy");
		
		List<PedidoDTO> dataFinal = new ArrayList<>();
		List<Pedido> listaPedidos = this.pedidoService.buscarPedidoAtendidoPorRangoFecha(fechaInicio, fechaFin);
		Integer cantidad = 0;
		for(Pedido prod : listaPedidos) {
			PedidoDTO dto = new PedidoDTO();
			List<PedidoDetalle> det = this.pedidoService.buscarPedidoPorPedido(prod.getIdPedido());
			cantidad = 0;
			for(PedidoDetalle d : det) {
				cantidad = cantidad + d.getCantidad();
			}
			for(PedidoDetalle d : det) {
				dto.setCantidad(String.valueOf(cantidad));
				dto.setFecha(formatoFecha.format(prod.getFecha()));
				dto.setIdPedido(prod.getIdPedido());
				dto.setNombreCliente(prod.getCliente().getNombres() + " " + prod.getCliente().getApellidos());
				dto.setTotal(prod.getTotal());
				dto.setCatidadProducto(d.getCantidad());
				dto.setPrecioUnitario(d.getPrecioUnitario());
				dto.setProducto(d.getProducto().getNombre());
				dto.setTotalProducto(d.getTotal());
				dataFinal.add(dto);
			}
		}
		
		FuncionesGenerales obj = new FuncionesGenerales();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("empresa", "EL MARISCAL FAST FOOD");
		parameters.put("fecha", formatoFecha.format(new Date()));
		parameters.put("nombrereporte","Listado de ventas de pedidos desde: " + formatoFecha.format(fechaInicio) + " hasta " + formatoFecha.format(fechaFin));
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataFinal, false);
		
		byte[] bytes = obj.generarReportePDF("rptVentas", parameters, source);
		
		ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("reporteVentas" + ".pdf").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok().header("Content-Type", "application/pdf; charset=UTF-8").headers(headers)
				.body(bytes);
	}
}
