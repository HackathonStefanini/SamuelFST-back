package com.stefanini.dto;

public class BattleRelatoryDTO {
	
	private String status;
	private String vencedor;
	private String perdedor;
	private Integer totalAtaque;
	
	public BattleRelatoryDTO() {
	}

	public BattleRelatoryDTO(String status, String vencedor, String perdedor, Integer totalAtaque) {
		this.status = status;
		this.vencedor = vencedor;
		this.perdedor = perdedor;
		this.totalAtaque = totalAtaque;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVencedor() {
		return vencedor;
	}

	public void setVencedor(String vencedor) {
		this.vencedor = vencedor;
	}

	public String getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(String perdedor) {
		this.perdedor = perdedor;
	}

	public Integer getTotalAtaque() {
		return totalAtaque;
	}

	public void setTotalAtaque(Integer totalAtaque) {
		this.totalAtaque = totalAtaque;
	}
}
