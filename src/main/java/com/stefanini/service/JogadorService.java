package com.stefanini.service;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.stefanini.dto.ComprarStefamonDTO;
import com.stefanini.dto.LoginRequestDTO;
import com.stefanini.dto.LoginResponseDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.entity.Stefamon;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.repository.JogadorRepository;
import com.stefanini.repository.StefamonRepository;

@ApplicationScoped
public class JogadorService {

	@Inject
	JogadorRepository jogadorRepository;
	@Inject
	StefamonRepository stefamonRepository;

	public List<Jogador> listarTodos() {
		return jogadorRepository.listAll();
	}

	public Jogador pegarPorId(Long id) {
		var jogador = jogadorRepository.findById(id);
		if (Objects.isNull(jogador)) {
			throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id,
					Response.Status.NOT_FOUND);
		}
		return jogador;
	}

	public void salvar(Jogador jogador) {
		validarSenhaJogador(jogador);
		validarQuantidadeStefamons(jogador);
		jogador.setSaldo(new BigDecimal(1000.00));
		jogadorRepository.save(jogador);
	}

	public LoginResponseDTO autenticar(LoginRequestDTO loginDTO) {
		validarDadosLogin(loginDTO);
		Jogador jogador = jogadorRepository.findByNickname(loginDTO.getNickname());
		String encondedInformedPassword = Base64.getEncoder().encodeToString(loginDTO.getPassword().getBytes());

		if (!encondedInformedPassword.equals(jogador.getPassword())) {
			throw new RegraDeNegocioException("A senha não corresponde", Response.Status.BAD_REQUEST);
		}

		return LoginResponseDTO.of(jogador);

	}

	public void alterar(Jogador jogador) {
		validarSenhaJogador(jogador);
		validarQuantidadeStefamons(jogador);
		jogadorRepository.update(jogador);
	}
	
	public void comprarStefamon(ComprarStefamonDTO comprarStefamonDTO) {
		Stefamon stefamon = stefamonRepository.findById(comprarStefamonDTO.getStefamonId());
		Jogador jogador = jogadorRepository.findById(comprarStefamonDTO.getJogadorId());
		String password = new String(Base64.getDecoder().decode(jogador.getPassword()));
		
		if (stefamon.getId() != null && jogador.getId() != null) {
			BigDecimal novoBalanco = jogador.getSaldo().subtract(new BigDecimal(comprarStefamonDTO.getValor()));
			List<Stefamon> stefamonsJogador = jogador.getStefamons();
			stefamonsJogador.add(stefamon);
			
			validarQuantidadeStefamons(jogador);
			
			if (novoBalanco.compareTo(BigDecimal.ZERO) > 0) {
				jogador.setPassword(password);
				jogador.setSaldo(novoBalanco);
				jogador.setStefamons(stefamonsJogador);
				jogadorRepository.update(jogador);
			} else {
				throw new RegraDeNegocioException("Saldo insuficiente para comprar esse stefamon");
			}
		} else {
			throw new RegraDeNegocioException("Dados do jogador ou do Stefamon incorretos");
		}
	}

	public void deletar(Long id) {
		jogadorRepository.delete(id);
	}

	private void validarSenhaJogador(Jogador jogador) {
		if (jogador.getPassword().length() < 4 || jogador.getPassword().length() > 10) {
			throw new RegraDeNegocioException("A senha deve ter entre 4 e 10 caracteres", Response.Status.BAD_REQUEST);
		}
	}
	
	private void validarQuantidadeStefamons(Jogador jogador) {
		if (jogador.getStefamons().size() < 1 || jogador.getStefamons().size() > 6) {
			throw new RegraDeNegocioException("É obrigatório no mínimo 1 stefamon e no máximo 6 stefamons");
		}
	}

	private void validarDadosLogin(LoginRequestDTO loginDTO) {
		if (loginDTO.getNickname() == "" || loginDTO.getPassword() == "") {
			throw new RegraDeNegocioException("O nickname e a senha devem ser informados", Response.Status.BAD_REQUEST);
		}
	}
}
