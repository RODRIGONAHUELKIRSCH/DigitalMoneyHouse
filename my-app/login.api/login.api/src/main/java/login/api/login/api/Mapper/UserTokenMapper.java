package login.api.login.api.Mapper;

import org.springframework.stereotype.Component;

import login.api.login.api.DTO.UserTokenDTO;
import login.api.login.api.Entity.UserToken;

@Component
public class UserTokenMapper {
    
    public UserTokenDTO UserTokentoDTO(UserToken UserToken){
        UserTokenDTO utdto=new UserTokenDTO();
        utdto.setToken(UserToken.getToken());
        utdto.setUserid(UserToken.getUserId());
        return utdto;
    }

    public UserToken DTOtoUserToken(UserTokenDTO userToken){
        UserToken ut=new UserToken();
        ut.setToken(userToken.getToken());
        ut.setUserId(userToken.getUserid());
        return ut;
    }
}