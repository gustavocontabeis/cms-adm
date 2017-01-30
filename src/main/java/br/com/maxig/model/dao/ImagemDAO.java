//ADICIONAR O PACOTE!<bean id="imagemDAO" class="ImagemDAO"></bean>
package br.com.maxig.model.dao;

import javax.inject.Named;

import br.com.maxig.model.entity.Arquivo;

@Named
public class ImagemDAO extends BaseDAO<Arquivo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}

