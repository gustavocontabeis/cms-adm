package br.com.coder.cms.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.coder.arqprime.model.entity.Arquivo;
import br.com.coder.arqprime.model.entity.BaseEntity;

@XmlRootElement
@Entity @Table(name="galeria")
@NamedQueries(value={
		@NamedQuery(name="Galeria-list", query="select obj from Galeria obj "),
		@NamedQuery(name="Galeria-porId", query="select obj from Galeria obj left join fetch obj.arquivos arq where obj.id=:id")
})
public class Galeria extends BaseEntity {
	
	private static final long serialVersionUID = 1L;


	@Id 
	@GeneratedValue(generator="seq_galeria", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="seq_galeria", sequenceName="seq_galeria") 
	@Column(name="id_galeria") 
	private Long id;

	@NotEmpty 
	@Column(name="titulo", length=80, nullable=false)
	private String titulo;

	@NotEmpty 
	@Column(name="slug", length=50, nullable=false)
	private String slug;

	@NotEmpty 
	@Column(name="descricao", length=255, nullable=false)
	private String descricao;

	@NotNull 
	//@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Arquivo> arquivos;

	@NotNull 
	@Enumerated(EnumType.STRING) 
	@Column(name="tipo", nullable=false, length=15)
	private TipoGaleria tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public TipoGaleria getTipo() {
		return tipo;
	}

	public void setTipo(TipoGaleria tipo) {
		this.tipo = tipo;
	}
	
}

