package br.com.coder.cms.web.jsf.managedbeans;


import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.coder.cms.model.dao.PaginaDAO;
import br.com.coder.cms.model.entity.Pagina;
import br.com.coder.cms.model.entity.TipoPagina;
import br.com.coder.cms.web.jsf.managedbeans.app.CrudManagedBean;

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
