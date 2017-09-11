/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Funcionalidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface FuncionalidadFacadeLocal {

    void create(Funcionalidad funcionalidad);

    void edit(Funcionalidad funcionalidad);

    void remove(Funcionalidad funcionalidad);

    Funcionalidad find(Object id);

    List<Funcionalidad> findAll();

    List<Funcionalidad> findRange(int[] range);

    int count();
    
}
