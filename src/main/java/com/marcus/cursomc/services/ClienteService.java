package com.marcus.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcus.cursomc.domain.Cidade;
import com.marcus.cursomc.domain.Cliente;
import com.marcus.cursomc.domain.Endereco;
import com.marcus.cursomc.dto.ClienteDTO;
import com.marcus.cursomc.dto.NewClienteDTO;
import com.marcus.cursomc.repositories.ClienteRepository;
import com.marcus.cursomc.repositories.EnderecoRepository;
import com.marcus.cursomc.resources.enums.TipoCliente;
import com.marcus.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> clienteObjeto = repository.findById(id);
		
		return clienteObjeto.orElseThrow(()-> new ObjectNotFoundException(
					"Objeto não encontrado!: Id"+ id + ", Tipo: "+ Cliente.class.getName()
				));
	}

	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente clienteBanco = find(cliente.getId());
		updateData(clienteBanco, cliente);
		return repository.save(clienteBanco);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é permitido remover Cliente com Pedido");
		}
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
		
	}
	
	
	private void updateData(Cliente clienteBanco, Cliente cliente) {
		clienteBanco.setNome(cliente.getNome());
		clienteBanco.setEmail(cliente.getEmail());
	}


	public Cliente fromDTO(@Valid NewClienteDTO ncd) {
		Cliente cli = new Cliente(null, ncd.getNome(), ncd.getEmail(), ncd.getCpfOuCnpj(), TipoCliente.toEnum(ncd.getTipo()));
		Cidade cid = new Cidade(ncd.getCidadeID(), null, null);
		Endereco end = new Endereco(null, ncd.getLogradouro(), ncd.getLogradouro(), ncd.getComplemento(), ncd.getBairro(), ncd.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(ncd.getTelefone1());
		if(ncd.getTelefone2() != null) {
			cli.getTelefones().add(ncd.getTelefone2());
		}
		if(ncd.getTelefone3() != null) {
			cli.getTelefones().add(ncd.getTelefone3());
		}
		return cli;
	}
}
