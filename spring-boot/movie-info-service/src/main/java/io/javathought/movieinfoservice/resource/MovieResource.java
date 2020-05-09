package io.javathought.movieinfoservice.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
//import io.javathought.moviecatalogservice.model.UserRating;
import io.javathought.movieinfoservice.model.Movie;
import io.javathought.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${api.key}")
	private String apiKey;
		
	@RequestMapping("/{movieID}")
	public ResponseEntity<Movie> getMovieInfo(@PathVariable("movieID") String movieID) {
		
		MovieSummary movieSummary=	webClientBuilder.build()
				.get()
				.uri("https://api.themoviedb.org/3/movie/"+movieID+"?api_key="+apiKey)
				.retrieve()
				.bodyToMono(MovieSummary.class)
				.block();
		
		 return ResponseEntity.ok(new Movie(movieID,movieSummary.getTitle(),movieSummary.getOverview()));
	}
}
