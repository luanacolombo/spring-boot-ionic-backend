package com.example.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	//variáveis
	private int cod;
	private String descricao;
	
	//construtor
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	//get, uma vez instanciado um tipo enumerado não se muda mais o nome
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null) { //se o código for nulo
			return null; //retorna nulo
		}
		
		for (TipoCliente x : TipoCliente.values()) { //percorre todos os valores possiveis do tipo enumerado cliente (PF e PJ)
			if (cod.equals(x.getCod())) { //se o código x for igual ao código que estamos procurando
				return x; //retorna o x
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
