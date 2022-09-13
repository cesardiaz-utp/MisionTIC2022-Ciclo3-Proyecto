package co.edu.utp.misiontic.cesardiaz.videotienda.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.CategoryDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.MovieDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {

    // S.O.L.I.D
    // SRP
    // OCP
    // LSP
    // ISP
    // DIP

    private final List<CategoryDto> categories = Arrays.asList(
            new CategoryDto("Action", 1),
            new CategoryDto("Comedy", 2),
            new CategoryDto("Romance", 3),
            new CategoryDto("Sci-fi", 4));

    private final List<MovieDto> movies = Arrays.asList(
            new MovieDto(1, "The Matrix", 4,
                    "Cuando una bella desconocida lleva al hacker Neo a un inframundo prohibido, descubre la impactante verdad: la vida que conoce es un elaborado engaño de una ciberinteligencia malvada.",
                    120,
                    "https://cdn.pocket-lint.com/r/s/1200x630/assets/images/155659-tv-news-feature-what-is-the-best-order-to-watch-the-matrix-movies-image6-n4msmyjaxw.jpg"),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null),
            new MovieDto(2, "Dumb and Dumber", 2,
                    "Cuando una bella desconocida lleva al hacker Neo a un inframundo prohibido, descubre la impactante verdad: la vida que conoce es un elaborado engaño de una ciberinteligencia malvada.",
                    90, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null));

    @Override
    public List<CategoryDto> getCategories() {
        return categories;
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Integer id) {
        var category = categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        return category;
    }

    @Override
    public List<MovieDto> getMoviesByCategoryId(Integer categoryId) {
        var categoryMovies = movies.stream()
                .filter(m -> m.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());

        return categoryMovies;
    }

}
