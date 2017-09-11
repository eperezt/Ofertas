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
@Table(name = "TRABAJADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t")
    , @NamedQuery(name = "Trabajador.findByIdtrabajador", query = "SELECT t FROM Trabajador t WHERE t.idtrabajador = :idtrabajador")
    , @NamedQuery(name = "Trabajador.findByRut", query = "SELECT t FROM Trabajador t WHERE t.rut = :rut")
    , @NamedQuery(name = "Trabajador.findByDv", query = "SELECT t FROM Trabajador t WHERE t.dv = :dv")
    , @NamedQuery(name = "Trabajador.findByNombre", query = "SELECT t FROM Trabajador t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Trabajador.findByApellidopaterno", query = "SELECT t FROM Trabajador t WHERE t.apellidopaterno = :apellidopaterno")
    , @NamedQuery(name = "Trabajador.findByApellidomaterno", query = "SELECT t FROM Trabajador t WHERE t.apellidomaterno = :apellidomaterno")
    , @NamedQuery(name = "Trabajador.findByDireccion", query = "SELECT t FROM Trabajador t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "Trabajador.findByTelefono", query = "SELECT t FROM Trabajador t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Trabajador.findByEmail", query = "SELECT t FROM Trabajador t WHERE t.email = :email")
    , @NamedQuery(name = "Trabajador.findByFechacreacion", query = "SELECT t FROM Trabajador t WHERE t.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Trabajador.findByFechamodificacion", query = "SELECT t FROM Trabajador t WHERE t.fechamodificacion = :fechamodificacion")
    , @NamedQuery(name = "Trabajador.findByIsactivo", query = "SELECT t FROM Trabajador t WHERE t.isactivo = :isactivo")})
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTRABAJADOR")
    private BigDecimal idtrabajador;
    @Column(name = "RUT")
    private BigInteger rut;
    @Size(max = 1)
    @Column(name = "DV")
    private String dv;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "APELLIDOPATERNO")
    private String apellidopaterno;
    @Size(max = 50)
    @Column(name = "APELLIDOMATERNO")
    private String apellidomaterno;
    @Size(max = 150)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 50)
    @Column(name = "TELEFONO")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Column(name = "ISACTIVO")
    private Short isactivo;
    @ManyToMany(mappedBy = "trabajadorList")
    private List<Tienda> tiendaList;
    @JoinColumn(name = "CIUDAD_IDCIUDAD", referencedColumnName = "IDCIUDAD")
    @ManyToOne
    private Ciudad ciudadIdciudad;
    @JoinColumn(name = "USUARIO_IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @OneToOne(optional = false)
    private Usuario usuarioIdusuario;

    public Trabajador() {
    }

    public Trabajador(BigDecimal idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public BigDecimal getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(BigDecimal idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public BigInteger getRut() {
        return rut;
    }

    public void setRut(BigInteger rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Short getIsactivo() {
        return isactivo;
    }

    public void setIsactivo(Short isactivo) {
        this.isactivo = isactivo;
    }

    @XmlTransient
    public List<Tienda> getTiendaList() {
        return tiendaList;
    }

    public void setTiendaList(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    public Ciudad getCiudadIdciudad() {
        return ciudadIdciudad;
    }

    public void setCiudadIdciudad(Ciudad ciudadIdciudad) {
        this.ciudadIdciudad = ciudadIdciudad;
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
        hash += (idtrabajador != null ? idtrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.idtrabajador == null && other.idtrabajador != null) || (this.idtrabajador != null && !this.idtrabajador.equals(other.idtrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Trabajador[ idtrabajador=" + idtrabajador + " ]";
    }
    
}
