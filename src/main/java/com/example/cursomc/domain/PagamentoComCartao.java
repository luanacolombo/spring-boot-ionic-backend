package com.example.cursomc.domain;

import javax.persistence.Entity;

import com.example.cursomc.domain.enums.EstadoPagamento;

//sub classe
@Entity
public class PagamentoComCartao extends Pagamento { //extensão da super classe
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	//construtor
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		this.numeroDeParcelas = numeroDeParcelas;
	}

	//gets e sets
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
