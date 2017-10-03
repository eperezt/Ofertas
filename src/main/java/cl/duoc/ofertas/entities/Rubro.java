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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "RUBRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubro.findAll", query = "SELECT r FROM Rubro r")
    , @NamedQuery(name = "Rubro.findByIdrubro", query = "SELECT r FROM Rubro r WHERE r.idrubro = :idrubro")
    , @NamedQuery(name = "Rubro.findByNombre", query = "SELECT r FROM Rubro r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "Rubro.findByDescripcion", query = "SELECT r FROM Rubro r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Rubro.findByIsactivo", query = "SELECT r FROM Rubro r WHERE r.isactivo = :isactivo")
    , @NamedQuery(name = "Rubro.findByFechacreacion", query = "SELECT r FROM Rubro r WHERE r.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Rubro.findByFechamodificacion", query = "SELECT r FROM Rubro r WHERE r.fechamodificacion = :fechamodificacion")})
public class Rubro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRUBRO")
    private BigDecimal idrubro;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ISACTIVO")
    private BigInteger isactivo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @ManyToMany(mappedBy = "rubroList")
    private List<Cupon> cuponList;
    @OneToMany(mappedBy = "rubroIdrubro")
    private List<Producto> productoList;

    public Rubro() {
    }

    public Rubro(BigDecimal idrubro) {
        this.idrubro = idrubro;
    }

    public BigDecimal getIdrubro() {
        return idrubro;
    }

    public void setIdrubro(BigDecimal idrubro) {
        this.idrubro = idrubro;
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
    public List<Cupon> getCuponList() {
        return cuponList;
    }

    public void setCuponList(List<Cupon> cuponList) {
        this.cuponList = cuponList;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubro != null ? idrubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubro)) {
            return false;
        }
        Rubro other = (Rubro) object;
        if ((this.idrubro == null && other.idrubro != null) || (this.idrubro != null && !this.idrubro.equals(other.idrubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Rubro[ idrubro=" + idrubro + " ]";
    }
    
}
