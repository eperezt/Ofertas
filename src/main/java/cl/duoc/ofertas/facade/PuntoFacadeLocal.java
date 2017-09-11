/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Punto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface PuntoFacadeLocal {

    void create(Punto punto);

    void edit(Punto punto);

    void remove(Punto punto);

    Punto find(Object id);

    List<Punto> findAll();

    List<Punto> findRange(int[] range);

    int count();
    
}
