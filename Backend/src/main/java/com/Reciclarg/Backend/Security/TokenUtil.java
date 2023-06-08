
package com.Reciclarg.Backend.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class TokenUtil {
    
    private final static String ACCES_TOKEN_SECRET = "\\x83uV\\x11\\xd2\\xe6>\\xea\\x9a\\x8f\\xba\\x11-\\xd4\\x1cc\\xbe\\xc6\\x07\\xfa\\x18\\xd5\\x81\\xaa\\xae\\x14\\x1b\\xb0\\xf5\\x94\\x1cx=";
    private final static Long ACCES_VALIDITY_SECONDS = 2_292_000L;//8hs de valides
    
    public static String createToken (String nombre, String username) {
        long expirationTime = ACCES_VALIDITY_SECONDS * 1_000;
        
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime );
        
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCES_TOKEN_SECRET.getBytes()))
                .compact();
    }
    
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCES_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
