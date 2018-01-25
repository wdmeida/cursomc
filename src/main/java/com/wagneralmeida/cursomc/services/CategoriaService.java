package com.wagneralmeida.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wagneralmeida.cursomc.domain.Categoria;
import com.wagneralmeida.cursomc.repositories.CategoriaRepository;
import com.wagneralmeida.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Categoria obj = categoriaRepository.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}	
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
}
