package br.com.maxig.model.entity;

import java.util.*;
import java.math.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@Entity @Table(name="pagina")
@NamedQueries(value={
		@NamedQuery(name="Pagina-list", query="select obj from Pagina obj "),
		@NamedQuery(name="Pagina-porId", query="select obj from Pagina obj where obj.id=:id")
	})
public class Pagina extends BaseEntity {
	
	private static final long serialVersionUID = 1L;


	@Id 
	@GeneratedValue(generator="seq_pagina", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="seq_pagina", sequenceName="seq_pagina") 
	@Column(name="id_pagina") 
	private Long id;

	@NotEmpty 
	@Column(name="titulo", length=80, nullable=false)
	private String titulo;

	@NotEmpty 
	@Column(name="slug", length=50, nullable=false)
	private String slug;

	//@NotEmpty 
	@Column(name="descricao", length=255, nullable=true)
	private String descricao;

	@NotEmpty 
	@Column(name="conteudo", nullable=false, columnDefinition="LONGVARCHAR")
	private String conteudo;

	@NotEmpty 
	@Column(name="intro", nullable=false, columnDefinition="LONGVARCHAR")
	private String intro;

	@NotEmpty 
	@Column(name="keywords", length=100, nullable=false)
	private String keywords;

	@NotNull 
	@Enumerated(EnumType.STRING) 
	@Column(name="tipo", nullable=false)
	private TipoPagina tipo;

	@Column(name="contem_comentario", length=1, nullable=false)
	private Boolean contemComentario;

	@Column(name="ativo", length=1, nullable=false)
	private Boolean ativo;

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getTitulo(){
		return this.titulo;
	}
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	public String getSlug(){
		return this.slug;
	}
	public void setSlug(String slug){
		this.slug = slug;
	}
	public String getDescricao(){
		return this.descricao;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public String getConteudo(){
		return this.conteudo;
	}
	public void setConteudo(String conteudo){
		this.conteudo = conteudo;
	}
	public String getIntro(){
		return this.intro;
	}
	public void setIntro(String intro){
		this.intro = intro;
	}
	public String getKeywords(){
		return this.keywords;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public TipoPagina getTipo(){
		return this.tipo;
	}
	public void setTipo(TipoPagina tipo){
		this.tipo = tipo;
	}
	public Boolean getContemComentario(){
		return this.contemComentario;
	}
	public void setContemComentario(Boolean contemComentario){
		this.contemComentario = contemComentario;
	}
	public Boolean getAtivo(){
		return this.ativo;
	}
	public void setAtivo(Boolean ativo){
		this.ativo = ativo;
	}

}

