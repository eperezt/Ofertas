/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Ciudad;
import cl.duoc.ofertas.facade.CiudadFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Esteban Perez
 */
//@Named(value = "genericServiceBean")
@ManagedBean(name="genericServiceBean")
@ApplicationScoped
public class GenericServiceBean implements Serializable{

    @EJB
    private CiudadFacadeLocal ciudadFacade;

    private List<Ciudad> listaCiudades;
    
    
    /**
     * Creates a new instance of GenericServiceBean
     */
    public GenericServiceBean() {
        
    }
    
    @PostConstruct
    public void init(){
        this.listaCiudades = ciudadFacade.getCiudadesActivas();
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }
}
