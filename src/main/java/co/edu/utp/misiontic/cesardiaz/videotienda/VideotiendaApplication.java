package co.edu.utp.misiontic.cesardiaz.videotienda;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Category;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Movie;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.CategoryRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@SpringBootApplication
public class VideotiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideotiendaApplication.class, args);
	}

	@Data
	@AllArgsConstructor
	public static class DataLoader implements CommandLineRunner {

		private final CategoryRepository categoryRepository;
		private final MovieRepository movieRepository;

		@Override
		public void run(String... args) throws Exception {
			loadData();

		}

		private void loadData() {
			var scifi = categoryRepository.save(new Category("Sci-fi"));
			var comedy = categoryRepository.save(new Category("Comedy"));

			var categories = Arrays.asList(
					new Category("Action"),
					new Category("Romance"));

			categoryRepository.saveAll(categories);

			var movies = Arrays.asList(
					// new Movie(code, name, description, length, imageUrl, category),
					new Movie(10001L, "The Matrix",
							"Cuando una bella desconocida lleva al hacker Neo a un inframundo prohibido, descubre la impactante verdad: la vida que conoce es un elaborado engaño de una ciberinteligencia malvada.",
							120,
							"https://cdn.pocket-lint.com/r/s/1200x630/assets/images/155659-tv-news-feature-what-is-the-best-order-to-watch-the-matrix-movies-image6-n4msmyjaxw.jpg",
							scifi),
					new Movie(10002L, "Dumb and Dumber", "Esta pelicula trata de ..", 90, null, comedy),
					new Movie(10003L, "Dumb and Dumber",
							"Cuando una bella desconocida lleva al hacker Neo a un inframundo prohibido, descubre la impactante verdad: la vida que conoce es un elaborado engaño de una ciberinteligencia malvada.",
							90, null, comedy),
					new Movie(10004L, "Dumb and Dumber", "Esta pelicula trata de ..", 90, null, comedy),
					new Movie(10005L, "Dumb and Dumber", "Esta pelicula trata de ..", 90, null, comedy),
					new Movie(10006L, "Dumb and Dumber", "Esta pelicula trata de ..", 90, null, comedy));

			movieRepository.saveAll(movies);
		}

	}

}
