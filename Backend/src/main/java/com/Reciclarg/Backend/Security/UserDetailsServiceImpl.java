
package com.Reciclarg.Backend.Security;


import com.Reciclarg.Backend.repository.UserRepository;
import com.Reciclarg.Backend.service.IUserService;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired 
    private UserRepository UserRepo;  
   
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       var  user = UserRepo.findOneByUsername(username);
                    
          return  new UserdetailsImpl(user);    
        
    }
    

}
