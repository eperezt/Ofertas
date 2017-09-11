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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "VALORACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v")
    , @NamedQuery(name = "Valoracion.findByIdvaloracion", query = "SELECT v FROM Valoracion v WHERE v.idvaloracion = :idvaloracion")
    , @NamedQuery(name = "Valoracion.findByLinkfoto", query = "SELECT v FROM Valoracion v WHERE v.linkfoto = :linkfoto")
    , @NamedQuery(name = "Valoracion.findByNota", query = "SELECT v FROM Valoracion v WHERE v.nota = :nota")
    , @NamedQuery(name = "Valoracion.findByDetalle", query = "SELECT v FROM Valoracion v WHERE v.detalle = :detalle")
    , @NamedQuery(name = "Valoracion.findByFechacreacion", query = "SELECT v FROM Valoracion v WHERE v.fechacreacion = :fechacreacion")})
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDVALORACION")
    private BigDecimal idvaloracion;
    @Size(max = 255)
    @Column(name = "LINKFOTO")
    private String linkfoto;
    @Column(name = "NOTA")
    private BigInteger nota;
    @Size(max = 255)
    @Column(name = "DETALLE")
    private String detalle;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "valoracionIdvaloracion")
    private Punto punto;
    @JoinColumn(name = "OFERTA_IDOFERTA", referencedColumnName = "IDOFERTA")
    @ManyToOne
    private Oferta ofertaIdoferta;
    @JoinColumn(name = "PUNTO_IDPUNTO", referencedColumnName = "IDPUNTO")
    @OneToOne(optional = false)
    private Punto puntoIdpunto;
    @JoinColumn(name = "USUARIO_IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioIdusuario;

    public Valoracion() {
    }

    public Valoracion(BigDecimal idvaloracion) {
        this.idvaloracion = idvaloracion;
    }

    public BigDecimal getIdvaloracion() {
        return idvaloracion;
    }

    public void setIdvaloracion(BigDecimal idvaloracion) {
        this.idvaloracion = idvaloracion;
    }

    public String getLinkfoto() {
        return linkfoto;
    }

    public void setLinkfoto(String linkfoto) {
        this.linkfoto = linkfoto;
    }

    public BigInteger getNota() {
        return nota;
    }

    public void setNota(BigInteger nota) {
        this.nota = nota;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public Oferta getOfertaIdoferta() {
        return ofertaIdoferta;
    }

    public void setOfertaIdoferta(Oferta ofertaIdoferta) {
        this.ofertaIdoferta = ofertaIdoferta;
    }

    public Punto getPuntoIdpunto() {
        return puntoIdpunto;
    }

    public void setPuntoIdpunto(Punto puntoIdpunto) {
        this.puntoIdpunto = puntoIdpunto;
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
        hash += (idvaloracion != null ? idvaloracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.idvaloracion == null && other.idvaloracion != null) || (this.idvaloracion != null && !this.idvaloracion.equals(other.idvaloracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Valoracion[ idvaloracion=" + idvaloracion + " ]";
    }
    
}
