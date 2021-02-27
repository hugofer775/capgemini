package com.capgemini.model.in;

import java.math.BigDecimal;

public class PixIn {
	
	private String nomeDestinatario;
	
	private String cpf;
	
	private String instituicaoBancaria;
	
	private String chavePix;
	
	private BigDecimal valor;
	
	private String descricao;
	
	private Long clienteId;
	
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
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
}
