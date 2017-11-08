/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;


import cl.duoc.ofertas.entities.Oferta;
import cl.duoc.ofertas.facade.OfertaFacade;
import cl.duoc.ofertas.facade.OfertaFacadeLocal;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Harvesting-Woods
 */
@ManagedBean
@ApplicationScoped
public class ImageStreamer {

    @EJB
    private OfertaFacadeLocal service;
    
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Oferta oferta = service.find(id);
            return new DefaultStreamedContent(new ByteArrayInputStream(oferta.getFotografia()));
        }
    }
}
