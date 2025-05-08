package user.api.user.api.Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
      public User(){}
    
    public User(String name, String email,String telefono,String pwd,String dni)  {
        this.NyAp = name;
        this.email = email;
        this.telefono=telefono;
        this.pwd=pwd;
        this.DNI=dni;
        this.cvu = generateCvu();
        this.alias = generateAlias();
    }

    @Id
    private String id=UUID.randomUUID().toString();

    @Column(name="NyAp",nullable = false)
    private String NyAp;

    @Column(name="Telefono",nullable = false,unique = true)
    private String telefono;

    @Column(name = "DNI",nullable=false,unique = true)
    private String DNI;

    @Column(name = "Email",nullable = false,unique = true)
    private String email;

    @Column(name="pwd",nullable =false)
    private String pwd;

    @Column(nullable = false, unique = true)
    private String cvu;

    @Column(nullable = false, unique = true)
    private String alias;


    private String generateCvu() {
        Random random = new Random();
        StringBuilder cvuBuilder = new StringBuilder();

        for (int i = 0; i < 22; i++) {
            cvuBuilder.append(random.nextInt(10)); 
        }

        return cvuBuilder.toString();
    }

private String generateAlias() {

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("palabrasalias.txt");

    if (inputStream == null) {
        throw new RuntimeException("No se pudo encontrar el archivo palabrasalias.txt en recursos.");
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        List<String> words = reader.lines()
            .map(String::trim)  
            .filter(word -> !word.isEmpty())
            .collect(Collectors.toList());

        if (words.size() < 3) {
            throw new IllegalArgumentException("El archivo palabrasalias.txt debe contener al menos 3 palabras.");
        }

        Random random = new Random();

        String word1 = words.get(random.nextInt(words.size()));
        String word2 = words.get(random.nextInt(words.size()));
        String word3 = words.get(random.nextInt(words.size()));

        return word1 + "." + word2 + "." + word3;

    } catch (IOException e) {
        throw new RuntimeException("Error al leer el archivo palabrasalias.txt", e);
    }
}

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
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
