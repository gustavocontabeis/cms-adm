package br.com.maxig.web.jsf;

import org.primefaces.model.LazyDataModel;

import br.com.coder.arqprime.model.dao.BaseDAO;
import br.com.coder.arqprime.model.entity.BaseEntity;


public class TabelaModel<T> extends LazyDataModel<T>{
	
	private BaseDAO<BaseEntity>dao; 

}
