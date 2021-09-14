package com.blogpessoalcinthia.blog.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class PostagemModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idPostagem;
	private @NotBlank String titulo;
	private @NotBlank String descricao;
	public Long getIdPostagem() {
		return idPostagem;
	}
	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToOne
	@JoinColumn(name = "origin_id")
	@JsonIgnoreProperties({"meusProdutos"})
	private PostagemModel origin;
}
