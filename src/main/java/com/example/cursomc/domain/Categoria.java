package com.example.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //geração automática dos ids
	private Integer id;
	private String nome;
	
	@JsonManagedReference //referência gerenciada pelo Json
	@ManyToMany(mappedBy = "categorias") //feito o mapeamento muitos pra muitos dos dois lados
	private List<Produto> produtos = new ArrayList<>();
	
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
