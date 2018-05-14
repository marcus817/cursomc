package com.marcus.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcus.cursomc.domain.Categoria;
import com.marcus.cursomc.domain.Pedido;
import com.marcus.cursomc.repositories.PedidoRepository;
import com.marcus.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repository;
	
	public Pedido find (Integer id) {
		Optional<Pedido> pedidoObjeto = repository.findById(id);
		
		return pedidoObjeto.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: "+ Categoria.class.getName()));
	}
}
