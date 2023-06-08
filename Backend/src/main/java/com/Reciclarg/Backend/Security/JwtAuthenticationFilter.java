
package com.Reciclarg.Backend.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
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
       response.getWriter().flush();
       
         super.successfulAuthentication(request, response, chain, authResult);
        
    }
}
