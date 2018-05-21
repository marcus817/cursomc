package com.marcus.cursomc.services.validation;
import com.marcus.cursomc.resources.enums.TipoCliente;
import com.marcus.cursomc.resources.exception.FieldMessage;
import com.marcus.cursomc.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcus.cursomc.domain.Cliente;
import com.marcus.cursomc.dto.NewClienteDTO;
import com.marcus.cursomc.repositories.ClienteRepository;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO>{

	@Autowired
	private ClienteRepository repo;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	
	@Override
	public boolean isValid(NewClienteDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list =  new ArrayList<>();
		
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Cliente cli = repo.findByEmail(objDTO.getEmail());
		
		if(cli != null) {
			list.add(new FieldMessage("Email", "E-mail já cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	
}
