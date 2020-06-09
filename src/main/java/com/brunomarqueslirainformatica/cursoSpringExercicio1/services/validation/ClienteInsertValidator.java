package com.brunomarqueslirainformatica.cursoSpringExercicio1.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Cliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.enums.TipoCliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.dto.ClienteNewDTO;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.ClienteRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.resources.exception.FieldMessage;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getcpfouCnpj())) {
			list.add(new FieldMessage("cpfouCnpj", "CPF inválido"));
		} 
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getcpfouCnpj())) {
			list.add(new FieldMessage("cpfouCnpj", "CNPJ inválido"));
		} 
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}