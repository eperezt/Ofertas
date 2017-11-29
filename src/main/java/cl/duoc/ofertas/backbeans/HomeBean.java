/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Oferta;
import cl.duoc.ofertas.entities.Tienda;
import cl.duoc.ofertas.facade.OfertaFacadeLocal;
import cl.duoc.ofertas.facade.RubroFacadeLocal;
import cl.duoc.ofertas.facade.TiendaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author Mauricio Toro
 */
@SessionScoped
@Named(value = "homeBean")
public class HomeBean implements Serializable {

    @EJB
    private OfertaFacadeLocal ofertaFacade;

    @EJB
    private TiendaFacadeLocal tiendaFacade;

    @EJB
    private RubroFacadeLocal rubroFacade;
    
    private final static Logger logger = Logger.getLogger(HomeBean.class);
    private String empresaSeleccionada;
    private String rubroSeleccionado;
    private List<String> listaEmpresas;
    private List<String> listaRubros;
    private List<Oferta> listaOfertas;
    private List<Oferta> listaOfertasFiltradas;
    private String filtro;

    public HomeBean() throws IOException, SQLException {

    }

    public List<Oferta> getListaOfertasFiltradas() {
        return listaOfertasFiltradas;
    }

    public void setListaOfertasFiltradas(List<Oferta> listaOfertasFiltradas) {
        this.listaOfertasFiltradas = listaOfertasFiltradas;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<String> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<String> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public List<Oferta> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(List<Oferta> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }

    public List<String> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<String> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public String getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    public void setEmpresaSeleccionada(String empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    public String getRubroSeleccionado() {
        return rubroSeleccionado;
    }

    public void setRubroSeleccionado(String rubroSeleccionado) {
        this.rubroSeleccionado = rubroSeleccionado;
    }
    
    public void ordenarSegunValoracion() {

    }

    public String cambiarPagina(String param) throws IOException {
        listarOfertas();
        listaOfertasFiltradas = listaOfertas;
        return param;
    }

    public List<Tienda> listarTiendas() {
        List<Tienda> lista = null;

        try {
            lista = tiendaFacade.findAll();
        } catch (Exception e) {
            logger.error("Error obteniendo empresas de tienda." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo tiendas.", "Error grave obteniendo tiendas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        return lista;
    }

    public List<String> listarEmpresas(List<Tienda> tiendas) {
        try {
            tiendas.stream().filter((tienda) -> (!listaEmpresas.contains(tienda.getEmpresa()))).forEachOrdered((tienda) -> {
                listaEmpresas.add(tienda.getEmpresa());
            });
            if (listaEmpresas.isEmpty()) {
                throw new Exception("lista vacia.");
            }
        } catch (Exception e) {
            logger.error("Error listando empresas mediante lista de tiendas: " + tiendas, e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error listando empresas mediante lista de tiendas: " + tiendas, "Error grave obteniendo empresas de tiendas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        return listaEmpresas;
    }

    private void listarOfertas() {
        try {
            this.listaOfertas = ofertaFacade.findAllPublicadas();
        } catch (Exception e) {
            logger.error("Error obteniendo ofertas." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo ofertas.", "Error grave obteniendo ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
    }

    public List<String> listarRubros() {
        try {
            this.rubroFacade.findAll().forEach((rubro) -> {
                this.listaRubros.add(rubro.getNombre());
            });
        } catch (Exception e) {
            logger.error("Error obteniendo rubros." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo rubros.", "Error grave obteniendo rubros.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
        return this.listaRubros;
    }

    public void filterList() {
        List<Oferta> nuevoFiltro = new ArrayList<>();
        listaOfertasFiltradas = listaOfertas;
        for (Oferta oferta : listaOfertasFiltradas) {
            if (filtro.isEmpty()
                    && (this.rubroSeleccionado == null || this.rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada == null || this.empresaSeleccionada.equals("Todas"))) {
                nuevoFiltro = listaOfertas;
            } else if (filtro.isEmpty()
                    && (this.rubroSeleccionado != null && !rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada == null || this.empresaSeleccionada.equals("Todas"))) {
                if (oferta.getProductoIdproducto().getRubroIdrubro().getNombre().equals(rubroSeleccionado)
                        && oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                    nuevoFiltro.add(oferta);
                }
            } else if (filtro.isEmpty()
                    && (this.rubroSeleccionado == null || this.rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada != null && !empresaSeleccionada.equals("Todas"))) {
                if (oferta.getProductoIdproducto().getTiendaList().get(0).getEmpresa().equals(empresaSeleccionada)
                        && oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                    nuevoFiltro.add(oferta);
                }
            } else if (filtro.isEmpty()
                    && oferta.getProductoIdproducto().getRubroIdrubro().getNombre().equals(rubroSeleccionado)
                    && oferta.getProductoIdproducto().getTiendaList().get(0).getEmpresa().equals(empresaSeleccionada)
                    && oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                nuevoFiltro.add(oferta);
            } else if (!filtro.isEmpty()
                    && (this.rubroSeleccionado == null || this.rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada == null || this.empresaSeleccionada.equals("Todas"))) {
                if (oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                    nuevoFiltro.add(oferta);
                }
            } else if (!filtro.isEmpty()
                    && (this.rubroSeleccionado != null && !rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada == null || this.empresaSeleccionada.equals("Todas"))) {
                if (oferta.getProductoIdproducto().getRubroIdrubro().getNombre().equals(rubroSeleccionado)
                        && oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                    nuevoFiltro.add(oferta);
                }
            } else if (!filtro.isEmpty()
                    && (this.rubroSeleccionado == null || this.rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada != null && !empresaSeleccionada.equals("Todas"))) {
                if (oferta.getProductoIdproducto().getTiendaList().get(0).getEmpresa().equals(empresaSeleccionada)
                        && oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                    nuevoFiltro.add(oferta);
                }
            } else if (!filtro.isEmpty()
                    && oferta.getProductoIdproducto().getRubroIdrubro().getNombre().equals(rubroSeleccionado)
                    && oferta.getProductoIdproducto().getTiendaList().get(0).getEmpresa().equals(empresaSeleccionada)
                    && oferta.getProductoIdproducto().getNombre().toUpperCase().contains(filtro.toUpperCase())) {
                nuevoFiltro.add(oferta);
            }
        }
        setListaOfertasFiltradas(nuevoFiltro);
    }

    @PostConstruct
    public void init() {
        try {
            this.listaOfertas = new ArrayList<>();
            this.listaEmpresas = new ArrayList<>();
            this.listaRubros = new ArrayList<>();
            this.rubroSeleccionado = "Todos";
            this.empresaSeleccionada = "Todas";
            
            listarOfertas();
            listarEmpresas(listarTiendas());
            listarRubros();
            //ordenarSegunValoracion();            
            
            this.listaOfertasFiltradas = listaOfertas;
        } catch (Exception e) {
            logger.error("No hay ofertas disponibles.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: No se han encontrado ofertas disponibles.", "Advertencia busqueda ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", message);
        }
    }
}
