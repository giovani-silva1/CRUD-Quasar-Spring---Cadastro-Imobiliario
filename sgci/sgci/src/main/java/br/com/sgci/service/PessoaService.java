package br.com.sgci.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.sgci.controller.dto.EnderecoResponseDTO;
import br.com.sgci.controller.dto.PessoaFilterDTO;
import br.com.sgci.controller.dto.PessoaReqDTO;
import br.com.sgci.controller.dto.PessoaResponseDTO;
import br.com.sgci.controller.dto.PessoaUpdDTO;
import br.com.sgci.controller.dto.ResponsePagedCommomDTO;
import br.com.sgci.model.Endereco;
import br.com.sgci.model.Pessoa;
import br.com.sgci.model.mapper.EnderecoMapper;
import br.com.sgci.model.mapper.PessoaMapper;
import br.com.sgci.repository.PessoaRepository;
import br.com.sgci.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

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

	public ResponsePagedCommomDTO<PessoaResponseDTO> findAll(@Valid PessoaFilterDTO filtros) {
	
		List<Predicate> condicoes = new ArrayList<>();

		Specification<Pessoa> filtrosCustomizados = (root, query, cb) -> {

			if (filtros.getNome() != null) {
				condicoes.add(cb.like(root.get("nome"), "%" + filtros.getNome() + "%"));
			}
			
			if (filtros.getCep()!= null) {
				condicoes.add(cb.like(root.get("endereco").get("cep"),filtros.getCep()));
			}
			
			if (filtros.getEstado() != null) {
				condicoes.add(cb.like(root.get("endereco").get("estado"),filtros.getEstado()));
			}
			
			if (filtros.getCidade() != null) {
				condicoes.add(cb.like(root.get("endereco").get("cidade"),filtros.getCidade()));
			}
			
			if (filtros.getTipo() != null) {
				condicoes.add(cb.equal(root.get("tipo"), filtros.getTipo()));
			}

			if (filtros.getDocumento() != null) {
				condicoes.add(cb.equal(root.get("documento"), filtros.getDocumento()));
			}

			return cb.and(condicoes.toArray(Predicate[]::new));

		};
		List<PessoaResponseDTO> listPessoaResponseDTO = new ArrayList<>();
		Page<Pessoa> pessoasEncontradasBD = pessoaRepository.findAll(filtrosCustomizados,PageRequest.of(filtros.getPage(),filtros.getSize(),Sort.by(filtros.getDirection(),filtros.getOrdernarPor())));

		pessoasEncontradasBD.forEach(item -> {
			EnderecoResponseDTO enderecoResponseDTO = EnderecoMapper.INSTANCE.toEnderecoResponseDTO(item.getEndereco());
			PessoaResponseDTO pessoaResponseDTO = PessoaMapper.INSTANCE.toPessoaResponseDTO(item, enderecoResponseDTO);
			listPessoaResponseDTO.add(pessoaResponseDTO);
		});

		return new ResponsePagedCommomDTO<PessoaResponseDTO>(listPessoaResponseDTO,pessoasEncontradasBD.getTotalElements(),pessoasEncontradasBD.getTotalPages(),filtros.getSize());
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		pessoaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));

		return pessoaEncontrada.get();
	}

	public PessoaResponseDTO findOne(Long id) {

		Pessoa pessoasEncontradasBD = pessoaRepository.findById(id).orElseThrow();

		EnderecoResponseDTO enderecoResponseDTO = EnderecoMapper.INSTANCE
				.toEnderecoResponseDTO(pessoasEncontradasBD.getEndereco());
		PessoaResponseDTO pessoaResponseDTO = PessoaMapper.INSTANCE.toPessoaResponseDTO(pessoasEncontradasBD,
				enderecoResponseDTO);
		return pessoaResponseDTO;
	}

	@Transactional
	public Pessoa updatePessoa(@Valid PessoaUpdDTO pessoaUpdDTO, Long id) {
		Pessoa pessoa = findById(id);
		PessoaService.atualizarPessoa(pessoa, pessoaUpdDTO);
		pessoaRepository.save(pessoa);
		return pessoa;

	}

	public static void atualizarPessoa(Pessoa pessoa, PessoaUpdDTO pessoaUpdDTO) {
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
