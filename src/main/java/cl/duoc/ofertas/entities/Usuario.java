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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByIsactivo", query = "SELECT u FROM Usuario u WHERE u.isactivo = :isactivo")
    , @NamedQuery(name = "Usuario.findByFechacreacion", query = "SELECT u FROM Usuario u WHERE u.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Usuario.findByFechamodificacion", query = "SELECT u FROM Usuario u WHERE u.fechamodificacion = :fechamodificacion")
    , @NamedQuery(name = "Usuario.findByIdsession", query = "SELECT u FROM Usuario u WHERE u.idsession = :idsession")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUSUARIO")
    private BigDecimal idusuario;
    @Size(max = 20)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ISACTIVO")
    private BigInteger isactivo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Size(max = 255)
    @Column(name = "IDSESSION")
    private String idsession;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarioIdusuario")
    private Consumidor consumidor;
    @OneToMany(mappedBy = "usuarioIdusuario")
    private List<Cupon> cuponList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarioIdusuario")
    private Trabajador trabajador;
    @OneToMany(mappedBy = "usuarioIdusuario")
    private List<Punto> puntoList;
    @JoinColumn(name = "PERFIL_IDPERFIL", referencedColumnName = "IDPERFIL")
    @ManyToOne
    private Perfil perfilIdperfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdusuario")
    private List<Valoracion> valoracionList;

    public Usuario() {
    }

    public Usuario(BigDecimal idusuario) {
        this.idusuario = idusuario;
    }

    public BigDecimal getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(BigDecimal idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getIdsession() {
        return idsession;
    }

    public void setIdsession(String idsession) {
        this.idsession = idsession;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    @XmlTransient
    public List<Cupon> getCuponList() {
        return cuponList;
    }

    public void setCuponList(List<Cupon> cuponList) {
        this.cuponList = cuponList;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    @XmlTransient
    public List<Punto> getPuntoList() {
        return puntoList;
    }

    public void setPuntoList(List<Punto> puntoList) {
        this.puntoList = puntoList;
    }

    public Perfil getPerfilIdperfil() {
        return perfilIdperfil;
    }

    public void setPerfilIdperfil(Perfil perfilIdperfil) {
        this.perfilIdperfil = perfilIdperfil;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
