package mff.administracion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import mff.administracion.dto.PedidoClienteDTO;
import mff.administracion.service.IPedidoService;
import mff.administracion.util.DatosSesionUtil;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/mff-administracion/pedido")
public class PedidosController {

	@Autowired
	private IPedidoService pedidoService;
	
	
	@PostMapping("/generarPedido/{idcliente}")
	public ResponseEntity<?> generarPedido(@Valid @RequestBody String data, @PathVariable Integer idcliente) throws JsonMappingException, JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		try {
			System.out.println(idcliente);
			System.out.println(data);
			//JSONObject jsonObject = new JSONObject(data);
			ObjectMapper objectMapper = new ObjectMapper();
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			List<PedidoClienteDTO> resp = objectMapper.readValue(data, typeFactory.constructCollectionType(List.class, PedidoClienteDTO.class));
			for(PedidoClienteDTO dto : resp) {
				System.out.println(dto.getIdProducto());
			}
			if(this.pedidoService.grabarPedido(idcliente, resp) == false) {
				response.put("mensaje", "Error al realizar el pedido");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			//System.out.println(jsonObject);
		} catch (DataAccessException e) {
			response.put("mensaje", DatosSesionUtil.mensajeErrorGrabar);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Pedido generado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
}
