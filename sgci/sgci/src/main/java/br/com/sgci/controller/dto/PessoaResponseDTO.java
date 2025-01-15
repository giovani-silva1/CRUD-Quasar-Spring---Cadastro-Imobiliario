package br.com.sgci.controller.dto;

import br.com.sgci.model.enums.EstadoCivil;
import br.com.sgci.model.enums.TipoPessoa;

public record PessoaResponseDTO (
		
		String nome,
		
		EnderecoReqDTO endereco, 
		
		TipoPessoa tipo,
		
		String documento,
		
		String profissao,
	
		EstadoCivil estadoCivil) {

}
