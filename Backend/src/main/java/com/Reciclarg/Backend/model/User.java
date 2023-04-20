
package com.Reciclarg.Backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.security.Timestamp;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private Timestamp  alta;
    private Timestamp  baja;
    private boolean enable; 
    @Transient
    private String token;
    
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getAlta() {
        return alta;
    }

    public void setAlta(Timestamp alta) {
        this.alta = alta;
    }

    public Timestamp getBaja() {
        return baja;
    }

    public void setBaja(Timestamp baja) {
        this.baja = baja;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public FotoPerfil getFotoPerfi() {
        return fotoPerfi;
    }

    public void setFotoPerfi(FotoPerfil fotoPerfi) {
        this.fotoPerfi = fotoPerfi;
    }
    
    
    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zona zona; 
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private FotoPerfil fotoPerfi;
  
    
    
    
}
