package com.marcus.cursomc.services.validation;
import com.marcus.cursomc.resources.enums.TipoCliente;
import com.marcus.cursomc.resources.exception.FieldMessage;
import com.marcus.cursomc.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.marcus.cursomc.dto.NewClienteDTO;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO>{

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
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	
}
