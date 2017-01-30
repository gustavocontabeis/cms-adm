package br.com.maxig.model.dao.usuarios;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;

import br.com.maxig.model.dao.BaseDAO;
import br.com.maxig.model.entity.usuarios.PerfilAcesso;


@Named
public class PerfilAcessoDAO extends BaseDAO<PerfilAcesso> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	public List<PerfilAcesso> buscarTodos() {
		Session session = getSession();
		List list = session.getNamedQuery("todosPerfilAcesso").list();
		session.close();
		return list;
	}

}

