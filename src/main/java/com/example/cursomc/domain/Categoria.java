package com.example.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//atributos
	private Integer id;
	private String nome;
	
	//construtor
	public Categoria () { //construtor vazio instancia obj sem jogar nada para os atributos
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	//Get e Set, métodos de acesso
	
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

	@Override //gera um código numérico p/ cada objeto
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override //método que faz a comparação entre dois objetos considerando varias possibilidades
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
