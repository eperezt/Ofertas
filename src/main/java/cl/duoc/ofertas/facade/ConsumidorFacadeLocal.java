/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.facade;

import cl.duoc.ofertas.entities.Consumidor;
import cl.duoc.ofertas.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface ConsumidorFacadeLocal {

    void create(Consumidor consumidor);

    void edit(Consumidor consumidor);

    void remove(Consumidor consumidor);

    Consumidor find(Object id);

    List<Consumidor> findAll();

    List<Consumidor> findRange(int[] range);

    int count();

    public void crearConsumidorUsuario(Usuario usuario, Consumidor consumidor);
}
