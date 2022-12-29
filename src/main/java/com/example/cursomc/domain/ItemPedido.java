package com.example.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore //o Json irá ignorar essa parte
	@EmbeddedId //id embutido em um tipo auxiliar
	private ItemPedidoPK id = new ItemPedidoPK(); //ItemPedidoPK é uma peculiaridade do ItemPedido
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	//construtor
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	//gets e sets
	public double getSubTotal() { //calculo do subtotal do pedido, ou seja o total de cada produto
		return (preco - desconto) * quantidade;
	}

	@JsonIgnore //o Json irá ignorar essa parte
	public Pedido getPedido() { //é p/ ter acesso direto ao pedido fora da classe ItemPedido
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) { //irá definir o pedido associado ao ItemPedido
		id.setPedido(pedido);
	}
	
	public Produto getProduto() { //é p/ ter acesso direto ao produto fora da classe ItemPedido
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) { //irá definir o produto associado ao ItemPedido
		id.setProduto(produto);
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	//hashCode e equals (implementação padrão: somente id)
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
}
