package register.api.DTO;

public class RegisterDTO {
    private String nyAp;  
    private String email;
    private String telefono;
    private String pwd;
    private String dni;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public RegisterDTO() {}

    public RegisterDTO(String nyap, String email, String telefono, String pwd, String dni) {
        this.nyAp = nyap;
        this.email = email;
        this.telefono = telefono;
        this.pwd = pwd;
        this.dni = dni;
    }

    public String getNyAp() {
        return nyAp;
    }

    public void setNyAp(String nyAp) {
        this.nyAp = nyAp;
    }



    // Getters y Setters...
    
}