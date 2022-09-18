package co.edu.utp.misiontic.cesardiaz.videotienda.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.CategoryDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.MovieDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.CategoryRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.MovieRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.service.CatalogService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CategoryRepository categoryRepository;
    private final MovieRepository movieRepository;

    // S.O.L.I.D
    // SRP
    // OCP
    // LSP
    // ISP
    // DIP

    @Override
    public List<CategoryDto> getCategories() {
        var categories = categoryRepository.findAll(Sort.by("name"));

        return categories.stream()
                .map(cat -> new CategoryDto(cat.getName(), cat.getId().intValue()))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<CategoryDto> getCategoryById(Integer id) {
        var category = categoryRepository.findById(id.longValue());

        if (category.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new CategoryDto(
                category.get().getName(),
                category.get().getId().intValue()));
    }

    @Override
    public List<MovieDto> getMoviesByCategoryId(Integer categoryId) {
        var movies = movieRepository.findAllByCategoryId(categoryId.longValue());

        var categoryMovies = movies.stream()
                .map(mov -> MovieDto.builder()
                        .id(mov.getCode())
                        .length(mov.getLength())
                        .name(mov.getName())
                        .description(mov.getDescription())
                        .imageUrl(mov.getImageUrl())
                        .price(mov.getValue())
                        .build())
                .collect(Collectors.toList());

        return categoryMovies;
    }

}
