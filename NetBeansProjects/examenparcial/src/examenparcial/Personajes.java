/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenparcial;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "personajes")
@NamedQueries({
    @NamedQuery(name = "Personajes.findAll", query = "SELECT p FROM Personajes p")})
public class Personajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpersonajes")
    private Integer idpersonajes;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "alias")
    private String alias;
    @Column(name = "poder")
    private String poder;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;

    public Personajes() {
    }

    public Personajes(Integer idpersonajes) {
        this.idpersonajes = idpersonajes;
    }

    public Integer getIdpersonajes() {
        return idpersonajes;
    }

    public void setIdpersonajes(Integer idpersonajes) {
        this.idpersonajes = idpersonajes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonajes != null ? idpersonajes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personajes)) {
            return false;
        }
        Personajes other = (Personajes) object;
        if ((this.idpersonajes == null && other.idpersonajes != null) || (this.idpersonajes != null && !this.idpersonajes.equals(other.idpersonajes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "examenparcial.Personajes[ idpersonajes=" + idpersonajes + " ]";
    }
    
}
