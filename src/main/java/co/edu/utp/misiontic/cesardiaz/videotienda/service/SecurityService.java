package co.edu.utp.misiontic.cesardiaz.videotienda.service;

import java.util.List;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.UserRequest;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.UserResponse;

public interface SecurityService {

    UserResponse validateUser(String username, String password);

    List<UserResponse> getAllUsers();

    UserResponse getUserByUsername(String username);

    void createUser(UserRequest user);

    void updateUser(UserRequest user);

    void deleteUser(String username);

    void activateUser(String username);

    void inactivateUser(String username);
}
