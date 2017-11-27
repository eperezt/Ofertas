/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Rubro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class RubroFacade extends AbstractFacade<Rubro> implements RubroFacadeLocal {

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubroFacade() {
        super(Rubro.class);
    }
    
    @Override
    public List<Rubro> findByCantidadValoraciones(){
        TypedQuery<Rubro> consultaRubros = em.createNamedQuery("findByCantidadValoraciones", Rubro.class);
        Query query = em.createQuery("SELECT r FROM Rubro r", Rubro.class);
//        Query query = em.createQuery("SELECT r.idrubro FROM Oferta o INNER JOIN Producto p ON o.productoIdproducto=p.IDPRODUCTO INNER JOIN Rubro r ON p.rubroIdrubro =r.IDRUBRO INNER JOIN Valoracion v ON o.IDOFERTA=v.OFERTA_IDOFERTA INNER JOIN Usuario u ON v.USUARIO_IDUSUARIO=u.IDUSUARIO WHERE u.idusuario = :idusuario AND o.ispublicada = 0 GROUP BY r.idrubro ORDER BY(COUNT(r.idrubro)) DESC", Rubro.class);
        List<Rubro> lr = consultaRubros.getResultList();
        return lr;
    }
    
}
