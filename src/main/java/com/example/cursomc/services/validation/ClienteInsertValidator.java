package com.example.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.enums.TipoCliente;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.resources.exceptions.FieldMessage;
import com.example.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo; //p/ testar se o e-mail ja existe
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	//isValid é o método da interface ConstraintValidator, que verifica se o nosso tipo(ClienteNewDTO) será válido ou não
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) { //se o tipo do DTO for igual a pessoa fisica e se o cpf não for válido 
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido")); //retorna a mensagem
		}
		 
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) { //se o tipo do DTO for igual a pessoa juridica e se o cnpj não for válido 
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido")); //retorna a mensagem
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail()); //logica para testar se o e-mail já existe 
			if (aux != null) { //se o aux for diferente de nulo significa que ele encontrou um registro no banco que tinha o e-mail
				 list.add(new FieldMessage("email", "email já existente")); //se o e-mail já existe mostra o erro e a mensagem
			}
		
		for (FieldMessage e : list) { //serve para percorrer a lista de FieldMessage, e p/ cada obj que tiver na lista vai ser add um 
		//erro correspondente na lista de erros do framework que está descrito abaixo
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation(); //esses 2 comandos permitem transportar os erros personalizados para a lista de erros do framework
		}
		return list.isEmpty(); //se a lista de erros estiver vazia, significa que não tem nenhum erro e o método isValid vai retornar 
		//verdadeiro, porém se houver algum erro a lista não vai estar vazia e o método vai retornar falso
	}
}
