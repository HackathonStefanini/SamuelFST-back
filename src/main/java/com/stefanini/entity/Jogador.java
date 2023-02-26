package com.stefanini.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "tb_jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "O nickname é obrigatório")
    private String nickname;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "A senha é obrigatória")
    private String password;

    @Column(name = "balance", nullable = false)
    private BigDecimal saldo;

    @ManyToMany
    @JoinTable(name = "Jogador_Stefamon",
            joinColumns = {@JoinColumn(name = "IdJogador")},
            inverseJoinColumns = {@JoinColumn(name = "IdStefamon")})
    private List<Stefamon> stefamons = new ArrayList<>();

    @PrePersist
	public void prePersist() {
		password = Base64.getEncoder().encodeToString(password.getBytes());
	}
    
    @PreUpdate
	public void preUpdate() {
		password = Base64.getEncoder().encodeToString(password.getBytes());
	}
     
    public Jogador() {
    }

	public Jogador(Long id, @NotEmpty(message = "O nickname é obrigatório") String nickname,
			@NotEmpty(message = "A senha é obrigatória") String password, BigDecimal saldo, List<Stefamon> stefamons) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.saldo = saldo;
		this.stefamons = stefamons;
	}

//	public static Jogador of(JogadorDTO jogadorDTO) {
//		Jogador jogador = new Jogador();
//		jogador.setNickname(jogadorDTO.getNickname());
//		jogador.setPassword(jogadorDTO.getPassword());
//		jogador.setSaldo(jogadorDTO.getSaldo());
//		jogador.setStefamons(jogadorDTO.getStefamons());
//		return jogador;
//	}
//	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Stefamon> getStefamons() {
		return stefamons;
	}

	public void setStefamons(List<Stefamon> list) {
		this.stefamons = list;
	}
}
