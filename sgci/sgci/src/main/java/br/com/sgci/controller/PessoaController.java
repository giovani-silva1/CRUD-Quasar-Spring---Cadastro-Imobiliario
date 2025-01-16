package br.com.sgci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgci.controller.dto.PessoaFilterDTO;
import br.com.sgci.controller.dto.PessoaReqDTO;
import br.com.sgci.controller.dto.PessoaResponseDTO;
import br.com.sgci.controller.dto.PessoaUpdDTO;
import br.com.sgci.controller.dto.ResponsePagedCommomDTO;
import br.com.sgci.model.Pessoa;
import br.com.sgci.service.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Pessoa::Pessoa")
@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@PostMapping()
	public ResponseEntity<Long> save(@Valid @RequestBody PessoaReqDTO pessoaRequest) {
		Pessoa pessoa = pessoaService.createPessoa(pessoaRequest);
		return ResponseEntity.ok(pessoa.getId());
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		pessoaService.deletePessoa(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<ResponsePagedCommomDTO<PessoaResponseDTO>> findAll(@Valid PessoaFilterDTO filtros){
		return ResponseEntity.ok(pessoaService.findAll(filtros));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Long> save(@Valid @RequestBody PessoaUpdDTO pessoaUpdDTO , @PathVariable Long id ) {
		Pessoa pessoa = pessoaService.updatePessoa(pessoaUpdDTO, id );
		return ResponseEntity.ok(pessoa.getId());
	}
	
	@GetMapping(value = "/{id}" )
	public ResponseEntity<PessoaResponseDTO> findAll(@PathVariable Long id){
		
		return ResponseEntity.ok(pessoaService.findOne(id));
	}

}
