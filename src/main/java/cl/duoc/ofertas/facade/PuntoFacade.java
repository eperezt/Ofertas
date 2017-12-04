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
        List<Punto> listaPuntos = new ArrayList<>();
        Query consulta = null;
        try {
            consulta = em.createQuery("SELECT p FROM Punto p, Usuario u WHERE u=p.usuarioIdusuario AND p.iscobrado=0 AND u.idusuario = :idusuario", Punto.class);
            consulta.setParameter("idusuario", id);
            for (Object obj : consulta.getResultList()) {
                Punto punto = (Punto) obj;
                listaPuntos.add(punto);
            }
            if (listaPuntos.isEmpty()) {
                throw new Exception("No hay puntos registrados.");
            }
        } catch (Exception e) {
            logger.error("Error obteniendo puntos." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo puntos de usuario.", "Error grave obteniendo puntos.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        return listaPuntos;
    }

    @Override
    public void create(Punto punto) {
        try {
            em.persist(punto);
        } catch (Exception e) {
            logger.error("Error insertando puntos." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error insertando puntos.", "Error grave insertando puntos.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
    }

    public PuntoFacade() {
        super(Punto.class);
    }

}
