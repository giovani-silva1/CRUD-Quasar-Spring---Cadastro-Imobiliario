package br.com.sgci.controller.dto;

import br.com.sgci.model.enums.EstadoCivil;
import br.com.sgci.model.enums.TipoPessoa;
import jakarta.validation.constraints.NotNull;

public record PessoaReqDTO (
		@NotNull
		String nome,
		@NotNull
		EnderecoReqDTO endereco, 
		@NotNull
		TipoPessoa tipo,
		@NotNull
		String documento,
		@NotNull
		String profissao,
		@NotNull
		EstadoCivil estadoCivil) {

}
