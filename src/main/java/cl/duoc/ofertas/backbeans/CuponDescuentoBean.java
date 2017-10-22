/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Colipavo
 */
@SessionScoped
@Named(value = "cuponDescuentoBean")
public class CuponDescuentoBean implements Serializable{
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    
    public void descargarCupon(){
        
    }
}
