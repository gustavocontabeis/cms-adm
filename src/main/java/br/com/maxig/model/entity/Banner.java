package br.com.maxig.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@Entity @Table(name="banner")
@NamedQueries(value={
		@NamedQuery(name="Banner-list", query="select obj from Banner obj "),
		@NamedQuery(name="Banner-porId", query="select obj from Banner obj inner join fetch obj.arquivo arq where obj.id=:id")
	})
public class Banner extends BaseEntity {
	
	private static final long serialVersionUID = 1L;


	@Id 
	@GeneratedValue(generator="seq_banner", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="seq_banner", sequenceName="seq_banner") 
	@Column(name="id_banner") 
	private Long id;

	@NotEmpty 
	@Column(name="titulo", length=80, nullable=false)
	private String titulo;

	@NotEmpty 
	@Column(name="slug", length=50, nullable=false)
	private String slug;

	@Column(name="descricao", length=255, nullable=true)
	private String descricao;

	@NotEmpty 
	@Column(name="acao_click", length=60, nullable=false)
	private String acaoClick;

	@Column(name="ativo", length=1, nullable=false)
	private Boolean ativo;

	@NotNull 
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@JoinColumn(name="id_arquivo", nullable=false, foreignKey=@ForeignKey(name="banner_arquivo_fk"))
	private Arquivo arquivo;

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
	public String getAcaoClick(){
		return this.acaoClick;
	}
	public void setAcaoClick(String acaoClick){
		this.acaoClick = acaoClick;
	}
	public Boolean getAtivo(){
		return this.ativo;
	}
	public void setAtivo(Boolean ativo){
		this.ativo = ativo;
	}
	public Arquivo getArquivo(){
		return this.arquivo;
	}
	public void setArquivo(Arquivo arquivo){
		this.arquivo = arquivo;
	}

}

