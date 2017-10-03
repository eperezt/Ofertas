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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "PUNTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Punto.findAll", query = "SELECT p FROM Punto p")
    , @NamedQuery(name = "Punto.findByIdpunto", query = "SELECT p FROM Punto p WHERE p.idpunto = :idpunto")
    , @NamedQuery(name = "Punto.findByCantidad", query = "SELECT p FROM Punto p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "Punto.findByFecha", query = "SELECT p FROM Punto p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Punto.findByIscobrado", query = "SELECT p FROM Punto p WHERE p.iscobrado = :iscobrado")})
public class Punto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPUNTO")
    private BigDecimal idpunto;
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "ISCOBRADO")
    private BigInteger iscobrado;
    @JoinColumn(name = "USUARIO_IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario usuarioIdusuario;
    @JoinColumn(name = "VALORACION_IDVALORACION", referencedColumnName = "IDVALORACION")
    @OneToOne(optional = false)
    private Valoracion valoracionIdvaloracion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "puntoIdpunto")
    private Valoracion valoracion;

    public Punto() {
    }

    public Punto(BigDecimal idpunto) {
        this.idpunto = idpunto;
    }

    public BigDecimal getIdpunto() {
        return idpunto;
    }

    public void setIdpunto(BigDecimal idpunto) {
        this.idpunto = idpunto;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getIscobrado() {
        return iscobrado;
    }

    public void setIscobrado(BigInteger iscobrado) {
        this.iscobrado = iscobrado;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    public Valoracion getValoracionIdvaloracion() {
        return valoracionIdvaloracion;
    }

    public void setValoracionIdvaloracion(Valoracion valoracionIdvaloracion) {
        this.valoracionIdvaloracion = valoracionIdvaloracion;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpunto != null ? idpunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Punto)) {
            return false;
        }
        Punto other = (Punto) object;
        if ((this.idpunto == null && other.idpunto != null) || (this.idpunto != null && !this.idpunto.equals(other.idpunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Punto[ idpunto=" + idpunto + " ]";
    }
    
}
