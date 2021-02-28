package com.capgemini.model.out;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PixOut {
	
	private Long id;
	
	private String nomeDestinatario;
	
	private String cpf;
	
	private String instituicaoBancaria;
	
	private String chavePix;
	
	private BigDecimal valor;

	private LocalDateTime dataPagamento;

	private String descricao;

	private BigDecimal porcentagem;
	
	private ClienteOut cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(String instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

	public ClienteOut getCliente() {
		return cliente;
	}

	public void setCliente(ClienteOut cliente) {
		this.cliente = cliente;
	}

	
}
