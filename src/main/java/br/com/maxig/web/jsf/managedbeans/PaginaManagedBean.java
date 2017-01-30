package br.com.maxig.web.jsf.managedbeans;


import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maxig.model.dao.PaginaDAO;
import br.com.maxig.model.entity.Pagina;
import br.com.maxig.model.entity.TipoPagina;

@Named @ViewScoped
public class PaginaManagedBean extends CrudManagedBean<Pagina, PaginaDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	@Inject private PaginaDAO dao;

	@PostConstruct
	private void init() {
	}

	@Override
	protected PaginaDAO getDao() {
		return dao;
	}
	
	@Override
	protected Pagina novo() {
		entity = new Pagina();
		return entity;
	}
	
	public TipoPagina[] getPopularComboTipoPagina(){
		return TipoPagina.values();
	}

}
