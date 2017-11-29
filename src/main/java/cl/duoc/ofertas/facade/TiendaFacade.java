/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Tienda;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class TiendaFacade extends AbstractFacade<Tienda> implements TiendaFacadeLocal {
    
    
    private final static Logger logger = Logger.getLogger(TiendaFacade.class);

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiendaFacade() {
        super(Tienda.class);
    }
    @Override
    public List<Tienda> findAll(){
        List<Tienda> listaTiendas = null;
        TypedQuery<Tienda> consulta = null;
        
        try {
            listaTiendas = new ArrayList<>();
            consulta = em.createNamedQuery("Tienda.findAll", Tienda.class);
            listaTiendas = consulta.getResultList();
            if (listaTiendas.isEmpty()) {
                throw new Exception("No hay tiendas disponibles.");
            }
        } catch (Exception e) {
            logger.error("Error obteniendo tiendas." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo tiendas.", "Error grave obteniendo tiendas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        
        return listaTiendas;
    }
    
}
