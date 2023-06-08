
package com.Reciclarg.Backend.repository;

import com.Reciclarg.Backend.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long>  {
   public User findOneByUsername(String username);
}
