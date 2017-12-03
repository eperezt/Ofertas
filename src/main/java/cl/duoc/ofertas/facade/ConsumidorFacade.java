/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Consumidor;
import cl.duoc.ofertas.entities.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class ConsumidorFacade extends AbstractFacade<Consumidor> implements ConsumidorFacadeLocal {

    private final static Logger logger = Logger.getLogger(ConsumidorFacade.class);

    @PersistenceContext(unitName = "cl.duoc_Ofertas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsumidorFacade() {
        super(Consumidor.class);
    }

    @Override
    public void crearConsumidorUsuario(Usuario usuario, Consumidor consumidor) {
        StoredProcedureQuery storedProcedure = null;
        try {
            storedProcedure = em.createStoredProcedureQuery("sp_crea_usuario_consumidor");
            storedProcedure.registerStoredProcedureParameter("RUTPARAM", BigInteger.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("DVPARAM", Character.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("NOMBREPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("APELLIDOPATERNOPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("APELLIDOMATERNOPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("DIRECCIONPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("TELEFONOPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("EMAILPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("ISACTIVOPARAM", BigInteger.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("IDCIUDAD", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("ISRECIBEOFERTASPARAM", BigInteger.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("LOGINPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("PASSWORDPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("ISACTIVOUSUPARAM", BigInteger.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("IDSESSIONPARAM", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("IDPERFILPARAM", BigDecimal.class, ParameterMode.IN);
            storedProcedure.setParameter("RUTPARAM", consumidor.getRut());
            storedProcedure.setParameter("DVPARAM", consumidor.getDv());
            storedProcedure.setParameter("NOMBREPARAM", consumidor.getNombre());
            storedProcedure.setParameter("APELLIDOPATERNOPARAM", consumidor.getApellidopaterno());
            storedProcedure.setParameter("APELLIDOMATERNOPARAM", consumidor.getApellidomaterno());
            storedProcedure.setParameter("DIRECCIONPARAM", consumidor.getDireccion());
            storedProcedure.setParameter("TELEFONOPARAM", consumidor.getTelefono());
            storedProcedure.setParameter("EMAILPARAM", consumidor.getEmail());
            storedProcedure.setParameter("ISACTIVOPARAM", consumidor.getIsactivo());
            storedProcedure.setParameter("IDCIUDAD", consumidor.getCiudadIdciudad().getIdciudad());
            storedProcedure.setParameter("ISRECIBEOFERTASPARAM", consumidor.getIsrecibeofertas());
            storedProcedure.setParameter("LOGINPARAM", usuario.getLogin().toUpperCase());
            storedProcedure.setParameter("PASSWORDPARAM", usuario.getPassword());
            storedProcedure.setParameter("ISACTIVOUSUPARAM", usuario.getIsactivo());
            storedProcedure.setParameter("IDSESSIONPARAM", usuario.getIdsession());
            storedProcedure.setParameter("IDPERFILPARAM", usuario.getPerfilIdperfil().getIdperfil());

            storedProcedure.execute();

        } catch (Exception ex) {
            logger.error("Error grave creando consumidor.", ex);
            throw new RuntimeException(ex);
        } finally {
            storedProcedure = null;
        }
    }
}
