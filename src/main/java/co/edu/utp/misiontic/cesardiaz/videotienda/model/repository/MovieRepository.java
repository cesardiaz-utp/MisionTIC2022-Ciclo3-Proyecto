package co.edu.utp.misiontic.cesardiaz.videotienda.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    List<Movie> findAllByCategoryId(Long categoryId);
}
