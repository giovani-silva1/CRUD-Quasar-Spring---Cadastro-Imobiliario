package br.com.sgci.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.sgci.controller.dto.EnderecoResponseDTO;
import br.com.sgci.controller.dto.PessoaResponseDTO;
import br.com.sgci.model.Pessoa;

@Mapper
public interface PessoaMapper {

	PessoaMapper INSTANCE =  Mappers.getMapper(PessoaMapper.class);
	
	
	
	@Mapping(source = "enderecoResponseDTO", target = "endereco")
	PessoaResponseDTO toPessoaResponseDTO(Pessoa pessoa,EnderecoResponseDTO enderecoResponseDTO);
}
