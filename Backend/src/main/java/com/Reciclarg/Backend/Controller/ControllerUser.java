
package com.Reciclarg.Backend.Controller;



import com.Reciclarg.Backend.model.FotoPerfil;
import com.Reciclarg.Backend.model.User;
import com.Reciclarg.Backend.service.IUserService;
import com.Reciclarg.Backend.util.ImageUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
public class ControllerUser {
   //  @Autowired
  //private AuthenticationManager authenticationManager;
     @Autowired   
    public IUserService userService;
     
    //@Autowired
    //private JwtUtilService jwtUtilService;
    
    //@Autowired
    //private UserDetailsService usuarioDetailsService;
   
     @PostMapping ("/login")
        public User getUser(@RequestParam("username") String username, @RequestParam("password") String password ){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();//BCryptPasswordEncoder clase parte de spring security cryto para encriptar pass 
        
        User user = userService.buscarUserByName(username);
        
        if (user != null) {
            if (bcrypt.matches(password, user.getPassword())) {// funcion matches verifica la igualdad entre el pass enviado y el pass encriptado
                
                // authenticationManager.authenticate(
                //    new UsernamePasswordAuthenticationToken(username, password));
                
               // final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username);
               // final String jwt = jwtUtilService.generateToken(userDetails);
                
                //String token = jwtUtilService.getJWTToken(username);
                user.setPassword("");
               // user.setToken(jwt);
                return user;
                
            }
        } 
        return null;

    }
        
        
        @PostMapping (path ="/newuser", consumes = {MULTIPART_FORM_DATA_VALUE})
        public String NuevoUsuario(@RequestPart("user")  User user, @RequestPart("image") MultipartFile file) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(); //BCryptPasswordEncoder clase parte de spring security cryto para encriptar pass 
        //Fatan verificar que los parametros de User no vengan vacios
            FotoPerfil foto = new FotoPerfil();
            
            User VerificaUser = userService.buscarUserByName(user.getUsername());
            if (VerificaUser == null) { // chequeamos que el username no exista
                try {
                  
                   user.setEnable(true);
                   //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss z");
                   Date Now = new Date(System.currentTimeMillis());
                   user.setAlta(Now);
                   user.setPassword(bcrypt.encode(user.getPassword()));
                    //userService.SaveUsuario(user);
                      if (file != null) { // si envia foto la guardamos
                    
                   // Long idFoto = userService.buscarUserByName(user.getUsername()).getId();
                    foto = FotoPerfil.builder()
                    .id(0)
                    .nombre(user.getUsername() + "_FotoPerfil")
                    .type(file.getContentType())
                    .photo(ImageUtil.compressImage(file.getBytes())).build();
                    user.setFotoPerfi(foto);                    

                    }
                userService.SaveUsuario(user);
                     
                    
                } catch (Exception e) {
                    return e.toString();
                }              
    
                return "Registro ok";
            }
            else {
                return "Error: El Usermane '" + user.getUsername() +"' ya existe";
            }
        
        }
        
          @GetMapping ("/verUser/{id}")
          @ResponseBody
          public User VerPerso(@PathVariable Long id){
              User usuario = userService.buscarUserById(id);
              usuario.setPassword("");
          return usuario;
        
    }
      
    /**
     *
     * @param authenticationReq
     * @return
     */
          /*
    @PostMapping("/login2")
    public ResponseEntity<User> authenticate(@RequestBody AuthenticationReq authenticationReq) {
    

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationReq.getUsername(),
            authenticationReq.getPassword()));

    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
        authenticationReq.getUsername());

    final String jwt = jwtUtilService.generateToken(userDetails);
        User usuarioOk = userService.buscarUserByName(authenticationReq.getUsername());
        usuarioOk.setPassword("");
        usuarioOk.setToken(jwt);
    return ResponseEntity.ok(usuarioOk);
  }
*/
}
