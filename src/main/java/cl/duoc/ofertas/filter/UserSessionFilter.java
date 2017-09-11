/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.filter;

import cl.duoc.ofertas.backbeans.LoginBean;
import cl.duoc.ofertas.constantes.Constantes;
import cl.duoc.ofertas.entities.Usuario;
import java.io.IOException;
import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
public class UserSessionFilter implements Filter {

    private final static Logger logger = Logger.getLogger(UserSessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Filtro que valida que el usuario este sessionado antes de ingresar a
     * cualquier página (ya sea por URL directa o navegación), además valida que
     * no puedan estar 2 usuarios logeados con la misma cuenta (utiliza la
     * SessionID que entrega Glassfish o cualquier App Server).
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String pathInfo = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

            if ("/".equals(pathInfo) || pathInfo.contains("javax.faces.resource") || !pathInfo.startsWith("/faces") || pathInfo.contains("login.xhtml")) {
                chain.doFilter(request, response);
            } else {
                HttpSession httpSession = httpRequest.getSession();
                if (httpSession == null || httpSession.getId() == null) {
                    throw new ViewExpiredException("Session Expirada");
                }
                LoginBean loginAdmin = (LoginBean) httpSession.getAttribute(Constantes.NOMBRE_LOGIN_BEAN);
                if (loginAdmin == null || !loginAdmin.isIsLogged()) {
                    HttpServletResponse res = (HttpServletResponse) response;
                    httpSession.invalidate(); //Dejamos la sesion no válida para que el usuario tenga que logearse nuevamente
                    res.sendRedirect(httpRequest.getContextPath() + Constantes.URL_LOGIN);
                } else if (loginAdmin.isIsLogged()) {
                    Usuario usuario = loginAdmin.getUsuarioFacade().find(loginAdmin.getUsuarioSesionado().getIdusuario());
                    if (!httpSession.getId().trim().equals(usuario.getIdsession().trim())) {
                        throw new ViewExpiredException("Session Expirada");
                    }
                    chain.doFilter(request, response);
                }
            }
        } catch (ViewExpiredException e) {
            logger.info("Session Expirada.");
            throw new ViewExpiredException("Session Expirada");
        } catch (Exception ex) {
            logger.error("Error grave aplicando Filtro de session.", ex);
            throw new ViewExpiredException("Session Expirada");
        } finally {

        }
    }

    @Override
    public void destroy() {
    }

}
