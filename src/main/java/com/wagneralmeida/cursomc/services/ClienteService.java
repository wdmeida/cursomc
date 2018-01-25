package com.wagneralmeida.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagneralmeida.cursomc.domain.Cliente;
import com.wagneralmeida.cursomc.repositories.ClienteRepository;
import com.wagneralmeida.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Cliente.class.getName());
		}	
		return obj;
	}
}
