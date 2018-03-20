package com.wagneralmeida.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wagneralmeida.cursomc.domain.Categoria;
import com.wagneralmeida.cursomc.domain.Produto;
import com.wagneralmeida.cursomc.dto.CategoriaDTO;
import com.wagneralmeida.cursomc.dto.ProdutoDTO;
import com.wagneralmeida.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue="") String nome,
			@RequestParam(value = "categorias", defaultValue="") String categorias,
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue="ASC") String direction) {
		Page<Produto> produtos = service.search(nome, ids, page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriesDTO = categories.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(categoriesDTO);
	}

}
