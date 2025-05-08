package login.api.login.api.Entity;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user_token")
public class UserToken {

    public UserToken(){}

    public UserToken(String userId,String token){
        this.userId=userId;
        this.token=token;
    }

    @Id
    private String id=UUID.randomUUID().toString();;

    @Column(name = "user_id", nullable = false,unique = true)
    private String userId;

    @Column(name = "token", nullable = false, length = 2048)
    private String token;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_creacion = new Date();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    
    
}