package br.com.maxig.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.maxig.model.entity.Configuracao;

public class ConfiguracaoDAO extends BaseDAO<Configuracao> {
	
 	private static final long serialVersionUID = 1L;
 	
 	public Configuracao buscarPorChave(String chave) throws DaoException {
 		Session session = getSession();
 		Query query = session.getNamedQuery("Configuracao-buscarPorChave");
 		query.setString("chave", chave);
 		Configuracao singleResult = null;
		try {
			singleResult = (Configuracao) query.getSingleResult();
			return singleResult;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}finally {
			session.close();
		}
 	}

	public void salvarConfiguracao(String chave, String valor) throws DaoException {
 		Session session = getSession();
 		Query query = session.getNamedQuery("Configuracao-buscarPorChave");
 		query.setString("chave", chave);
 		Configuracao singleResult = null;
		try {
			singleResult = (Configuracao) query.getSingleResult();
			singleResult.setValor(valor);
			salvar(singleResult);
		} catch (javax.persistence.NoResultException e) {
			singleResult = new Configuracao();
			singleResult.setChave(chave);
			singleResult.setValor(valor);
			salvar(singleResult);
		}finally {
			session.close();
		}
	}
	
}
 