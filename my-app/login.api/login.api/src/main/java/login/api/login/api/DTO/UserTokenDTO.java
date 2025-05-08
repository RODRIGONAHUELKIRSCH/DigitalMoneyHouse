package login.api.login.api.DTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTokenDTO {
    
    public UserTokenDTO(){}

    public UserTokenDTO(String token, String userid){
        this.token=token;
        this.userid=userid;
    }
    
    private String token;

    private String userid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}