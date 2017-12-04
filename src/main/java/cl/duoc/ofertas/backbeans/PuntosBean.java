/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Punto;
import cl.duoc.ofertas.facade.PuntoFacadeLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Esteban Perez
 */
@ManagedBean(name = "puntosBean")
@ViewScoped
public class PuntosBean {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private PuntoFacadeLocal puntoFacade;

    private long puntos;

    /**
     * Creates a new instance of PuntosBean
     */
    public PuntosBean() {

    }

    public void obtenerPuntos() {
        this.puntoFacade.findAllByUsuario(loginBean.getUsuarioSesionado().getIdusuario().toBigInteger()).forEach((p) -> {
            this.puntos += p.getCantidad().longValue();
        });
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public long getPuntos() {
        return puntos;
    }

    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }

    @PostConstruct
    public void init() {
        this.puntos = 0;
        
        obtenerPuntos();

//        for (Punto p : loginBean.getUsuarioSesionado().getPuntoList()) {
//            totalPuntos += p.getCantidad().longValue();
//        }
//        
//        puntos = totalPuntos;
    }
}
