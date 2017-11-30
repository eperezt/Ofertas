/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Punto;
import javax.annotation.PostConstruct;
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
    
    private long puntos;

    /**
     * Creates a new instance of PuntosBean
     */
    public PuntosBean() {

    }

    @PostConstruct
    public void init() {
        long totalPuntos = 0;

        for (Punto p : loginBean.getUsuarioSesionado().getPuntoList()) {
            totalPuntos += p.getCantidad().longValue();
        }
        
        puntos = totalPuntos;
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
}
