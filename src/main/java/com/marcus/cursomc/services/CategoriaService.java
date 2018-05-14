package com.marcus.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcus.cursomc.domain.Categoria;
import com.marcus.cursomc.repositories.CategoriaRepository;
import com.marcus.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoriaObjeto = repo.findById(id);
		
		return categoriaObjeto.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: "+ id + ", Tipo: "+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repo.save(categoria);
	}
}
