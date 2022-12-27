package com.example.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class) //indica que é um tratador de exceções desse tipo de exceção
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
	//esse método vai receber a exceção e as infos da requisição
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class) //indica que é um tratador de exceções desse tipo de exceção
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
	//esse método vai receber a exceção e as infos da requisição
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); //caso ocorra o erro, vai estourar essa exceção personalizada
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) //indica que é um tratador de exceções desse tipo de exceção
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){ //esse método vai receber a exceção e as infos da requisição
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for (FieldError x : e.getBindingResult().getFieldErrors()) { //e.getBindingResult().getFieldError() com isso acessa todos os erros de campos que aconteceram na exceção MethodArgumentNotValidException
			err.addError(x.getField(), x.getDefaultMessage()); //p/ cada FieldError x, irá chamar o nome e a mensagem
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); //caso ocorra o erro, vai estourar essa exceção personalizada
	}
	
}
