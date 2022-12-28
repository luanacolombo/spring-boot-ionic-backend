package com.example.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request; //permite obter o parametro da uri
	
	@Autowired
	private ClienteRepository repo; //p/ testar se o e-mail ja existe
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	//isValid é o método da interface ConstraintValidator, que verifica se o nosso tipo(ClienteDTO) será válido ou não
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); //Map é uma estrutura de dados, uma coleção de pares chave valor 
		//HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE pega o map de variáveis de uri que estão ma requisição
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail()); //logica para testar se o e-mail já existe 
			if (aux != null && !aux.getId().equals(uriId)) { //se o aux for diferente de nulo significa que ele encontrou um registro no banco que tinha o e-mail
				 list.add(new FieldMessage("email", "Email já existente")); //se o e-mail já existe mostra o erro e a mensagem
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
