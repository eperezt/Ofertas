/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    private final static Logger logger = Logger.getLogger(UsuarioFacade.class);

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario obtenerUsuarioPorLogin(String loginUsuario) {
        Query query = null;
        try {
            query = em.createQuery("SELECT m FROM Usuario m WHERE m.login = :loginUsuario", Usuario.class);
            query.setParameter("loginUsuario", loginUsuario.trim());
            Usuario usu = (Usuario) query.getSingleResult();
            return usu;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception e) {
            logger.error("Error grave obteniendo usuario con login: " + loginUsuario, e);
            throw new RuntimeException(e);
        } finally {
            query = null;
        }
    }
}
