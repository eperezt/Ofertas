/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "CIUDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.findByIdciudad", query = "SELECT c FROM Ciudad c WHERE c.idciudad = :idciudad")
    , @NamedQuery(name = "Ciudad.findByNombre", query = "SELECT c FROM Ciudad c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Ciudad.findByIsactivo", query = "SELECT c FROM Ciudad c WHERE c.isactivo = :isactivo")})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCIUDAD")
    private BigDecimal idciudad;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ISACTIVO")
    private BigInteger isactivo;
    @OneToMany(mappedBy = "ciudadIdciudad")
    private List<Consumidor> consumidorList;
    @OneToMany(mappedBy = "ciudadIdciudad")
    private List<Trabajador> trabajadorList;
    @OneToMany(mappedBy = "ciudadIdciudad")
    private List<Tienda> tiendaList;

    public Ciudad() {
    }

    public Ciudad(BigDecimal idciudad) {
        this.idciudad = idciudad;
    }

    public BigDecimal getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(BigDecimal idciudad) {
        this.idciudad = idciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getIsactivo() {
        return isactivo;
    }

    public void setIsactivo(BigInteger isactivo) {
        this.isactivo = isactivo;
    }

    @XmlTransient
    public List<Consumidor> getConsumidorList() {
        return consumidorList;
    }

    public void setConsumidorList(List<Consumidor> consumidorList) {
        this.consumidorList = consumidorList;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    @XmlTransient
    public List<Tienda> getTiendaList() {
        return tiendaList;
    }

    public void setTiendaList(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciudad != null ? idciudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.idciudad == null && other.idciudad != null) || (this.idciudad != null && !this.idciudad.equals(other.idciudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Ciudad[ idciudad=" + idciudad + " ]";
    }

}
