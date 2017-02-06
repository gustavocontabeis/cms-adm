package br.com.maxig.web.listeners;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.coder.arqprime.model.entity.usuarios.Usuario;
import br.com.maxig.model.dao.DaoException;
import br.com.maxig.model.dao.usuarios.UsuarioDAO;
import br.com.maxig.model.utils.GenerateMD5;

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
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	List<Usuario> buscarTodos = usuarioDAO.buscarTodos();
		Usuario usuario = new Usuario();
		usuario.setDtSenha(new Date());
		usuario.setDtUltimoAcesso(new Date());
		usuario.setId(null);
		usuario.setDtInativo(null);
		usuario.setLogin("gustavo");
		usuario.setPerfis("USU-ADM,WEB,USU");
		String generate = GenerateMD5.generateMD5("123");
		System.out.println(generate);
		usuario.setSenha(generate);
		usuarioDAO.salvar(usuario);
		System.out.println(usuario.getId());
		
	}

}
