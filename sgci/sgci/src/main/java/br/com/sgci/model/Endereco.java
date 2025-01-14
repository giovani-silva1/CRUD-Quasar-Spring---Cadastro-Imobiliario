package br.com.sgci.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ENDERECO")
	private Integer id;

	
	@Size(max = 8)
	@Column(name = "CEP")
	private String cep;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "ESTADO")
	private String estado;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "CIDADE")
	private String cidade;
	
	
	@Size(max = 255)
	@Column(name = "RUA")
	private String rua;

	
	@Size(max = 255)
	@Column(name = "BAIRRO")
	private String bairro;

	
	@Column(name = "NUMERO")
	private Integer numero;

	public Endereco() {

	}

	public Endereco(Integer id, @Size(max = 8) String cep, @Size(max = 255) String estado,
			@Size(max = 255) String cidade, @Size(max = 255) String rua, @Size(max = 255) String bairro,
			Integer numero) {
		super();
		this.id = id;
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}

}