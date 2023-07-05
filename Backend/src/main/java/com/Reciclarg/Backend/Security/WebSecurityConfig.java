
package com.Reciclarg.Backend.Security;

import jakarta.servlet.MultipartConfigElement;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    
    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    
    @Bean
    SecurityFilterChain web(HttpSecurity http, AuthenticationManager authManager) throws Exception {
     
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        
     
        return http
         .cors(withDefaults())
        .csrf().disable() // (2)
        
        .authorizeRequests()
                .requestMatchers("/zona/listarzonas").permitAll()
                .requestMatchers("/user/newuser").permitAll()
                .requestMatchers("/nosotros/listNosotros").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
           
                
            .build();
         
    }
    /*
   @Bean
    UserDetailsService userDetailsService() {
      InMemoryUserDetailsManager manager  = new InMemoryUserDetailsManager();
      manager.createUser(User.withUsername("admin")
                            .password(passwordEncoder().encode("admin"))
                        .roles()
                        .build());
    return manager;
  }
*/

    
  @Bean
    CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
                configuration.setAllowCredentials(true);
                configuration.setExposedHeaders(List.of("Authorization"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;	
    }
    
  @Bean
  AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
      return http.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(userDetailsService)
              .passwordEncoder(passwordEncoder())
              .and().build();
  }
  
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
