package br.com.coder.cms.web.jsf.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.coder.arqprime.model.utils.I18nUtils;

public class BaseManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	@ManagedProperty(value="#{loginManagedBean}")
	protected LoginManagedBean loginBean;

	public LoginManagedBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginManagedBean loginBean) {
		this.loginBean = loginBean;
	}
	
	protected void message(Exception e) {
		message(null, e);
	}

	protected void message(String component, Exception exception) {
		Throwable error = exception;
		while(error.getCause()!=null && error.getLocalizedMessage() != null){
			error = error.getCause();
		}
		messageError(component, error.getLocalizedMessage());
	}

	protected void message(String component, String msg) {
		msg = I18nUtils.getMessage(msg); 
		FacesContext.getCurrentInstance().addMessage(component, new FacesMessage(msg));
	}

	protected void messageError(String component, String msg) {
		msg = I18nUtils.getMessage(msg);
		FacesContext.getCurrentInstance().addMessage(component, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	@PostConstruct
	private void init() {
		System.out.println("BaseManagedBean.init() ");
	}
	
}
