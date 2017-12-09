/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Oferta;
import cl.duoc.ofertas.entities.Punto;
import cl.duoc.ofertas.entities.Tienda;
import cl.duoc.ofertas.entities.Valoracion;
import cl.duoc.ofertas.facade.OfertaFacadeLocal;
import cl.duoc.ofertas.facade.RubroFacadeLocal;
import cl.duoc.ofertas.facade.TiendaFacadeLocal;
import cl.duoc.ofertas.facade.ValoracionFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RateEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Mauricio Toro
 */
//@Named(value = "homeBean")
@ManagedBean(name = "homeBean")
@SessionScoped
public class HomeBean implements Serializable {

    @EJB
    private OfertaFacadeLocal ofertaFacade;

    @EJB
    private TiendaFacadeLocal tiendaFacade;

    @EJB
    private RubroFacadeLocal rubroFacade;

    @EJB
    private ValoracionFacadeLocal valoracionFacade;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private final static Logger logger = Logger.getLogger(HomeBean.class);
    private String empresaSeleccionada;
    private String rubroSeleccionado;
    private Oferta ofertaSeleccionada;
    private List<String> listaEmpresas;
    private List<String> listaRubros;
    private List<Oferta> listaOfertas;
    private List<Oferta> listaOfertasFiltradas;
    private String filtro;
    private UploadedFile file;
    private Integer rating;
    private Valoracion valoracion;

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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }

    public void ordenarSegunValoracion() {

    }

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valoración", "¡Has dado: " + ((Integer) rateEvent.getRating()).intValue() + " estrellas!");
        this.valoracion.setNota(new BigInteger(rateEvent.getRating().toString()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String cambiarPagina(String param) throws IOException {
        listarOfertas();
        listaOfertasFiltradas = listaOfertas;
        return param + "?faces-redirect=true";
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

    public String calcularPrecioOferta(BigInteger precio, BigInteger isPorcentaje, BigInteger isPrecioDirecto, BigDecimal descuento, BigInteger precioDescuento) {
        String respuesta = "";
        try {
            if (isPorcentaje.intValue() == 1) {
                respuesta = String.valueOf(precio.intValue() - ((precio.intValue() * descuento.intValue()) / 100));
            } else if (isPrecioDirecto.intValue() == 1) {
                respuesta = String.valueOf(precio.intValue() - precioDescuento.intValue());
            } else {
                throw new Exception("Error obteniendo valor de descuento oferta.");
            }
        } catch (Exception e) {
            logger.error("Error obteniendo valor." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo valor.", "Error grave obteniendo valor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }

        return respuesta;
    }

    public void handleFileUpload(FileUploadEvent event) {
        this.valoracion.setFotografia(event.getFile().getContents());
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void valorizar() {
        try {
            Punto punto = new Punto();
            //punto.setIdpunto(BigDecimal.ZERO); //creado en inserción.
            punto.setCantidad(new BigInteger("10"));
            punto.setFecha(Date.from(Instant.now()));
            punto.setIscobrado(BigInteger.ZERO);
            punto.setUsuarioIdusuario(loginBean.getUsuarioSesionado());

            //this.valoracion.setIdvaloracion(BigDecimal.ZERO); //creado en inserción.
            this.valoracion.setDetalle("Valoracion de oferta.");
            this.valoracion.setFechacreacion(Date.from(Instant.now()));
            this.valoracion.setUsuarioIdusuario(loginBean.getUsuarioSesionado());
            this.valoracion.setOfertaIdoferta(this.ofertaSeleccionada);
            //this.valoracion.setFotografia(event.getFile().getContents()); //guardada al momento de escoger la imagen.
            //this.valoracion.setNota(BigInteger.ONE); //guardada al momento de seleccionar cantidad de estrellas.

            punto.setValoracionIdvaloracion(valoracion);
            this.valoracion.setPunto(punto);
            this.valoracionFacade.create(valoracion);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
                    "Gracias por valorar nuestra oferta de " + this.valoracion.getOfertaIdoferta().getProductoIdproducto().getNombre() + " ¡" +
                    "Has acumulado " + this.valoracion.getPunto().getCantidad() + " puntos para tu cupón de descuento!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error("Error generando valorización." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error generando valoración.", "Error grave generando valoración.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        } finally {
//            FacesContext.getCurrentInstance().getMessages(
//            FacesContext.getCurrentInstance().getMessages().next().setDetail("Has acumulado " + this.valoracion.getPunto().getCantidad() + " puntos.");
            this.valoracion = new Valoracion();
        }

    }

    public void filterList() {
        List<Oferta> nuevoFiltro = new ArrayList<>();
        listaOfertasFiltradas = listaOfertas;
        for (Oferta oferta : listaOfertasFiltradas) {
            if (filtro.isEmpty()
                    && (this.rubroSeleccionado == null || this.rubroSeleccionado.equals("Todos"))
                    && (this.empresaSeleccionada == null || this.empresaSeleccionada.equals("Todas"))) {
                nuevoFiltro = ofertaFacade.findAllPublicadas();
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
            this.valoracion = new Valoracion();

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
