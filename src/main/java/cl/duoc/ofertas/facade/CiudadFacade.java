/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Ciudad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> implements CiudadFacadeLocal {

    private final static Logger logger = Logger.getLogger(CiudadFacade.class);

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    @Override
    public List<Ciudad> getCiudadesActivas() {
        Query query = null;
        List<Ciudad> listaCiudades = null;
        try {
            query = em.createQuery("SELECT m FROM Ciudad m WHERE m.isactivo = 1 ORDER BY m.nombre ASC", Ciudad.class);
            listaCiudades = (List<Ciudad>) query.getResultList();
            return listaCiudades;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            logger.error("Error grave obteniendo ciudades", e);
            throw new RuntimeException(e);
        } finally {
            query = null;
        }
    }

}
