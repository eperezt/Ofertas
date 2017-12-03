/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Trabajador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface TrabajadorFacadeLocal {

    void create(Trabajador trabajador);

    void edit(Trabajador trabajador);

    void remove(Trabajador trabajador);

    Trabajador find(Object id);

    List<Trabajador> findAll();

    List<Trabajador> findRange(int[] range);

    int count();

}
