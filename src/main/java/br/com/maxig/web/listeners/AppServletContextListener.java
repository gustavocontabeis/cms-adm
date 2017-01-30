package br.com.maxig.web.listeners;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.maxig.model.dao.DaoException;
import br.com.maxig.model.dao.usuarios.UsuarioDAO;
import br.com.maxig.model.entity.usuarios.Usuario;
import br.com.maxig.model.entity.usuarios.UsuarioPerfil;

@WebListener
public class AppServletContextListener implements ServletContextListener{
	
	@Override
    public void contextInitialized(ServletContextEvent event) {
        try {
			inicializarDados();
		} catch (DaoException e) {
			e.printStackTrace();
		}
    }

	@Override
    public void contextDestroyed(ServletContextEvent event) {
        
    }
	
    private void inicializarDados() throws DaoException {
		Usuario usuario = new Usuario();
		usuario.setDtSenha(new Date());
		usuario.setDtUltimoAcesso(new Date());
		usuario.setId(null);
		usuario.setInativo(false);
		usuario.setLogin("gustavo");
		usuario.setPerfis("USU-ADM,USU");
		usuario.setSenha("123");
		new UsuarioDAO().salvar(usuario);
		
	}

}
