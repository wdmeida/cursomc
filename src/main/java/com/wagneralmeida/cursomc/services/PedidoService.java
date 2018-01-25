package com.wagneralmeida.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagneralmeida.cursomc.domain.Pedido;
import com.wagneralmeida.cursomc.repositories.PedidoRepository;
import com.wagneralmeida.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}	
		return obj;
	}
}
