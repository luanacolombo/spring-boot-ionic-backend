package com.example.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.cursomc.domain.enums.EstadoPagamento;

//sub classe
@Entity
public class PagamentoComBoleto extends Pagamento { //extensão da super classe
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;

	//construtor
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	//gets e sets
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

		
}
