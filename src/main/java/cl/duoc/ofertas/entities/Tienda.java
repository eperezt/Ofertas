/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "TIENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT t FROM Tienda t")
    , @NamedQuery(name = "Tienda.findByIdtienda", query = "SELECT t FROM Tienda t WHERE t.idtienda = :idtienda")
    , @NamedQuery(name = "Tienda.findByNombre", query = "SELECT t FROM Tienda t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tienda.findByDireccion", query = "SELECT t FROM Tienda t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "Tienda.findByTelefono", query = "SELECT t FROM Tienda t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Tienda.findByIsactivo", query = "SELECT t FROM Tienda t WHERE t.isactivo = :isactivo")
    , @NamedQuery(name = "Tienda.findByFechacreacion", query = "SELECT t FROM Tienda t WHERE t.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Tienda.findByFechamodificacion", query = "SELECT t FROM Tienda t WHERE t.fechamodificacion = :fechamodificacion")
    , @NamedQuery(name = "Tienda.findByEmpresa", query = "SELECT t FROM Tienda t WHERE t.empresa = :empresa")})
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTIENDA")
    private BigDecimal idtienda;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 50)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "ISACTIVO")
    private BigInteger isactivo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Size(max = 50)
    @Column(name = "EMPRESA")
    private String empresa;
    @ManyToMany(mappedBy = "tiendaList")
    private List<Oferta> ofertaList;
    @ManyToMany(mappedBy = "tiendaList")
    private List<Producto> productoList;
    @JoinTable(name = "RL_TRAB_TIENDA", joinColumns = {
        @JoinColumn(name = "TIENDA_IDTIENDA", referencedColumnName = "IDTIENDA")}, inverseJoinColumns = {
        @JoinColumn(name = "TRABAJADOR_IDTRABAJADOR", referencedColumnName = "IDTRABAJADOR")})
    @ManyToMany
    private List<Trabajador> trabajadorList;
    @JoinColumn(name = "CIUDAD_IDCIUDAD", referencedColumnName = "IDCIUDAD")
    @ManyToOne
    private Ciudad ciudadIdciudad;

    public Tienda() {
    }

    public Tienda(BigDecimal idtienda) {
        this.idtienda = idtienda;
    }

    public BigDecimal getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(BigDecimal idtienda) {
        this.idtienda = idtienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigInteger getIsactivo() {
        return isactivo;
    }

    public void setIsactivo(BigInteger isactivo) {
        this.isactivo = isactivo;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @XmlTransient
    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public Ciudad getCiudadIdciudad() {
        return ciudadIdciudad;
    }

    public void setCiudadIdciudad(Ciudad ciudadIdciudad) {
        this.ciudadIdciudad = ciudadIdciudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtienda != null ? idtienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.idtienda == null && other.idtienda != null) || (this.idtienda != null && !this.idtienda.equals(other.idtienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Tienda[ idtienda=" + idtienda + " ]";
    }
    
}
