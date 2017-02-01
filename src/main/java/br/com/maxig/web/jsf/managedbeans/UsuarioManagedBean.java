package br.com.maxig.web.jsf.managedbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maxig.model.dao.DaoException;
import br.com.maxig.model.dao.usuarios.PerfilAcessoDAO;
import br.com.maxig.model.dao.usuarios.UsuarioDAO;
import br.com.maxig.model.entity.BaseEntity;
import br.com.maxig.model.entity.usuarios.PerfilAcesso;
import br.com.maxig.model.entity.usuarios.Usuario;
import br.com.maxig.model.utils.GenerateMD5;

@Named @ViewScoped
public class UsuarioManagedBean extends CrudManagedBean<Usuario, UsuarioDAO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<PerfilAcesso> perfisAcesso;
	private String[] selecionados;
	private String confirmeSenha, senhaAnterior;
	private boolean trocarSenha = false;
	
	@Inject private UsuarioDAO dao;// = new UsuarioDAO();
	@Inject private PerfilAcessoDAO perfilAcessoDAO;// = new PerfilAcessoDAO();
	
	@PostConstruct
	private void init() {
		perfisAcesso = perfilAcessoDAO.buscarTodos();
		//novo();
	}

	@Override
	protected Usuario novo() {
		selecionados = null;
		entity = new Usuario(); 
		return entity;
	}
	
	@Override
	protected boolean salvarAntes(Usuario entity) {
		if(trocarSenha){
			return true;
		}
		String perfis = "";
		for(String selecionado : selecionados){
			perfis += selecionado+",";
		}
		entity.setPerfis(perfis);
		entity.setDtSenha(new Date());
		entity.setSenha(GenerateMD5.generateMD5(entity.getSenha()));
		return true;
	}
	
	@Override
	protected BaseEntity buscarAntes(Usuario entity, String rowKey) {
		selecionados = null;
		return super.buscarAntes(entity, rowKey);
	}
	
	@Override
	protected void buscarApos(Usuario entity) {
		selecionados = entity.getPerfisArray();
	}
	
	public void trocarSenha(ActionEvent evt) throws DaoException {
		trocarSenha = true;
		Usuario buscar = dao.buscar(entity.getId());
		String generateMD5 = GenerateMD5.generateMD5(senhaAnterior);
		if(!generateMD5.equals(buscar.getSenha())){
			messageError(null, "A senha anterior não esta correta.");
			return;
		}
		generateMD5 = GenerateMD5.generateMD5(confirmeSenha);
		if(!generateMD5.equals(GenerateMD5.generateMD5(entity.getSenha()))){
			messageError(null, "As senhas não estão corretas.");
			return;
		}
		entity.setSenha(generateMD5);
		salvar(null);
	}
	
	@Override
	protected UsuarioDAO getDao() {
		return dao;
	}

	public List<PerfilAcesso> getPerfisAcesso() {
		return perfisAcesso;
	}

	public void setPerfisAcesso(List<PerfilAcesso> perfisAcesso) {
		this.perfisAcesso = perfisAcesso;
	}

	public String[] getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(String[] selecionados) {
		this.selecionados = selecionados;
	}

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}

	public String getSenhaAnterior() {
		return senhaAnterior;
	}

	public void setSenhaAnterior(String senhaAnterior) {
		this.senhaAnterior = senhaAnterior;
	}

	
}