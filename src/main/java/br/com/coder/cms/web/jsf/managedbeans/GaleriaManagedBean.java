package br.com.coder.cms.web.jsf.managedbeans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.coder.arqprime.model.dao.DaoException;
import br.com.coder.arqprime.model.entity.Arquivo;
import br.com.coder.cms.model.dao.GaleriaDAO;
import br.com.coder.cms.model.dao.app.ArquivoDAO;
import br.com.coder.cms.model.entity.Galeria;
import br.com.coder.cms.model.entity.TipoGaleria;
import br.com.coder.cms.web.jsf.managedbeans.app.CrudManagedBean;

@Named
@SessionScoped
public class GaleriaManagedBean extends CrudManagedBean<Galeria, GaleriaDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private GaleriaDAO dao;
	@Inject
	private ArquivoDAO arquivoDAO;

	@PostConstruct
	private void init() {
	}

	@Override
	protected GaleriaDAO getDao() {
		return dao;
	}

	@Override
	protected Galeria novo() {
		entity = new Galeria();
		entity.setArquivos(new ArrayList<Arquivo>());
		return entity;
	}

	public TipoGaleria[] getPopularComboTipoGaleria() {
		return TipoGaleria.values();
	}

	public void atualizar(Arquivo arquivo) throws DaoException {
		arquivoDAO.salvar(arquivo);
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			UploadedFile file = event.getFile();
			byte[] contents = file.getContents();
			long size = file.getSize();
			String fileName = file.getFileName();
			String contentType = file.getContentType();

			Arquivo arquivo = new Arquivo();

			arquivo.setId(null);
			arquivo.setData(contents);
			arquivo.setExtencao(fileName.substring(fileName.indexOf(".")));
			arquivo.setNome(fileName);
			arquivo.setTamanho(size);
			arquivo.setContentType(contentType);

			if (entity.getArquivos() == null) {
				entity.setArquivos(new ArrayList<Arquivo>());
			}

			entity.getArquivos().add(arquivo);
			salvar(null);

		} catch (Exception e) {
			e.printStackTrace();
			message(e);
		}
	}
	
	public int getQuantidadesDeArquivos() throws DaoException {
		return entity!=null
				&& entity.getArquivos()!=null ? entity.getArquivos().size() : 0;
	}
	

}
