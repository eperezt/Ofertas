/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Oferta;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class OfertaFacade extends AbstractFacade<Oferta> implements OfertaFacadeLocal {

    private final static Logger logger = Logger.getLogger(OfertaFacade.class);

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfertaFacade() {
        super(Oferta.class);
    }

    public byte[] getImageById(int id) throws IOException {
        TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findByIdoferta", Oferta.class);
        em.setProperty("IDOFERTA", id);
        List<Oferta> lo = consultaOfertas.getResultList();
        return lo.get(0).getFotografia();
    }

    @Override
    public Oferta find(Object id) {
        Oferta oferta = new Oferta();
        try {
            TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findByIdoferta", Oferta.class);
            String numeroStr = id.toString();
            BigDecimal qwe = new BigDecimal(numeroStr);
            consultaOfertas.setParameter("idoferta", qwe);
            oferta = (Oferta) consultaOfertas.getSingleResult();
        } catch (Exception e) {
            System.out.println("cl.duoc.ofertas.facade.OfertaFacade.find()" + e.getMessage());
        }
        return oferta;
    }

    @Override
    public List<Oferta> findAllSortedByRubro(BigDecimal idRubro) {
        List<Oferta> listaOfertas = null;
        TypedQuery<Oferta> consultaOfertas = null;
        try {
            listaOfertas = new ArrayList<>();
            consultaOfertas = em.createNamedQuery("Oferta.findAllSortedByRubro", Oferta.class);
            consultaOfertas.setParameter("idrubro", idRubro);
            listaOfertas = consultaOfertas.getResultList();
        } catch (Exception e) {
            System.out.println("cl.duoc.ofertas.facade.OfertaFacade.find()" + e.getMessage());
        }
        return listaOfertas;
    }

    @Override
    public List<Oferta> findAllPublicadas() {
        List<Oferta> listaOfertas = null;
        TypedQuery<Oferta> consulta = null;
        
        try {
            listaOfertas = new ArrayList<>();
            consulta = em.createNamedQuery("Oferta.findAllPublicadas", Oferta.class);
            listaOfertas = consulta.getResultList();
            if (listaOfertas.isEmpty()) {
                throw new Exception("No hay ofertas disponibles.");
            }
        } catch (Exception e) {
            logger.error("Error obteniendo ofertas." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo ofertas.", "Error grave obteniendo ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        
        return listaOfertas;
    }
}
