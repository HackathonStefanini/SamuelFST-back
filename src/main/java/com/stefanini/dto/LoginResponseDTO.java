package com.stefanini.dto;

import com.stefanini.entity.Jogador;

public class LoginResponseDTO {

	private Long id;
	private String nickname;
	private Boolean isLogged;
	
	public LoginResponseDTO() {
	}

	public LoginResponseDTO(Long id, String nickname, Boolean isLogged) {
		this.id = id;
		this.nickname = nickname;
		this.isLogged = isLogged;
	}
	
	public static LoginResponseDTO of(Jogador jogador) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setId(jogador.getId());
		loginResponseDTO.setNickname(jogador.getNickname());
		loginResponseDTO.setIsLogged(true);
		return loginResponseDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}
}
