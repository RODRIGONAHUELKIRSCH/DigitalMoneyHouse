package user.api.user.api.UserDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    
    private String NyAp;

    private String telefono;

    private String DNI;

    private String email;

    private String pwd;

    private String cvu;

    private String alias;

    public UserDTO(){}

    public UserDTO(String NyAp,String telefono,String DNI,String email,String pwd, String cvu,String alias ){

        this.NyAp=NyAp;
        this.telefono=telefono;
        this.DNI=DNI;
        this.email=email;
        this.pwd=pwd;
        this.cvu=cvu;
        this.alias=alias;
    }

    
    public UserDTO(String NyAp, String email){
        this.NyAp=NyAp;
        this.email=email;
    }

    public String getNyAp() {
        return NyAp;
    }

    public void setNyAp(String nyAp) {
        NyAp = nyAp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
