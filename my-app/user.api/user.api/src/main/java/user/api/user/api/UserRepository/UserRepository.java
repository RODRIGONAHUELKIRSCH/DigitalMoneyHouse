package user.api.user.api.UserRepository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import user.api.user.api.Entity.User;

public interface UserRepository extends CrudRepository<User,String>{
    
    Optional<User> findByEmail(String email);
}