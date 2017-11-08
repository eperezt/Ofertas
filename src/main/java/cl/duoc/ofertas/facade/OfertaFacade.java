/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Oferta;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class OfertaFacade extends AbstractFacade<Oferta> implements OfertaFacadeLocal {

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
}
