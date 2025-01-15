package br.com.sgci.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sgci.controller.dto.EnderecoResponseDTO;
import br.com.sgci.model.Endereco;

@Mapper
public interface EnderecoMapper {

	EnderecoMapper INSTANCE =  Mappers.getMapper(EnderecoMapper.class);
	
	
	EnderecoResponseDTO toEnderecoResponseDTO(Endereco endereco);
}
