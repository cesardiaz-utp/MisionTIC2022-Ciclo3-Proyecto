package co.edu.utp.misiontic.cesardiaz.videotienda.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
