package br.com.sgci.controller.dto;

public record EnderecoResponseDTO(
		String cep,
		String estado,
		String cidade,
		String rua,
		String bairro,
		Integer numero) {

}
