/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Rubro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface RubroFacadeLocal {

    void create(Rubro rubro);

    void edit(Rubro rubro);

    void remove(Rubro rubro);

    Rubro find(Object id);

    List<Rubro> findAll();

    List<Rubro> findRange(int[] range);

    int count();
    
    List<Rubro> findByCantidadValoraciones();
}
