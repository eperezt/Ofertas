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
@Table(name = "CONSUMIDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumidor.findAll", query = "SELECT c FROM Consumidor c")
    , @NamedQuery(name = "Consumidor.findByIdconsumidor", query = "SELECT c FROM Consumidor c WHERE c.idconsumidor = :idconsumidor")
    , @NamedQuery(name = "Consumidor.findByRut", query = "SELECT c FROM Consumidor c WHERE c.rut = :rut")
    , @NamedQuery(name = "Consumidor.findByDv", query = "SELECT c FROM Consumidor c WHERE c.dv = :dv")
    , @NamedQuery(name = "Consumidor.findByNombre", query = "SELECT c FROM Consumidor c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Consumidor.findByApellidopaterno", query = "SELECT c FROM Consumidor c WHERE c.apellidopaterno = :apellidopaterno")
    , @NamedQuery(name = "Consumidor.findByApellidomaterno", query = "SELECT c FROM Consumidor c WHERE c.apellidomaterno = :apellidomaterno")
    , @NamedQuery(name = "Consumidor.findByDireccion", query = "SELECT c FROM Consumidor c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Consumidor.findByTelefono", query = "SELECT c FROM Consumidor c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Consumidor.findByEmail", query = "SELECT c FROM Consumidor c WHERE c.email = :email")
    , @NamedQuery(name = "Consumidor.findByIsactivo", query = "SELECT c FROM Consumidor c WHERE c.isactivo = :isactivo")
    , @NamedQuery(name = "Consumidor.findByFechacreacion", query = "SELECT c FROM Consumidor c WHERE c.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Consumidor.findByFechamodificacion", query = "SELECT c FROM Consumidor c WHERE c.fechamodificacion = :fechamodificacion")
    , @NamedQuery(name = "Consumidor.findByIsrecibeofertas", query = "SELECT c FROM Consumidor c WHERE c.isrecibeofertas = :isrecibeofertas")})
public class Consumidor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCONSUMIDOR")
    private BigDecimal idconsumidor;
    @Column(name = "RUT")
    private BigInteger rut;
    @Column(name = "DV")
    private Character dv;
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
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ISACTIVO")
    private Short isactivo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Column(name = "ISRECIBEOFERTAS")
    private Short isrecibeofertas;
    @JoinColumn(name = "CIUDAD_IDCIUDAD", referencedColumnName = "IDCIUDAD")
    @ManyToOne
    private Ciudad ciudadIdciudad;
    @JoinColumn(name = "USUARIO_IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @OneToOne(optional = false)
    private Usuario usuarioIdusuario;

    public Consumidor() {
    }

    public Consumidor(BigDecimal idconsumidor) {
        this.idconsumidor = idconsumidor;
    }

    public BigDecimal getIdconsumidor() {
        return idconsumidor;
    }

    public void setIdconsumidor(BigDecimal idconsumidor) {
        this.idconsumidor = idconsumidor;
    }

    public BigInteger getRut() {
        return rut;
    }

    public void setRut(BigInteger rut) {
        this.rut = rut;
    }

    public Character getDv() {
        return dv;
    }

    public void setDv(Character dv) {
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

    public Short getIsrecibeofertas() {
        return isrecibeofertas;
    }

    public void setIsrecibeofertas(Short isrecibeofertas) {
        this.isrecibeofertas = isrecibeofertas;
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
        hash += (idconsumidor != null ? idconsumidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumidor)) {
            return false;
        }
        Consumidor other = (Consumidor) object;
        if ((this.idconsumidor == null && other.idconsumidor != null) || (this.idconsumidor != null && !this.idconsumidor.equals(other.idconsumidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.duoc.ofertas.entities.Consumidor[ idconsumidor=" + idconsumidor + " ]";
    }
    
}
