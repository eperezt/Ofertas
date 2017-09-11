/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Ciudad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface CiudadFacadeLocal {

    void create(Ciudad ciudad);

    void edit(Ciudad ciudad);

    void remove(Ciudad ciudad);

    Ciudad find(Object id);

    List<Ciudad> findAll();

    List<Ciudad> findRange(int[] range);

    int count();
    
}
