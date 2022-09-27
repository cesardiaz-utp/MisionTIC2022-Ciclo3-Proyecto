package co.edu.utp.misiontic.cesardiaz.videotienda.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.CategoryDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.ContactDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.FilterDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.MovieDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Contact;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.CategoryRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.ContactRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.MovieRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.service.CatalogService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CategoryRepository categoryRepository;
    private final MovieRepository movieRepository;
    private final ContactRepository contactRepository;

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

    @Override
    public void saveContact(ContactDto contact) {

        var entity = new Contact();
        entity.setDate(new Date());
        entity.setCountry(contact.getCountry());
        entity.setName(contact.getName());
        entity.setEmail(contact.getEmail());
        entity.setSubject(contact.getSubject());
        entity.setMessage(contact.getMessage());

        contactRepository.save(entity);

    }

    @Override
    public MovieDto getMovieById(String id) {
        var movieOp = movieRepository.findById(id);

        var movie = movieOp.get();
        return MovieDto.builder()
                .id(movie.getCode())
                .length(movie.getLength())
                .name(movie.getName())
                .description(movie.getDescription())
                .imageUrl(movie.getImageUrl())
                .price(movie.getValue())
                .build();
    }

    @Override
    public List<MovieDto> getMoviesByFilter(FilterDto filter) {
        var types = filter.getType() != null;
        var schools = filter.getSchool() != null;

        if(types && schools) {
            movieRepository.findAllByTypeIdInAndSchoolIdIn(filter.getType(), filter.getSchool());
            // SELECT * FROM movie WHERE type in ('d') and school in ('1', '2');
        }

        return null;
    }

}
