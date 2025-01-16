package br.com.sgci.controller.dto;

import br.com.sgci.model.enums.TipoPessoa;

public class PessoaFilterDTO extends FilterPageableDTO {
	private String nome;
	private String cep;
	private String estado;
	private String cidade;
	private TipoPessoa tipo;
	private String documento;
	
	public PessoaFilterDTO() {
		
	}

	public PessoaFilterDTO(String nome, String cep, String estado, String cidade, TipoPessoa tipo, String documento) {
		super();
		this.nome = nome;
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
		this.tipo = tipo;
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	
	
}
		
		

