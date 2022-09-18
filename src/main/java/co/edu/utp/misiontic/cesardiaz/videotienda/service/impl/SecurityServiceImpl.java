package co.edu.utp.misiontic.cesardiaz.videotienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.UserRequest;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.UserResponse;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.User;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.UserRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.service.SecurityService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    @Override
    public UserResponse validateUser(String username, String password) {
        // var userOp = userRepository.findById(username);
        // if (userOp.isEmpty()) {
        // throw new RuntimeException("Usuario no existente");
        // }

        // var user = userOp.get();
        // if (!user.getActive()) {
        // throw new RuntimeException("Usuario inactivo");
        // }

        // if (!user.getPassword().equals(password)) {
        // throw new RuntimeException("Credenciales inválidas");
        // }

        var userOp = userRepository.findByUsernameAndPasswordAndActiveIsTrue(username, password);
        if (userOp.isEmpty()) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        var user = userOp.get();
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .admin(user.getAdmin())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> UserResponse.builder()
                        .username(u.getUsername())
                        .name(u.getName())
                        .email(u.getEmail())
                        .admin(u.getAdmin())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        var userOp = userRepository.findById(username);
        if (userOp.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        var user = userOp.get();
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .admin(user.getAdmin())
                .build();
    }

    @Override
    public void createUser(UserRequest user) {

        var userOp = userRepository.findById(user.getUsername()); 
        if(userOp.isPresent()){
            throw new RuntimeException("No puede utilizar ese nombre de usuario");
        }

        userOp = userRepository.findByEmail(user.getEmail());
        if(userOp.isPresent()){
            throw new RuntimeException("Ya existe un usuario registrado con ese correo electrónico");
        }
        
        var userDb = new User();
        userDb.setUsername(user.getUsername());
        userDb.setPassword(user.getPassword());
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setActive(true);
        userDb.setAdmin(user.getAdmin());
        userDb = userRepository.save(userDb);

    }

    @Override
    public void updateUser(UserRequest user) {
        var userOp = userRepository.findById(user.getUsername());
        if (userOp.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        var userDb = userOp.get();
        userDb.setUsername(user.getUsername());
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setAdmin(user.getAdmin());
        userDb = userRepository.save(userDb);
    }

    @Override
    public void deleteUser(String username) {
        var userOp = userRepository.findById(username);
        if (userOp.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        userRepository.delete(userOp.get());
    }

    @Override
    public void activateUser(String username) {
        var userOp = userRepository.findById(username);
        if(userOp.isEmpty()){
            throw new RuntimeException("El usuario no existe");
        }

        var user = userOp.get();
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void inactivateUser(String username) {
        var userOp = userRepository.findById(username);
        if(userOp.isEmpty()){
            throw new RuntimeException("El usuario no existe");
        }

        var user = userOp.get();
        user.setActive(false);
        userRepository.save(user);

    }

}
