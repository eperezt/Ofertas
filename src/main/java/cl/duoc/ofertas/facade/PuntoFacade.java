/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Punto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class PuntoFacade extends AbstractFacade<Punto> implements PuntoFacadeLocal {

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(PuntoFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Punto> findAllByUsuario(BigInteger id) {
        List<Punto> listaPuntos = null;
        Query consulta = null;
        try {
            listaPuntos = new ArrayList<>();
            consulta = em.createQuery("SELECT p FROM Punto INNER JOIN Usuario u ON u=p.usuarioIdusuario WHERE p.iscobrado=0");
            for (Object obj : consulta.getResultList()) {
                Punto punto = (Punto) obj;
                listaPuntos.add(punto);
            }
            if (listaPuntos.isEmpty()) {
                throw new Exception("No hay ofertas disponibles.");
            }
        } catch (Exception e) {
            logger.error("Error obteniendo ofertas." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo ofertas.", "Error grave obteniendo ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        return null;
    }

    @Override
    public void create(Punto punto) {
        Query consulta;
        try {
            em.getTransaction().begin();
            em.persist(punto);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error obteniendo ofertas." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo ofertas.", "Error grave obteniendo ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
    }

    public PuntoFacade() {
        super(Punto.class);
    }

}
