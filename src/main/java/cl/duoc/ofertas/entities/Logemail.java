/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "LOGEMAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logemail.findAll", query = "SELECT l FROM Logemail l")
    , @NamedQuery(name = "Logemail.findByIdlogcorreo", query = "SELECT l FROM Logemail l WHERE l.idlogcorreo = :idlogcorreo")
    , @NamedQuery(name = "Logemail.findByEmail", query = "SELECT l FROM Logemail l WHERE l.email = :email")
    , @NamedQuery(name = "Logemail.findByFechaenvio", query = "SELECT l FROM Logemail l WHERE l.fechaenvio = :fechaenvio")})
public class Logemail implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLOGCORREO")
    private BigDecimal idlogcorreo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FECHAENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaenvio;
    @JoinColumn(name = "OFERTA_IDOFERTA", referencedColumnName = "IDOFERTA")
    @ManyToOne
    private Oferta ofertaIdoferta;

    public Logemail() {
    }

    public Logemail(BigDecimal idlogcorreo) {
        this.idlogcorreo = idlogcorreo;
    }

    public BigDecimal getIdlogcorreo() {
        return idlogcorreo;
    }

    public void setIdlogcorreo(BigDecimal idlogcorreo) {
        this.idlogcorreo = idlogcorreo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public Oferta getOfertaIdoferta() {
        return ofertaIdoferta;
    }

    public void setOfertaIdoferta(Oferta ofertaIdoferta) {
        this.ofertaIdoferta = ofertaIdoferta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlogcorreo != null ? idlogcorreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logemail)) {
            return false;
        }
        Logemail other = (Logemail) object;
        if ((this.idlogcorreo == null && other.idlogcorreo != null) || (this.idlogcorreo != null && !this.idlogcorreo.equals(other.idlogcorreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Logemail[ idlogcorreo=" + idlogcorreo + " ]";
    }
    
}
