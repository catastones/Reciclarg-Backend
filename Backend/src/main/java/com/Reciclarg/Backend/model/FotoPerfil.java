
package com.Reciclarg.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "fotoPerfil")
public class FotoPerfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String mime;
    
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;
    
     @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
      
    
}
