
package com.Reciclarg.Backend.Security;

import com.Reciclarg.Backend.model.FotoPerfil;
import com.Reciclarg.Backend.model.User;
import com.Reciclarg.Backend.model.Zona;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class UserdetailsImpl implements UserDetails{
    
    private final User usuario;
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        
       return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public String getNombre(){
        return usuario.getNombre();
    }
     public String getApellido(){
        return usuario.getApellido();
    }
     
    public Long getId(){
        return usuario.getId();
    }
    
    public String getZona(){
        return usuario.getZona().getNombre();
    }
    
    
    
   
    @Override  
    public String toString(){
     return String.format("{\n" +
"    \"id\":\"%s\",\n" +
"    \"nombre\":\"%s\",\n" +
"    \"apellido\":\"%s\",\n" +
"    \"zona\":\"%s\",\n" +     
"    \"username\":\"%s\"\n" +             
"}", getId(), getNombre(), getApellido(),getZona(),getUsername());

       
    }
     
}
