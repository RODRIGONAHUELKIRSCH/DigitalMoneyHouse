package login.api.login.api.Repository;

import org.springframework.data.repository.CrudRepository;
import login.api.login.api.Entity.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken,String> {
    
}