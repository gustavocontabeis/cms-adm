package br.com.coder.cms.web.jsf.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.coder.arqprime.model.dao.DaoException;
import br.com.coder.arqprime.model.entity.usuarios.PerfilAcesso;
import br.com.coder.arqprime.model.utils.Filtro;
import br.com.coder.cms.model.dao.app.usuarios.PerfilAcessoDAO;

@ManagedBean
@ViewScoped
public class PerfilManagedBean extends BaseManagedBean {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private PerfilAcesso perfil;
	private PerfilAcessoDAO dao;
	protected LazyDataModel<PerfilAcesso> model;
	protected Filtro filtro;

	@PostConstruct
	private void init() {
		dao = new PerfilAcessoDAO();
		novo(null);
		loadLazyModel();
	}

	private void loadLazyModel() {
		model = new LazyDataModel<PerfilAcesso>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<PerfilAcesso> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro = new Filtro(PerfilAcesso.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(dao.getQuantidade(filtro));
				List<PerfilAcesso> buscar = dao.buscar(filtro);
				return buscar;
			}
			
			@Override
			public PerfilAcesso getRowData(String rowKey) {
				try {
					return dao.buscar(new Long(rowKey));
				} catch (NumberFormatException | DaoException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	public void novo(ActionEvent evt) {
		perfil = new PerfilAcesso();
	}

	public void salvar(ActionEvent evt) throws DaoException {
		try {
			dao.salvar(perfil);
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public void excluir(ActionEvent evt) throws DaoException {
		try {
			dao.excluir(perfil);
			perfil = new PerfilAcesso();
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}

	public LazyDataModel<PerfilAcesso> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<PerfilAcesso> model) {
		this.model = model;
	}

}
