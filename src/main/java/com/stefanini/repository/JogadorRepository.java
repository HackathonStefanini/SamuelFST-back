package com.stefanini.repository;

import javax.enterprise.context.ApplicationScoped;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Jogador;

@ApplicationScoped
public class JogadorRepository extends GenericDAO<Jogador, Long> {
	
	public Jogador findByNickname(String nickname) {
		return createQuery("from Jogador where nickname = :nickname").setParameter("nickname", nickname).getSingleResult();
	}
}
