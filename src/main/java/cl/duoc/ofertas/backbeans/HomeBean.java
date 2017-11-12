/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Oferta;
import cl.duoc.ofertas.entities.Rubro;
import cl.duoc.ofertas.entities.Tienda;
import cl.duoc.ofertas.facade.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Mauricio Toro
 */
@SessionScoped
@Named(value = "homeBean")
public class HomeBean implements Serializable {

    /* MTP 
     * Esta página necesita:
     * * Formulario de filtración con:
     * * * Lista según empresa : la obtiene desde BD - Tabla tiendas.
     * * * 
     */
    private final static Logger logger = Logger.getLogger(HomeBean.class);

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private String empresaSeleccionada;
    private String rubroSeleccionado;
    private List<String> listaEmpresas;
    private List<String> listaRubros;
    private List<Oferta> listaOfertas;
    private List<Oferta> listaOfertasFiltradas;
    private String filtro;
    private StreamedContent imagentest;

    public HomeBean() throws IOException, SQLException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findAll", Oferta.class);
            List<Oferta> lo = consultaOfertas.getResultList();
            Oferta oferta = null;
            if (lo.isEmpty()) {
                throw new Exception("No hay ofertas disponibles.");
            } else {
                oferta = lo.get(1);
            }

            imagentest = oferta.getImage();
            listaOfertas = consultaOfertas.getResultList();
        } catch (Exception e) {
            logger.error("No hay ofertas disponibles.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion: No se han encontrado ofertas disponibles.", "Advertencia busqueda ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", message);
        }
    }

//    public InputStream getImagentest() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<Oferta> consultaOfertas = em.createNamedQuery("Oferta.findAll", Oferta.class);
//        Oferta lo = consultaOfertas.getResultList().get(0);
////        content = new DefaultStreamedContent(is, "", student.getStuID());
////        return imagentest;
//        return lo.getImage();
//    }
    public StreamedContent getImagentest() {
        return imagentest;
    }

    public void setImagentest(StreamedContent imagentest) {
        this.imagentest = imagentest;
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

    public List<Tienda> getTiendas() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        TypedQuery<Tienda> consultaTiendas = null;
        List<Tienda> lista = null;

        try {
            emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
            em = emf.createEntityManager();
            consultaTiendas
                    = em.createNamedQuery("Tienda.findAll", Tienda.class
                    );
            lista = consultaTiendas.getResultList();
        } catch (Exception e) {
            logger.error("Error obteniendo empresas de tienda." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo tiendas.", "Error grave obteniendo tiendas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        } finally {
            consultaTiendas = null;
            em = null;
            emf = null;
        }
        return (List<Tienda>) lista;
    }

//    public List<String> listarEmpresas() {
//        ArrayList listaEmpresas = null;
//        try {
//            listaEmpresas = new ArrayList();
//            for (Tienda tienda : this.getTiendas()) {
//                listaEmpresas.add(tienda.getEmpresa());
//            }
//            if (listaEmpresas.size() == 0) {
//                throw new Exception("lista vacia.");
//            }
//        } catch (Exception e) {
//            logger.error("Error listando empresas mediante lista de tiendas: ", e);
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error listando empresas mediante lista de tiendas: ", "Error grave obteniendo empresas de tiendas.");
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage("growl", message);
//        }
//        return listaEmpresas;
//    }
    public List<String> listarEmpresas(List<Tienda> tiendas) {
        try {
            listaEmpresas = new ArrayList();
            for (Tienda tienda : tiendas) {
                listaEmpresas.add(tienda.getEmpresa());
            }
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
        EntityManagerFactory emf = null;
        EntityManager em = null;
        TypedQuery<Oferta> consultaOfertas = null;
        List<Oferta> lista = null;

        try {
            emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
            em = emf.createEntityManager();
            consultaOfertas
                    = em.createNamedQuery("Oferta.findAll", Oferta.class
                    );
            List<Oferta> lo = new ArrayList<>();
            lo = consultaOfertas.getResultList();
            this.listaOfertas = new ArrayList<>();
            this.listaOfertas = lo;
        } catch (Exception e) {
            logger.error("Error obteniendo ofertas." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo ofertas.", "Error grave obteniendo ofertas.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        } finally {
            consultaOfertas = null;
            em = null;
            emf = null;
        }
    }

    public List<String> listarRubros() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        TypedQuery<Rubro> consultaRubros = null;
        List<Rubro> listaRubros = null;
        List<String> listaNombresRubros = new ArrayList<>();

        try {
            emf = Persistence.createEntityManagerFactory("cl.duoc_Ofertas_war_1.0-SNAPSHOTPU");
            em = emf.createEntityManager();
            consultaRubros
                    = em.createNamedQuery("Rubro.findAll", Rubro.class
                    );
            listaRubros = consultaRubros.getResultList();
            for (Rubro rubro : listaRubros) {
                listaNombresRubros.add(rubro.getNombre());
            }
            this.listaRubros = listaNombresRubros;
        } catch (Exception e) {
            logger.error("Error obteniendo rubros." + e.getMessage(), e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error obteniendo rubros.", "Error grave obteniendo rubros.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        } finally {
            consultaRubros = null;
            em = null;
            emf = null;
        }
        return listaNombresRubros;
    }

    @PostConstruct
    public void init() {
        listarEmpresas(getTiendas());
//        listarOfertas();
        listarRubros();
        this.listaOfertasFiltradas = listaOfertas;
        this.rubroSeleccionado = "Todos";
        this.empresaSeleccionada = "Todas";
    }
}
