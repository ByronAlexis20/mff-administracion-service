package mff.administracion.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.administracion.dao.IPedidoDetalleDAO;
import mff.administracion.dto.DashboardDTO;
import mff.administracion.entity.Cliente;
import mff.administracion.entity.Pedido;
import mff.administracion.service.IClienteService;
import mff.administracion.service.IPedidoService;
import mff.administracion.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-administracion/dashboard")
public class DashboardRestController {

	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoDetalleDAO pedidoDetalleService;
	
	@GetMapping(value = "/consultarDashboardPorDia")
	public ResponseEntity<?> buscarOrdenesPorProducto() {
		DashboardDTO data = new DashboardDTO();
		Map<String, Object> response = new HashMap<>();
		try {
			List<Pedido> listaAtendidos = this.pedidoService.buscarPedidoAtendidoPorFecha(new Date());
			Double totalAtendidos = 0.0;
			for(Pedido ped : listaAtendidos) {
				totalAtendidos = totalAtendidos + ped.getTotal();
			}
			data.setRecaudacionDiaria(totalAtendidos);
			data.setTotalPedidosRealizados(listaAtendidos.size());
			List<Pedido> listaPendiente = this.pedidoService.buscarPedidoAtendidoPorFecha(new Date());
			data.setTotalPedidosPendientes(listaPendiente.size());
			List<Cliente> listaClientes = this.clienteService.listaClientesActivos();
			data.setTotalClientes(listaClientes.size());
			LocalDate date = LocalDate.now();
			List<Object[]> masMenosVendido = this.pedidoDetalleService.buscarMasVendidoMenosVendido(date.getYear(), date.getMonthValue());
			if(masMenosVendido.size() > 0) {
				Object[] masVendido = masMenosVendido.get(0);
				data.setCantidadMasVendido(Integer.parseInt(masVendido[2].toString()));
				data.setMasVendido(masVendido[1].toString());
				
				Object[] menosVendido = masMenosVendido.get(masMenosVendido.size() - 1);
				data.setCantidadMenosVendido(Integer.parseInt(menosVendido[2].toString()));
				data.setMenosVendido(menosVendido[1].toString());
			}
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DashboardDTO>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarClientesActivos")
	public ResponseEntity<?> listarClientesActivos() {
		List<Cliente> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = this.clienteService.listaClientesActivos();
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Cliente>>(data, HttpStatus.OK);
	}
	
}
