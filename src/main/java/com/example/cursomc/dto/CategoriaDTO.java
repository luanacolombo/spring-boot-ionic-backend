package com.example.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import com.example.cursomc.domain.Categoria;


public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	
	@NotEmpty(message="Preenchimento obrigatório") //diz que não pode ser vazio, se a validação não for verificada vai lançar a mensagem
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres") //se a validação não for verificada vai lançar a mensagem
	private String nome;
	
	//construtor
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	//gets e sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
