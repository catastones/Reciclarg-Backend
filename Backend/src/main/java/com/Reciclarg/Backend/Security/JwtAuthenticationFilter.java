
package com.Reciclarg.Backend.Security;

import com.Reciclarg.Backend.model.User;
import com.Reciclarg.Backend.repository.UserRepository;
import com.Reciclarg.Backend.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    @Autowired   
    public IUserService userService;
    @Override
     public Authentication attemptAuthentication(HttpServletRequest request, 
                                                HttpServletResponse response) 
                                                    throws AuthenticationException {
         
         AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException ex) {
            Logger.getLogger(JwtAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
            authCredentials.getUsername(), 
                authCredentials.getPassword(),
                Collections.emptyList()
        );
         return getAuthenticationManager().authenticate(usernamePAT);
        
    }
     @Override
     protected void successfulAuthentication(HttpServletRequest request, 
             HttpServletResponse response, FilterChain chain, 
             Authentication authResult) throws IOException, ServletException {
         
       UserdetailsImpl userDetails = (UserdetailsImpl) authResult.getPrincipal();
       
       
       String token = TokenUtil.createToken(userDetails.getNombre(), userDetails.getUsername());
       response.addHeader("Authorization", "Bearer " + token);
      
       //User usuario = UserRepo.findOneByUsername(userDetails.getUsername());
       //usuario.setPassword("");
       //usuario.setToken(token);
     
      
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(userDetails.toString());
        out.flush();
         System.out.println("Usuario Identificado");
       //response.getWriter().flush();
       
         super.successfulAuthentication(request, response, chain, authResult);
        
    }
}
