package co.edu.utp.misiontic.cesardiaz.videotienda.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.CategoryDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;

// Spring MVC

@Controller
@Slf4j
public class VideoShopController {

    private final List<CategoryDto> categories = Arrays.asList(
            new CategoryDto("Action", 1),
            new CategoryDto("Comedy", 2),
            new CategoryDto("Romance", 3),
            new CategoryDto("Sci-fi", 4));

    private final List<MovieDto> movies = Arrays.asList(
            new MovieDto(1, "The Matrix", 4,
                    "Cuando una bella desconocida lleva al hacker Neo a un inframundo prohibido, descubre la impactante verdad: la vida que conoce es un elaborado engaño de una ciberinteligencia malvada.",
                    120, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null),
            new MovieDto(2, "Dumb and Dumber", 2,
                    "Cuando una bella desconocida lleva al hacker Neo a un inframundo prohibido, descubre la impactante verdad: la vida que conoce es un elaborado engaño de una ciberinteligencia malvada.",
                    90, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null),
            new MovieDto(2, "Dumb and Dumber", 2, "Esta pelicula trata de ..", 90, null));

    @GetMapping("/catalog")
    public String goToCatalog(Model model) {
        model.addAttribute("title", "Welcome to my site");

        model.addAttribute("categories", categories);

        return "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String loadCatalogById(@PathVariable("id") Integer id, Model model) {
        log.info("Cargando informacion de categoria {}", id);

        var category = categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("La categoria no existe"));

        model.addAttribute("title", category.getName());
        model.addAttribute("id", category.getId());
        model.addAttribute("categories", categories);

        var categoryMovies = movies.stream()
                .filter(m -> m.getCategoryId().equals(id))
                .collect(Collectors.toList());

        model.addAttribute("movies", categoryMovies);

        return "catalog";
    }

    @GetMapping("/contact")
    public String goToContact(Model model) {
        return "contact";
    }

    @GetMapping("/login")
    public String goToLogin(Model model) {
        return "login";
    }
}
