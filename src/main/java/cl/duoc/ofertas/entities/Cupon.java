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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "CUPON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cupon.findAll", query = "SELECT c FROM Cupon c")
    , @NamedQuery(name = "Cupon.findByIdcupon", query = "SELECT c FROM Cupon c WHERE c.idcupon = :idcupon")
    , @NamedQuery(name = "Cupon.findByPuntos", query = "SELECT c FROM Cupon c WHERE c.puntos = :puntos")
    , @NamedQuery(name = "Cupon.findByDescuento", query = "SELECT c FROM Cupon c WHERE c.descuento = :descuento")
    , @NamedQuery(name = "Cupon.findByTope", query = "SELECT c FROM Cupon c WHERE c.tope = :tope")
    , @NamedQuery(name = "Cupon.findByFechainiciovigencia", query = "SELECT c FROM Cupon c WHERE c.fechainiciovigencia = :fechainiciovigencia")
    , @NamedQuery(name = "Cupon.findByFechafinvigencia", query = "SELECT c FROM Cupon c WHERE c.fechafinvigencia = :fechafinvigencia")
    , @NamedQuery(name = "Cupon.findByIsutilizado", query = "SELECT c FROM Cupon c WHERE c.isutilizado = :isutilizado")})
public class Cupon implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCUPON")
    private BigDecimal idcupon;
    @Column(name = "PUNTOS")
    private BigInteger puntos;
    @Column(name = "DESCUENTO")
    private BigDecimal descuento;
    @Column(name = "TOPE")
    private BigInteger tope;
    @Column(name = "FECHAINICIOVIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainiciovigencia;
    @Column(name = "FECHAFINVIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafinvigencia;
    @Column(name = "ISUTILIZADO")
    private BigInteger isutilizado;
    @JoinTable(name = "RL_RUBRO_CUPON", joinColumns = {
        @JoinColumn(name = "CUPON_IDCUPON", referencedColumnName = "IDCUPON")}, inverseJoinColumns = {
        @JoinColumn(name = "RUBRO_IDRUBRO", referencedColumnName = "IDRUBRO")})
    @ManyToMany
    private List<Rubro> rubroList;
    @JoinColumn(name = "USUARIO_IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario usuarioIdusuario;

    public Cupon() {
    }

    public Cupon(BigDecimal idcupon) {
        this.idcupon = idcupon;
    }

    public BigDecimal getIdcupon() {
        return idcupon;
    }

    public void setIdcupon(BigDecimal idcupon) {
        this.idcupon = idcupon;
    }

    public BigInteger getPuntos() {
        return puntos;
    }

    public void setPuntos(BigInteger puntos) {
        this.puntos = puntos;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigInteger getTope() {
        return tope;
    }

    public void setTope(BigInteger tope) {
        this.tope = tope;
    }

    public Date getFechainiciovigencia() {
        return fechainiciovigencia;
    }

    public void setFechainiciovigencia(Date fechainiciovigencia) {
        this.fechainiciovigencia = fechainiciovigencia;
    }

    public Date getFechafinvigencia() {
        return fechafinvigencia;
    }

    public void setFechafinvigencia(Date fechafinvigencia) {
        this.fechafinvigencia = fechafinvigencia;
    }

    public BigInteger getIsutilizado() {
        return isutilizado;
    }

    public void setIsutilizado(BigInteger isutilizado) {
        this.isutilizado = isutilizado;
    }

    @XmlTransient
    public List<Rubro> getRubroList() {
        return rubroList;
    }

    public void setRubroList(List<Rubro> rubroList) {
        this.rubroList = rubroList;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcupon != null ? idcupon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cupon)) {
            return false;
        }
        Cupon other = (Cupon) object;
        if ((this.idcupon == null && other.idcupon != null) || (this.idcupon != null && !this.idcupon.equals(other.idcupon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Cupon[ idcupon=" + idcupon + " ]";
    }
    
}
