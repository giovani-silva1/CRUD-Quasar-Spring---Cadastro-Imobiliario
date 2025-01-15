package br.com.sgci.model;

import java.io.Serializable;
import java.util.Objects;

import br.com.sgci.model.enums.EstadoCivil;
import br.com.sgci.model.enums.TipoPessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA")
	private Long id;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "ID_ENDERECO")
	private Endereco endereco;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "EN_TIPO")
	private TipoPessoa tipo;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "DOCUMENTO" , unique=true)
	private String documento;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "TX_PROFISSAO")
	private String profissao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "EN_ESTADO_CIVIL")
	private EstadoCivil estadoCivil;

	
	public Pessoa() {
		
	}


	public Pessoa(Endereco endereco, @NotNull @Size(max = 255) String nome, @NotNull TipoPessoa tipo,
			@NotNull @Size(max = 255) String documento, @NotNull @Size(max = 255) String profissao,
			@NotNull EstadoCivil estadoCivil) {
		super();
		this.endereco = endereco;
		this.nome = nome;
		this.tipo = tipo;
		this.documento = documento;
		this.profissao = profissao;
		this.estadoCivil = estadoCivil;
	}

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoPessoa getTipo() {
		return tipo;
	}


	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}


	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	
}