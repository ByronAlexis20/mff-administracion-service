package mff.administracion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.administracion.dao.IClienteDAO;
import mff.administracion.entity.Cliente;
import mff.administracion.service.IClienteService;

@Service
public class ClienteImpl implements IClienteService {

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listaClientesActivos() {
		return this.clienteDAO.listaClientesActivos();
	}

}
