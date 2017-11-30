/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.constantes.Constantes;
import cl.duoc.ofertas.entities.Ciudad;
import cl.duoc.ofertas.entities.Consumidor;
import cl.duoc.ofertas.entities.Perfil;
import cl.duoc.ofertas.entities.Usuario;
import cl.duoc.ofertas.facade.ConsumidorFacadeLocal;
import cl.duoc.ofertas.facade.PerfilFacadeLocal;
import cl.duoc.ofertas.facade.UsuarioFacadeLocal;
import cl.duoc.ofertas.utils.PasswordUtils;
import cl.duoc.ofertas.utils.Utils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Esteban Perez
 */
@ManagedBean(name="registroConsumidorBean")
@ViewScoped
public class RegistroConsumidorBean implements Serializable {

    @EJB
    private ConsumidorFacadeLocal consumidorFacade;

    @EJB
    private PerfilFacadeLocal perfilFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private final static Logger logger = Logger.getLogger(RegistroConsumidorBean.class);

    /**
     * Creates a new instance of RegistroConsumidorBean
     */
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String direccion;
    private Ciudad ciudad;
    private String telefono;
    private String login;
    private String correo;
    private String password;
    private boolean recibeNotificaciones;

    /**
     * Creates a new instance of RegistroConsumidor
     */
    public RegistroConsumidorBean() {

    }

    public void registroListener(ActionEvent event) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            if (!Utils.validarRut(this.getRut().trim())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El Rut ingresado no es válido.", "Error: El Rut ingresado no es válido.");
                //FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("growl", message);
                return;
            } else {
                //Validamos si el login ingresado ya existe para algún otro usuario
                Usuario usu = usuarioFacade.obtenerUsuarioPorLogin(this.getLogin().toUpperCase().trim());

                if (usu != null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El login ingresado ya se encuentra utilizado.", "Error: El login ingresado ya se encuentra utilizado.");
                    //FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("growl", message);
                    return;
                }

                if (this.getRut().split("-").length < 2) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El formato del Rut no es correcto.", "Error: El formato del Rut no es correcto.");
                    //FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("growl", message);
                    return;
                }

                Consumidor nuevoConsumidor = new Consumidor();
                nuevoConsumidor.setNombre(this.getNombre().trim());
                nuevoConsumidor.setApellidopaterno(this.getApellidoPaterno().trim());
                nuevoConsumidor.setApellidomaterno(this.getApellidoMaterno().trim());
                nuevoConsumidor.setRut(new BigInteger(this.getRut().trim().split("-")[0]));
                nuevoConsumidor.setDv(this.getRut().trim().split("-")[1].charAt(0));
                nuevoConsumidor.setDireccion(this.getDireccion().trim());
                nuevoConsumidor.setCiudadIdciudad(this.getCiudad());
                nuevoConsumidor.setTelefono(this.getTelefono().trim());
                nuevoConsumidor.setFechacreacion(new Date());
                nuevoConsumidor.setFechamodificacion(new Date());
                nuevoConsumidor.setIsactivo(new BigInteger("1"));
                String recibeOfertas = this.isRecibeNotificaciones() ? "1" : "0";
                nuevoConsumidor.setIsrecibeofertas(new BigInteger(recibeOfertas));
                nuevoConsumidor.setEmail(this.getCorreo().trim());

                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setConsumidor(nuevoConsumidor);
                nuevoUsuario.setFechacreacion(new Date());
                nuevoUsuario.setFechamodificacion(new Date());
                HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                String idSession = session.getId().trim();
                nuevoUsuario.setIdsession(idSession);
                nuevoUsuario.setIsactivo(new BigInteger("1"));
                nuevoUsuario.setLogin(this.getLogin().toUpperCase().trim());
                nuevoUsuario.setPassword(PasswordUtils.getHash(this.getPassword().trim(), Constantes.CIFRADO_SHA1));

                //Obtenemos el perfil correspondiente a consumidor
                Perfil perfilConsumidor = perfilFacade.find(new BigDecimal(Constantes.CODIGO_PERFIL_CONSUMIDOR));

                nuevoUsuario.setPerfilIdperfil(perfilConsumidor);
                nuevoConsumidor.setUsuarioIdusuario(nuevoUsuario);

                consumidorFacade.crearConsumidorUsuario(nuevoUsuario, nuevoConsumidor);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro completado exitosamente.", "Registro completado exitosamente.");
                context = FacesContext.getCurrentInstance();
                context.addMessage("growl", message);

                RequestContext.getCurrentInstance().execute("PF('dlgRegistro').hide();");

                this.limpiarFormulario();
            }
        } catch (Exception e) {
            logger.error("Error grave creando Consumidor.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error grave al crear Consumidor.", "Error: Se ha encontrado un error grave al crear Consumidor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
    }

    private void limpiarFormulario() {
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.rut = "";
        this.direccion = "";
        this.ciudad = null;
        this.telefono = "";
        this.login = "";
        this.correo = "";
        this.password = "";
        this.recibeNotificaciones = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRecibeNotificaciones() {
        return recibeNotificaciones;
    }

    public void setRecibeNotificaciones(boolean recibeNotificaciones) {
        this.recibeNotificaciones = recibeNotificaciones;
    }
}
