package br.com.coder.cms.web.jsf;

import org.primefaces.model.LazyDataModel;

import br.com.coder.arqprime.model.dao.app.BaseDAO;
import br.com.coder.arqprime.model.entity.BaseEntity;


public class TabelaModel<T> extends LazyDataModel<T>{
	
	private static final long serialVersionUID = 1L;
	
	private BaseDAO<BaseEntity>dao; 

}
