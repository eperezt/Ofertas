/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Logemail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface LogemailFacadeLocal {

    void create(Logemail logemail);

    void edit(Logemail logemail);

    void remove(Logemail logemail);

    Logemail find(Object id);

    List<Logemail> findAll();

    List<Logemail> findRange(int[] range);

    int count();
    
}
