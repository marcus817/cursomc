package com.marcus.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.marcus.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull(message="Campo não pode ser vazio")
	@Size(min=4, max=80, message="Nome não pode ser menor que 2 e conter no máximo 10 caracteres")
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
