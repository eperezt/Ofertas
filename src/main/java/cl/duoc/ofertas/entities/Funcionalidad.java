/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "FUNCIONALIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionalidad.findAll", query = "SELECT f FROM Funcionalidad f")
    , @NamedQuery(name = "Funcionalidad.findByIdfuncionalidad", query = "SELECT f FROM Funcionalidad f WHERE f.idfuncionalidad = :idfuncionalidad")
    , @NamedQuery(name = "Funcionalidad.findByNombre", query = "SELECT f FROM Funcionalidad f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "Funcionalidad.findByDescripcion", query = "SELECT f FROM Funcionalidad f WHERE f.descripcion = :descripcion")
    , @NamedQuery(name = "Funcionalidad.findByIsactivo", query = "SELECT f FROM Funcionalidad f WHERE f.isactivo = :isactivo")
    , @NamedQuery(name = "Funcionalidad.findByFechacreacion", query = "SELECT f FROM Funcionalidad f WHERE f.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Funcionalidad.findByFechamodificacion", query = "SELECT f FROM Funcionalidad f WHERE f.fechamodificacion = :fechamodificacion")})
public class Funcionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFUNCIONALIDAD")
    private BigDecimal idfuncionalidad;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ISACTIVO")
    private Short isactivo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @ManyToMany(mappedBy = "funcionalidadList")
    private List<Perfil> perfilList;

    public Funcionalidad() {
    }

    public Funcionalidad(BigDecimal idfuncionalidad) {
        this.idfuncionalidad = idfuncionalidad;
    }

    public BigDecimal getIdfuncionalidad() {
        return idfuncionalidad;
    }

    public void setIdfuncionalidad(BigDecimal idfuncionalidad) {
        this.idfuncionalidad = idfuncionalidad;
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

    public Short getIsactivo() {
        return isactivo;
    }

    public void setIsactivo(Short isactivo) {
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
    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionalidad != null ? idfuncionalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionalidad)) {
            return false;
        }
        Funcionalidad other = (Funcionalidad) object;
        if ((this.idfuncionalidad == null && other.idfuncionalidad != null) || (this.idfuncionalidad != null && !this.idfuncionalidad.equals(other.idfuncionalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Funcionalidad[ idfuncionalidad=" + idfuncionalidad + " ]";
    }
    
}
