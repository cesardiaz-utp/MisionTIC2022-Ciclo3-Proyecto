package co.edu.utp.misiontic.cesardiaz.videotienda.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByUsernameAndPasswordAndActiveIsTrue(String username, String password);

    Optional<User> findByEmail(String email);
}
