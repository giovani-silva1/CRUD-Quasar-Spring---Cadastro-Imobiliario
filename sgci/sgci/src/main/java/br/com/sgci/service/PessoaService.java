package br.com.sgci.service;

import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgci.controller.dto.PessoaReqDTO;
import br.com.sgci.model.Endereco;
import br.com.sgci.model.Pessoa;
import br.com.sgci.repository.PessoaRepository;
import br.com.sgci.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa createPessoa(PessoaReqDTO  pessoaReq) {
		Endereco endereco = new Endereco(pessoaReq.endereco().cep(),pessoaReq.endereco().estado() ,pessoaReq.endereco().cidade(),pessoaReq.endereco().rua(),pessoaReq.endereco().bairro(),pessoaReq.endereco().numero());
		Pessoa pessoa = new Pessoa(endereco, pessoaReq.nome(),pessoaReq.tipo(),pessoaReq.documento(),pessoaReq.profissao(),pessoaReq.estadoCivil());
		pessoaRepository.save(pessoa);
		return pessoa;
	}

	@Transactional
	public void deletePessoa(Long id) {
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		 pessoaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
		
		
		
		
		
	}
	
	
	
	
	
}
