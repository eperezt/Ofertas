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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByIsdosporuno", query = "SELECT p FROM Producto p WHERE p.isdosporuno = :isdosporuno")
    , @NamedQuery(name = "Producto.findBySku", query = "SELECT p FROM Producto p WHERE p.sku = :sku")
    , @NamedQuery(name = "Producto.findByIsactivo", query = "SELECT p FROM Producto p WHERE p.isactivo = :isactivo")
    , @NamedQuery(name = "Producto.findByFechacreacion", query = "SELECT p FROM Producto p WHERE p.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Producto.findByFechamodificacion", query = "SELECT p FROM Producto p WHERE p.fechamodificacion = :fechamodificacion")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPRODUCTO")
    private BigDecimal idproducto;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PRECIO")
    private BigInteger precio;
    @Column(name = "ISDOSPORUNO")
    private BigInteger isdosporuno;
    @Size(max = 100)
    @Column(name = "SKU")
    private String sku;
    @Column(name = "ISACTIVO")
    private BigInteger isactivo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @JoinTable(name = "RL_PROD_TIENDA", joinColumns = {
        @JoinColumn(name = "PRODUCTO_IDPRODUCTO", referencedColumnName = "IDPRODUCTO")}, inverseJoinColumns = {
        @JoinColumn(name = "TIENDA_IDTIENDA", referencedColumnName = "IDTIENDA")})
    @ManyToMany
    private List<Tienda> tiendaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productoIdproducto")
    private Descuento descuento;
    @JoinColumn(name = "RUBRO_IDRUBRO", referencedColumnName = "IDRUBRO")
    @ManyToOne
    private Rubro rubroIdrubro;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productoIdproducto")
    private Oferta oferta;

    public Producto() {
    }

    public Producto(BigDecimal idproducto) {
        this.idproducto = idproducto;
    }

    public BigDecimal getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(BigDecimal idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getPrecio() {
        return precio;
    }

    public void setPrecio(BigInteger precio) {
        this.precio = precio;
    }

    public BigInteger getIsdosporuno() {
        return isdosporuno;
    }

    public void setIsdosporuno(BigInteger isdosporuno) {
        this.isdosporuno = isdosporuno;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    @XmlTransient
    public List<Tienda> getTiendaList() {
        return tiendaList;
    }

    public void setTiendaList(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    public Rubro getRubroIdrubro() {
        return rubroIdrubro;
    }

    public void setRubroIdrubro(Rubro rubroIdrubro) {
        this.rubroIdrubro = rubroIdrubro;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Producto[ idproducto=" + idproducto + " ]";
    }

}
