
package com.Reciclarg.Backend.Controller;

import com.Reciclarg.Backend.model.User;
import com.Reciclarg.Backend.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class ControllerUser {
    
     @Autowired   
    public IUserService userService;
     
   
     @PostMapping ("/login")
        public User getUser(@RequestParam("username") String username, @RequestParam("password") String password ){
        
        User user = userService.buscarUserByName(username);
        
        if (user != null) {
            if (user.getPassword().equals(password)) {
                //String token = getJWTToken(usuario);
                user.setPassword("");
                return user;
                
            }
        } 
        return null;

    }
        @PostMapping ("/newuser")
        public String NuevoUsuario(User user) {
            //Fatan verificar que los parametros de User no vengan vacios
            User U = userService.buscarUserByName(user.getUsername());
            if (U == null) {
                try {
                  
                   //user.setAlta(new Timestamp()); no me deja crear un Timestamp
                   user.setEnable(true);
                    
                } catch (Exception e) {
                }
    
                return "Registro ok";
            }
            else {
                return "Error: El Usermane '" + user.getUsername() +"' ya existe";
            }
        
        }
}
