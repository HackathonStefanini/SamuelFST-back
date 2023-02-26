package com.stefanini.dto;

import java.util.List;

import com.stefanini.entity.Stefamon;

public class BattleDTO {
	List<Stefamon> stefamonsFirstPlayer;
	List<Stefamon> stefamonsSecondPlayer;
	boolean isBot;
	
	public BattleDTO() {
	}

	public BattleDTO(List<Stefamon> stefamonsFirstPlayer, List<Stefamon> stefamonsSecondPlayer, boolean isBot) {
		this.stefamonsFirstPlayer = stefamonsFirstPlayer;
		this.stefamonsSecondPlayer = stefamonsSecondPlayer;
		this.isBot = isBot;
	}

	public List<Stefamon> getStefamonsFirstPlayer() {
		return stefamonsFirstPlayer;
	}

	public void setStefamonsFirstPlayer(List<Stefamon> stefamonsFirstPlayer) {
		this.stefamonsFirstPlayer = stefamonsFirstPlayer;
	}

	public List<Stefamon> getStefamonsSecondPlayer() {
		return stefamonsSecondPlayer;
	}

	public void setStefamonsSecondPlayer(List<Stefamon> stefamonsSecondPlayer) {
		this.stefamonsSecondPlayer = stefamonsSecondPlayer;
	}

	public boolean getIsBot() {
		return isBot;
	}

	public void setIsBot(boolean isBot) {
		this.isBot = isBot;
	}
}
