
package com.Reciclarg.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "zona")
public class Zona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nombre;
    private String descripcion;
    
    @JsonIgnore
    @OneToMany(mappedBy = "zona")
    private Set<User> user = new HashSet<>(); 
    
}
