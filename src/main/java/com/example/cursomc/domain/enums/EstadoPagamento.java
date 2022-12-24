package com.example.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	//variáveis
		private int cod;
		private String descricao;
		
	//construtor
	private EstadoPagamento(int cod, String descricao) {
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
		
	public static EstadoPagamento toEnum(Integer cod) {
			
		if (cod == null) { //se o código for nulo
			return null; //retorna nulo
		}
			
		for (EstadoPagamento x : EstadoPagamento.values()) { //percorre todos os valores possiveis do tipo enumerado pagamento
			if (cod.equals(x.getCod())) { //se o código x for igual ao código que estamos procurando
				return x; //retorna o x
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
