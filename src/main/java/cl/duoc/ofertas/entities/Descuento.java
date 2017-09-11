/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "DESCUENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descuento.findAll", query = "SELECT d FROM Descuento d")
    , @NamedQuery(name = "Descuento.findByIddescuento", query = "SELECT d FROM Descuento d WHERE d.iddescuento = :iddescuento")
    , @NamedQuery(name = "Descuento.findByNombre", query = "SELECT d FROM Descuento d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Descuento.findByDescripcion", query = "SELECT d FROM Descuento d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Descuento.findByIsporcentaje", query = "SELECT d FROM Descuento d WHERE d.isporcentaje = :isporcentaje")
    , @NamedQuery(name = "Descuento.findByPorcentajedescuento", query = "SELECT d FROM Descuento d WHERE d.porcentajedescuento = :porcentajedescuento")
    , @NamedQuery(name = "Descuento.findByIspreciodirecto", query = "SELECT d FROM Descuento d WHERE d.ispreciodirecto = :ispreciodirecto")
    , @NamedQuery(name = "Descuento.findByPreciodescuento", query = "SELECT d FROM Descuento d WHERE d.preciodescuento = :preciodescuento")})
public class Descuento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDESCUENTO")
    private BigDecimal iddescuento;
    @Size(max = 150)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ISPORCENTAJE")
    private Short isporcentaje;
    @Column(name = "PORCENTAJEDESCUENTO")
    private BigDecimal porcentajedescuento;
    @Column(name = "ISPRECIODIRECTO")
    private Short ispreciodirecto;
    @Column(name = "PRECIODESCUENTO")
    private BigInteger preciodescuento;
    @JoinColumn(name = "PRODUCTO_IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    @OneToOne(optional = false)
    private Producto productoIdproducto;

    public Descuento() {
    }

    public Descuento(BigDecimal iddescuento) {
        this.iddescuento = iddescuento;
    }

    public BigDecimal getIddescuento() {
        return iddescuento;
    }

    public void setIddescuento(BigDecimal iddescuento) {
        this.iddescuento = iddescuento;
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

    public Short getIsporcentaje() {
        return isporcentaje;
    }

    public void setIsporcentaje(Short isporcentaje) {
        this.isporcentaje = isporcentaje;
    }

    public BigDecimal getPorcentajedescuento() {
        return porcentajedescuento;
    }

    public void setPorcentajedescuento(BigDecimal porcentajedescuento) {
        this.porcentajedescuento = porcentajedescuento;
    }

    public Short getIspreciodirecto() {
        return ispreciodirecto;
    }

    public void setIspreciodirecto(Short ispreciodirecto) {
        this.ispreciodirecto = ispreciodirecto;
    }

    public BigInteger getPreciodescuento() {
        return preciodescuento;
    }

    public void setPreciodescuento(BigInteger preciodescuento) {
        this.preciodescuento = preciodescuento;
    }

    public Producto getProductoIdproducto() {
        return productoIdproducto;
    }

    public void setProductoIdproducto(Producto productoIdproducto) {
        this.productoIdproducto = productoIdproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddescuento != null ? iddescuento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descuento)) {
            return false;
        }
        Descuento other = (Descuento) object;
        if ((this.iddescuento == null && other.iddescuento != null) || (this.iddescuento != null && !this.iddescuento.equals(other.iddescuento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Descuento[ iddescuento=" + iddescuento + " ]";
    }
    
}
