package br.com.maxig.web.jsf.filters;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.maxig.model.entity.usuarios.Usuario;

@WebFilter(filterName = "SegurancaFilter", urlPatterns = {"*.jsf"})
public class SegurancaFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SegurancaFilter.class.getSimpleName());
	
	public static final String USER = "usuario";
	private Map<String, String[]> paginasPrivadas = new HashMap<>();
	
    private static final String FACES_LOGIN_XHTML = "/pages/login/login.jsf";
    private static final String URL_PATTERN = "";

    public SegurancaFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        paginasPrivadas.put("/pages/banner/", new String[]{"ADM"});
        paginasPrivadas.put("/pages/configuracoes/", new String[]{"ADM"});
        paginasPrivadas.put("/pages/galeria/", new String[]{"ADM"});
        paginasPrivadas.put("/pages/pagina/", new String[]{"ADM"});
        paginasPrivadas.put("/pages/perfil/", new String[]{"ADM"});
        paginasPrivadas.put("/pages/usuario/", new String[]{"ADM"});
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            HttpSession session = request.getSession(false);

            String requestURI = request.getRequestURI();
            if (requestURI.indexOf("/public/") >= 0
                    || requestURI.contains("javax.faces.resource")) {
            	LOGGER.trace("É público ou resource.");
                chain.doFilter(req, res);
                return;
            }
            
            LOGGER.debug("------------");
            
            //verifica se é privada e se esta logado e se tem permissao.
            LOGGER.debug("requestURI: {}", requestURI);
            String page = getPage(request);
            
            if (paginasPrivadas.containsKey(page)) {
                LOGGER.debug("É uma página privada.");
                Usuario usuario = (Usuario) getUser(session);
                if (usuario != null) {
                	LOGGER.debug("{} está logado.", usuario.getLogin());
                    String[] perfis = paginasPrivadas.get(page);
                    if (usuario.isContemPerfil(perfis)) {
                        LOGGER.debug("OK. {} autorizado a acessar {}", usuario.getLogin(), requestURI);
                        chain.doFilter(req, res);
                    } else {
                    	LOGGER.debug("Usuário {} não autorizado a acessar {}", usuario.getLogin(), requestURI!=null?requestURI:"");
                    	//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário ou senha inválidos."));
                        redirecionarLogin(request, response, "Usuário não autorizado.");
                    }
                } else {
                	LOGGER.debug("Nenhum usuário logado. Você será redirecionado para o login.");
                	redirecionarLogin(request, response);
                }
            }else{
            	//LOGGER.debug("É uma página pública.");
            	chain.doFilter(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private String getPage(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return requestURI.replace(request.getContextPath(), "").replace(URL_PATTERN, "").replaceAll(";jsessionid=.*.?", "");
	}
	
    private static Object getUser(HttpSession session) {
        if(session == null){
            return null;
        }
        return session.getAttribute(USER);
    }

    private void redirecionarLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	redirecionarLogin(request, response, null);
    }

	private void redirecionarLogin(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
		
//		if(StringUtils.isNotBlank(msg)){
//			msg = Base64.getUrlEncoder().encodeToString(msg.getBytes());
//		}else{
//			msg = StringUtils.EMPTY;
//		}
		
        LOGGER.debug("Redirecionado para login com destino a \"{}\".", request.getRequestURI());
        request.getSession(false).setAttribute("destino", request.getRequestURI());
        response.sendRedirect(request.getContextPath() + FACES_LOGIN_XHTML + (StringUtils.isNotBlank(msg)?"?msg="+msg:""));
	}

    @Override
    public void destroy() {

    }
}
