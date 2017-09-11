/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Funcionalidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class FuncionalidadFacade extends AbstractFacade<Funcionalidad> implements FuncionalidadFacadeLocal {

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionalidadFacade() {
        super(Funcionalidad.class);
    }
    
}
