/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.converter;

import cl.duoc.ofertas.entities.Ciudad;
import cl.duoc.ofertas.facade.CiudadFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Esteban Perez
 */
@Named(value = "ciudadConverterBean")
@SessionScoped
public class CiudadConverterBean implements Serializable, Converter {

    @EJB
    private CiudadFacadeLocal ciudadFacade;

    /**
     * Creates a new instance of CiudadConverterBean
     */
    public CiudadConverterBean() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return ciudadFacade.find(new BigDecimal(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Conversion Error"));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Ciudad) object).getIdciudad());
        } else {
            return null;
        }
    }
}
