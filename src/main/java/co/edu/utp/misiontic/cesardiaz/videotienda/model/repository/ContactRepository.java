package co.edu.utp.misiontic.cesardiaz.videotienda.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
