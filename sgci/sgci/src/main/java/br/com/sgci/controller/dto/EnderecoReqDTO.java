package br.com.sgci.controller.dto;

import jakarta.validation.constraints.NotNull;

public record EnderecoReqDTO(
		String cep,
		@NotNull
		String estado,
		@NotNull
		String cidade,
		String rua,
		String bairro,
		Integer numero) {

}
