/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.constantes.Constantes;
import cl.duoc.ofertas.entities.Usuario;
import cl.duoc.ofertas.facade.UsuarioFacadeLocal;
import cl.duoc.wsofertas.ws.LoginRequestVO;
import cl.duoc.wsofertas.ws.LoginResponseVO;
import cl.duoc.wsofertas.ws.OfertasWS;
import cl.duoc.wsofertas.ws.OfertasWS_Service;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private final static Logger logger = Logger.getLogger(LoginBean.class);
    /**
     * Creates a new instance of LoginBean
     */
    private String loginUsuario;
    private String passwordUsuario;
    private boolean isLogged;

    private Usuario usuarioSesionado;

    public LoginBean() throws IOException, SQLException {
        
    }

    public String loginListener() {
        OfertasWS_Service service = null;
        OfertasWS port = null;
        LoginRequestVO loginRequest = null;
        LoginResponseVO result = null;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            service = new OfertasWS_Service();

            port = service.getOfertasWSPort();

            loginRequest = new LoginRequestVO();

            loginRequest.setUsername(this.getLoginUsuario().trim().toUpperCase());
            loginRequest.setPassword(this.getPasswordUsuario().trim());

            result = port.procesarLogin(loginRequest);

            if (result == null || result.getCodigoRespuesta() == Constantes.CODIGO_ERROR_GENERAL_WS) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error grave al procesar Login.", "Error grave procesando Login.");
                context.addMessage("growl", message);
            } else {
                if (result.getCodigoRespuesta() != Constantes.CODIGO_RESPUESTA_EXITOSA_WS) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, result.getRespuesta().trim(), result.getRespuesta().trim());
                    context.addMessage("growl", message);
                } else {
                    Usuario usuarioLogin = usuarioFacade.find(new BigDecimal(result.getCodigoUsuario()));

                    if (usuarioLogin.getIsactivo().intValue() == 0) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El Usuario se encuentra inactivo.", "Error: El Usuario se encuentra inactivo.");
                        context.addMessage("growl", message);
                        return "";
                    }

                    this.setUsuarioSesionado(usuarioLogin);
                    this.setIsLogged(true);
                    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                    String idSession = session.getId().trim();
                    usuarioLogin.setIdsession(idSession);
                    usuarioFacade.edit(usuarioLogin);
                    return "/pages/home/home";
                }
            }
        } catch (Exception e) {
            logger.error("Error grave procesando Login.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error grave al procesar Login.", "Error grave procesando Login.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        } finally {
            service = null;
            port = null;
            loginRequest = null;
            result = null;
        }
        return "";
    }

    public String logoutListener() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            this.setIsLogged(false);
            this.setUsuarioSesionado(null);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            logger.error("Error grave procesando Logout.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error grave al procesar Logout.", "Error grave procesando Logout.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
            return "";
        }
    }

    public String irARegistroConsumidor() {
        return "/pages/registro/registroConsumidor";
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public Usuario getUsuarioSesionado() {
        return usuarioSesionado;
    }

    public void setUsuarioSesionado(Usuario usuarioSesionado) {
        this.usuarioSesionado = usuarioSesionado;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }
}
