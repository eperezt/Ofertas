/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.constantes.Constantes;
import cl.duoc.ofertas.entities.Oferta;
import cl.duoc.ofertas.entities.Usuario;
import cl.duoc.ofertas.facade.UsuarioFacadeLocal;
import cl.duoc.wsofertas.ws.LoginRequestVO;
import cl.duoc.wsofertas.ws.LoginResponseVO;
import cl.duoc.wsofertas.ws.OfertasWS;
import cl.duoc.wsofertas.ws.OfertasWS_Service;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Esteban Perez
 */
@SessionScoped
@Named(value = "loginBean")
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
    private StreamedContent imagentest;
    private List<Oferta> listaO;
    private Oferta of;
    private List<Integer> imageIds;

    public List<Integer> getImageIds() {
        return imageIds;
    }    
    
    public StreamedContent getprueba() throws IOException, SQLException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findAll", Oferta.class);
        List<Oferta> lo = consultaOfertas.getResultList();
//        imagentest = lo.get(0).getImage();
//        listaO = lo;
        return lo.get(0).getImage();
    }

    public Oferta getOf() {
        return of;
    }

    public void setOf(Oferta of) {
        this.of = of;
    }

    public List<Oferta> getListaO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findAll", Oferta.class);
        List<Oferta> lo = consultaOfertas.getResultList();
        return lo;
    }

    public void setListaO(List<Oferta> listaO) {
        this.listaO = listaO;
    }
    
    public StreamedContent getImagentest() {
        return imagentest;
    }

    public void setImagentest(StreamedContent imagentest) {
        this.imagentest = imagentest;
    }

    public LoginBean() throws IOException, SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findAll", Oferta.class);
        List<Oferta> lo = consultaOfertas.getResultList();
        imagentest = lo.get(0).getImage();
        listaO = lo;
        of = lo.get(0);
        imageIds = new ArrayList<Integer>();
        imageIds.add(1);
        imageIds.add(3);
        imageIds.add(4);
        imageIds.add(5);
        
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
            return "/index";
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
