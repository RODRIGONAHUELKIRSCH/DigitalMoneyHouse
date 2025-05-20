package login.api.login.api.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import login.api.login.api.Entity.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken,String> {
    
    Optional<UserToken> findByUserId(String userId);
    Optional<UserToken> findByToken(String token);
    void deleteByToken(String token);
}