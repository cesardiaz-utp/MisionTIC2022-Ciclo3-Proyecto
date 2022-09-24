package co.edu.utp.misiontic.cesardiaz.videotienda.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findAllByCategoryId(Long categoryId);

    List<Movie> findAllByCategoryIdIn(List<Long> categoryIds);

    List<Movie> findAllByDescriptionLikeOrderByNameAsc(String description);

    // JPQL -> Selects a objetos JPA
    // @Query("SELECT m FROM Movie m WHERE m.category.id = :categoryId"
    // + " AND (m.username like :name OR m.name like :name) ")

    // Nativo -> SQL
    // @Query(value = "SELECT m.* FROM movie m WHERE m.category_id = :categoryId"
    //         + " AND (m.username like :name OR m.name like :name)", nativeQuery = true)
    // List<Movie> findParticular(Long categoryId, String name);

}
