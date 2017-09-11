/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Cupon;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface CuponFacadeLocal {

    void create(Cupon cupon);

    void edit(Cupon cupon);

    void remove(Cupon cupon);

    Cupon find(Object id);

    List<Cupon> findAll();

    List<Cupon> findRange(int[] range);

    int count();
    
}
