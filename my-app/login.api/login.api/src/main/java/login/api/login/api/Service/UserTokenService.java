package login.api.login.api.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import login.api.login.api.DTO.UserTokenDTO;
import login.api.login.api.Entity.UserToken;
import login.api.login.api.Mapper.UserTokenMapper;
import login.api.login.api.Repository.UserTokenRepository;

@Service
public class UserTokenService {
 
    @Autowired 
    UserTokenMapper umapp;

    @Autowired
    UserTokenRepository urep;

    @Transactional
    public UserTokenDTO saveUser(UserTokenDTO dtoUser) {
  Optional<UserToken> optionalToken = urep.findByUserId(dtoUser.getUserid());

    UserToken userToken;
    if (optionalToken.isPresent()) {
        userToken = optionalToken.get();
        userToken.setToken(dtoUser.getToken());

    } else {
        userToken = umapp.DTOtoUserToken(dtoUser);

    }

    urep.save(userToken); // Guarda o actualiza
    return umapp.UserTokentoDTO(userToken);
    }

    @Transactional
    public List<UserTokenDTO> getTokenUsers() {
        return ((List<UserToken>) urep.findAll())
                .stream()
                .map(umapp::UserTokentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void DeleteUser(String id){

        urep.deleteById(id);
    }
        public Optional<String> getUserIdByToken(String token) {
        return urep.findByToken(token)
                .map(UserToken::getUserId);
    }

    public void deleteToken(String token) {
        urep.deleteByToken(token);
    }
    
}