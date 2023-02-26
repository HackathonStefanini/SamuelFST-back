package com.stefanini.dto;

public class ComprarStefamonDTO {
	private Long jogadorId;
	private Double valor;
	private Long stefamonId;
	
	public ComprarStefamonDTO() {
	}

	public ComprarStefamonDTO(Long jogadorId, Double valor, Long stefamonId) {
		this.jogadorId = jogadorId;
		this.valor = valor;
		this.stefamonId = stefamonId;
	}

	public Long getJogadorId() {
		return jogadorId;
	}

	public void setJogadorId(Long jogadorId) {
		this.jogadorId = jogadorId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getStefamonId() {
		return stefamonId;
	}

	public void setStefamonId(Long stefamonId) {
		this.stefamonId = stefamonId;
	}
}
