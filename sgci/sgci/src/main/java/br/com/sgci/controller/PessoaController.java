package br.com.sgci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgci.controller.dto.PessoaReqDTO;
import br.com.sgci.controller.dto.PessoaResponseDTO;
import br.com.sgci.model.Pessoa;
import br.com.sgci.service.PessoaService;
import jakarta.validation.Valid;

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
	public ResponseEntity<List<PessoaResponseDTO>> findAll(){
		return ResponseEntity.ok(pessoaService.findAll());
	}

}
