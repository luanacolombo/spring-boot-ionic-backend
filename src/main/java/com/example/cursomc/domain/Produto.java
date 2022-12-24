package com.example.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //geração automática dos ids
	private Integer id;
	private String nome;
	private Double preco;
	
	@JsonBackReference //vai omitir a lista de categorias para cada produto
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "produto_id"), //mapeamento da ista de categorias informando quem vai ser a tabela do banco de 
		inverseJoinColumns = @JoinColumn(name = "categoria_id") //dados que vai fazer o meio de campo entre as 2 tabelas (prod. e cate.)
			) //nessa notação é feita a definição de quem vai ser a tabela que vai fazer o muitos pra muitos no banco de dados
	
	private List<Categoria> categorias = new ArrayList<>(); //categorias é coleção, não entra no construtor
	
	@JsonIgnore //o Json irá ignorar essa parte
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>(); //serve para garantir que não terá item repitido no mesmo pedido
	
	//construtor
	public Produto() {
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	//Get e Set, métodos de acesso
	@JsonIgnore //o Json irá ignorar essa parte
	public List<Pedido> getPedidos() { //irá varrer os itens itens de pedindo montando uma lista de pedidos associados a essess itens
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) { //percorre a lista de itens que já existe na classe
			 lista.add(x.getPedido()); //pra cada item de pedido x que existe na lista de itens será adicionado o pedido associado a ele na lista
		}
		return lista;
	}
	
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
}	
	
