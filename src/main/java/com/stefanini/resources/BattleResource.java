package com.stefanini.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.stefanini.dto.BattleDTO;
import com.stefanini.dto.BattleRelatoryDTO;
import com.stefanini.service.BattleService;

@Path("/battles")
public class BattleResource {

	@Inject
	BattleService battleService;
	
	@POST
	public BattleRelatoryDTO batalhar(BattleDTO battleDTO) {
		return battleService.batalhar(battleDTO);
	}
}
