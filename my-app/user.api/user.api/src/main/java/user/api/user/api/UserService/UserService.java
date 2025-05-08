package user.api.user.api.UserService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.api.user.api.Entity.User;
import user.api.user.api.UserDTO.UserDTO;
import user.api.user.api.UserMapper.UserMapper;
import user.api.user.api.UserRepository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository urep;

    @Autowired
    UserMapper umap;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO saveUser(UserDTO dtoUser) {
        User user = umap.DTOtoUser(dtoUser);
        String encodedPassword = passwordEncoder.encode(user.getPwd()); // Codifica la contraseña
        user.setPwd(encodedPassword);
        urep.save(user);
        return umap.UsertoDTO(user);
    }

    @Transactional
    public List<UserDTO> getUsers() {
        return ((List<User>) urep.findAll())
                .stream()
                .map(umap::UsertoDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserNameEmail() {
       
        return((List<User>) urep.findAll())
                    .stream()
                    .map(user -> new UserDTO(user.getNyAp(), user.getEmail()))
                    .collect(Collectors.toList());
    }


    @Transactional
    public void DeleteUser(String id){

        urep.deleteById(id);
    }

    public String validatePassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en la base de datos"));

        if (!passwordEncoder.matches(password, user.getPwd())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user.getId(); // Aquí retornamos el UUID como String
    }
}