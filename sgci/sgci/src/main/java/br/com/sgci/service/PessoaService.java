package br.com.sgci.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgci.controller.dto.EnderecoResponseDTO;
import br.com.sgci.controller.dto.PessoaReqDTO;
import br.com.sgci.controller.dto.PessoaResponseDTO;
import br.com.sgci.controller.dto.PessoaUpdDTO;
import br.com.sgci.model.Endereco;
import br.com.sgci.model.Pessoa;
import br.com.sgci.model.mapper.EnderecoMapper;
import br.com.sgci.model.mapper.PessoaMapper;
import br.com.sgci.repository.PessoaRepository;
import br.com.sgci.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public Pessoa createPessoa(PessoaReqDTO pessoaReq) {
		Endereco endereco = new Endereco(pessoaReq.endereco().cep(), pessoaReq.endereco().estado(),
				pessoaReq.endereco().cidade(), pessoaReq.endereco().rua(), pessoaReq.endereco().bairro(),
				pessoaReq.endereco().numero());
		Pessoa pessoa = new Pessoa(endereco, pessoaReq.nome(), pessoaReq.tipo(), pessoaReq.documento(),
				pessoaReq.profissao(), pessoaReq.estadoCivil());
		pessoaRepository.save(pessoa);
		return pessoa;
	}

	@Transactional
	public void deletePessoa(Long id) {
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		pessoaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));

	}

	public List<PessoaResponseDTO> findAll() {
		List<PessoaResponseDTO> listPessoaResponseDTO = new ArrayList<>();
		List<Pessoa> pessoasEncontradasBD = pessoaRepository.findAll();
		pessoasEncontradasBD.forEach(item -> {
			EnderecoResponseDTO enderecoResponseDTO = EnderecoMapper.INSTANCE.toEnderecoResponseDTO(item.getEndereco());
			PessoaResponseDTO pessoaResponseDTO = PessoaMapper.INSTANCE.toPessoaResponseDTO(item, enderecoResponseDTO);
			listPessoaResponseDTO.add(pessoaResponseDTO);
		});

		return listPessoaResponseDTO;
	}
	
	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		pessoaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
		
		return pessoaEncontrada.get();
	}
	
	
	public PessoaResponseDTO findOne(Long id) {
		
		Pessoa pessoasEncontradasBD = pessoaRepository.findById(id).orElseThrow();
	
			EnderecoResponseDTO enderecoResponseDTO = EnderecoMapper.INSTANCE.toEnderecoResponseDTO(pessoasEncontradasBD.getEndereco());
			PessoaResponseDTO pessoaResponseDTO = PessoaMapper.INSTANCE.toPessoaResponseDTO(pessoasEncontradasBD, enderecoResponseDTO);
			return pessoaResponseDTO;
	}
	

	@Transactional
	public Pessoa updatePessoa(@Valid PessoaUpdDTO pessoaUpdDTO, Long id) {
		 Pessoa pessoa = findById(id);
		 PessoaService.atualizarPessoa(pessoa, pessoaUpdDTO);
		 pessoaRepository.save(pessoa);
		 return pessoa;
		 
		
		
	}
	
	public static void atualizarPessoa(Pessoa pessoa , PessoaUpdDTO pessoaUpdDTO) {
		pessoa.getEndereco().setCep(pessoaUpdDTO.endereco().cep());
		pessoa.getEndereco().setEstado(pessoaUpdDTO.endereco().estado());
		pessoa.getEndereco().setCidade(pessoaUpdDTO.endereco().cidade());
		pessoa.getEndereco().setRua(pessoaUpdDTO.endereco().rua());
		pessoa.getEndereco().setBairro(pessoaUpdDTO.endereco().bairro());
		pessoa.getEndereco().setNumero(pessoaUpdDTO.endereco().numero());
		
		pessoa.setDocumento(pessoaUpdDTO.documento());
		pessoa.setEstadoCivil(pessoaUpdDTO.estadoCivil());
		pessoa.setNome(pessoaUpdDTO.nome());
		pessoa.setProfissao(pessoaUpdDTO.profissao());
		pessoa.setTipo(pessoaUpdDTO.tipo());
		
		
	}
	

}
