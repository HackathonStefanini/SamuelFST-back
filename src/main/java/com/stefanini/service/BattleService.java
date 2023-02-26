package com.stefanini.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.stefanini.dto.BattleDTO;
import com.stefanini.dto.BattleRelatoryDTO;
import com.stefanini.entity.Stefamon;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.repository.StefamonRepository;

@ApplicationScoped
public class BattleService {
	
	@Inject
	StefamonRepository stefamonRepository;
	
	public BattleRelatoryDTO batalhar(BattleDTO battleDTO) {
		if (battleDTO.getStefamonsFirstPlayer().size() < 1 || battleDTO.getStefamonsFirstPlayer().size() > 6) {
			throw new RegraDeNegocioException("É necessário escolher ao menos 1 stefamon ou no máximo 6 stefamons", Response.Status.BAD_REQUEST);
		}
		
		if (battleDTO.getStefamonsSecondPlayer().size() == 0) {
			battleDTO.setIsBot(true);
			List<Stefamon> botStefamons = new ArrayList<>();
			
			for (int index = 0; index < 6; index++) {
				Long randomId = (long) (1 + (int) (Math.random() * 230));
				botStefamons.add(stefamonRepository.findById(randomId));
			}
			
			battleDTO.setStefamonsSecondPlayer(botStefamons);;
		}
		
		for (int i = 0; i < battleDTO.getStefamonsFirstPlayer().size(); i++) {
			Stefamon firstPlayerStefamon = battleDTO.getStefamonsFirstPlayer().get(i);
			Stefamon secondPlayerStefamon = battleDTO.getStefamonsSecondPlayer().get(i);
//			Integer totalFirstPlayerDamage;
//			Integer totalSecondPlayerDamage;
				
			if (secondPlayerStefamon == null) {
				secondPlayerStefamon = battleDTO.getStefamonsSecondPlayer().get(0);
			}
			
			Integer damage = secondPlayerStefamon.getVida() - firstPlayerStefamon.getAtaque();
			secondPlayerStefamon.setVida(damage);
//			totalFirstPlayerDamage = totalFirstPlayerDamage + damage;
			
			if (secondPlayerStefamon.getVida() > 0) {
				damage = firstPlayerStefamon.getVida() - secondPlayerStefamon.getAtaque();
				firstPlayerStefamon.setVida(damage);
//				totalFirstPlayerDamage = totalFirstPlayerDamage + damage;
			} else {
				battleDTO.getStefamonsSecondPlayer().remove(secondPlayerStefamon);
			}
			
			if (firstPlayerStefamon.getVida() < 0) {
				battleDTO.getStefamonsFirstPlayer().remove(firstPlayerStefamon);
			}
			
			if (battleDTO.getStefamonsFirstPlayer().size() == 0) {

			}
			
			if (battleDTO.getStefamonsSecondPlayer().size() == 0) {

			}
		}
		
		return null;
	}
}
