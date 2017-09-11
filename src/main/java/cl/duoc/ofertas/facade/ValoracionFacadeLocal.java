/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Valoracion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface ValoracionFacadeLocal {

    void create(Valoracion valoracion);

    void edit(Valoracion valoracion);

    void remove(Valoracion valoracion);

    Valoracion find(Object id);

    List<Valoracion> findAll();

    List<Valoracion> findRange(int[] range);

    int count();
    
}
