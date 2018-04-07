package com.marcus.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcus.cursomc.domain.Cliente;
import com.marcus.cursomc.repositories.ClienteRepository;
import com.marcus.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> clienteObjeto = repository.findById(id);
		
		return clienteObjeto.orElseThrow(()-> new ObjectNotFoundException(
					"Objeto não encontrado!: Id"+ id + ", Tipo: "+ Cliente.class.getName()
				));
	}
}
