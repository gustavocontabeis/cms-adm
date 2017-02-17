package br.com.coder.cms.web.jsf.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.coder.arqprime.model.entity.Arquivo;
import br.com.coder.cms.model.dao.BannerDAO;
import br.com.coder.cms.model.entity.Banner;

@Named
@SessionScoped
public class BannerManagedBean extends CrudManagedBean<Banner, BannerDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private StreamedContent image;

	@Inject
	private BannerDAO dao;

	@PostConstruct
	private void init() {
	}

	@Override
	protected BannerDAO getDao() {
		return dao;
	}

	@Override
	protected Banner novo() {
		entity = new Banner();
		return entity;
	}

	@Override
	protected void buscarApos(Banner entity) {
		byte[] data = entity.getArquivo().getData();
		InputStream is = new ByteArrayInputStream(data);
		image = new DefaultStreamedContent(is, "image/jpeg");
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			UploadedFile file = event.getFile();
			byte[] contents = file.getContents();
			long size = file.getSize();
			String fileName = file.getFileName();
			String contentType = file.getContentType();

			image = new DefaultStreamedContent(file.getInputstream(), "image/jpeg");

			Arquivo arquivo = new Arquivo();

			arquivo.setId(null);
			arquivo.setData(contents);
			arquivo.setExtencao(fileName.substring(fileName.indexOf(".")));
			arquivo.setNome(fileName);
			arquivo.setTamanho(size);
			arquivo.setContentType(contentType);
			entity.setArquivo(arquivo);

		} catch (Exception e) {
			e.printStackTrace();
			message(e);
		}
	}
	
	public StreamedContent getImage() {
		return this.image;
	}
	
}
