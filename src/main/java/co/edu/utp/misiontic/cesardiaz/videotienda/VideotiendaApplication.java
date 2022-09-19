package co.edu.utp.misiontic.cesardiaz.videotienda;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Category;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.Movie;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.entity.User;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.CategoryRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.MovieRepository;
import co.edu.utp.misiontic.cesardiaz.videotienda.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@SpringBootApplication
public class VideotiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideotiendaApplication.class, args);
	}

	@Component
	@Data
	@AllArgsConstructor
	public static class DataLoader implements CommandLineRunner {

		private final CategoryRepository categoryRepository;
		private final MovieRepository movieRepository;
		private final UserRepository userRepository;

		@Override
		public void run(String... args) throws Exception {
			loadUsers();
			loadCategories();
			loadMovies1();
		}

		private void loadUsers() {
			userRepository.save(new User("admin", "admin123", "Administrator",
					"admin@cesarvideoshop.com", true, true));
		}

		private void loadCategories() {
			categoryRepository.saveAll(Arrays.asList(
					new Category("Sci-fi"),
					new Category("Comedy"),
					new Category("Action"),
					new Category("Drama")));

		}

		private void loadMovies1() {
			movieRepository.deleteAll();

			// Peliculas Sci-fi
			var category = categoryRepository.findByName("Sci-fi").get();
			movieRepository.saveAll(Arrays.asList(
					new Movie("tt0133093", "The Matrix",
							"When a beautiful stranger leads computer hacker Neo to a forbidding underworld,"
									+ " he discovers the shocking truth--the life he knows is the elaborate deception"
									+ " of an evil cyber-intelligence.",
							136, 40_000d,
							"https://cdn.pocket-lint.com/r/s/1200x630/assets/images/155659-tv-news-feature-what-is-the-best-order-to-watch-the-matrix-movies-image6-n4msmyjaxw.jpg",
							category),
					new Movie("tt10648342", "Thor: Love and Thunder",
							"Thor enlists the help of Valkyrie, Korg and ex-girlfriend Jane Foster to fight Gorr the"
									+ " God Butcher, who intends to make the gods extinct.",
							118, 80_000d,
							"https://lumiere-a.akamaihd.net/v1/images/eu_thor-4_v1_hero_m_e458970c.jpeg?region=0,71,750,422",
							category),
					new Movie("tt0499549", "Avatar",
							"A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn"
									+ " between following his orders and protecting the world he feels is his home.",
							162, 50_000d,
							"https://cloudfront-us-east-1.images.arcpublishing.com/gruponacion/4BFSX3ACOBFSVKGSQTENK4WFSI.jpg",
							category)));

			// Peliculas Comedy
			category = categoryRepository.findByName("Comedy").get();
			movieRepository.saveAll(Arrays.asList(
					new Movie("tt1758830", "This Is 40",
							"Pete and Debbie are both about to turn 40, their kids hate each other, both of their"
									+ " businesses are failing, they're on the verge of losing their house, and their"
									+ " relationship is threatening to fall apart.",
							154, 40_000d,
							"https://losinterrogantes.com/wp-content/uploads/2013/02/si-fuera-facil-pelicula-estreno-comedia.jpg",
							category),
					new Movie("tt5113044", "Minions: The Rise of Gru",
							"The untold story of one twelve-year-old's dream to become the world's greatest"
									+ " supervillain.",
							87, 80_000d,
							"https://www.ateleus.com/wp-content/uploads//2022/07/z-minions-gru.jpg",
							category)));

			// Peliculas Action
			category = categoryRepository.findByName("Action").get();
			movieRepository.saveAll(Arrays.asList(
					new Movie("tt4633694", "Spider-Man: Into the Spider-Verse",
							"Teen Miles Morales becomes the Spider-Man of his universe, and must join with five"
									+ " spider-powered individuals from other dimensions to stop a threat for all"
									+ " realities.",
							117, 50_000d,
							"https://elfarandi.com/wp-content/uploads/2018/12/Spider-Man.jpg",
							category),
					new Movie("tt1745960", "Top Gun: Maverick",
							"After thirty years, Maverick is still pushing the envelope as a top naval aviator, but"
									+ " must confront ghosts of his past when he leads TOP GUN's elite graduates on a"
									+ " mission that demands the ultimate sacrifice from those chosen to fly it.",
							130, 80_000d,
							"https://as01.epimg.net/meristation/imagenes/2022/05/23/reportajes/1653297680_702523_1653300885_noticia_normal.jpg",
							category),
					new Movie("tt1270797", "Venom",
							"A failed reporter is bonded to an alien entity, one of many symbiotes who have invaded"
									+ " Earth. But the being takes a liking to Earth and decides to protect it.",
							112, 40_000d,
							"https://depor.com/resizer/Sf1filNnEYj0mTGU4VaAC6eoMHI=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/5APGD5X6ZNAPVBQNKZYCJ4KXKE.jpg",
							category),
					new Movie("tt1877830", "The Batman",
							"When a sadistic serial killer begins murdering key political figures in Gotham, Batman"
									+ " is forced to investigate the city's hidden corruption and question his"
									+ " family's involvement.",
							176, 80_000d,
							"https://i.blogs.es/0c9485/the-batman-cartel/1366_2000.jpeg",
							category)));

			// Peliculas Drama
			category = categoryRepository.findByName("Drama").get();
			movieRepository.saveAll(Arrays.asList(
					new Movie("tt9411972", "Where the Crawdads Sing",
							"A woman who raised herself in the marshes of the deep South becomes a suspect in the"
									+ " murder of a man she was once involved with.",
							125, 80_000d,
							"https://i.ytimg.com/vi/r-Z81ioszp0/maxresdefault.jpg",
							category)));

		}
	}

}
