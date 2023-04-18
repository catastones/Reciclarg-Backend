
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
    
    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zona zona; 
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private FotoPerfil fotoPerfi;
  
    
    
    
}
