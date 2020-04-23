package io.javathought.moviecatalogservice.resource;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.discovery.DiscoveryClient;

import io.javathought.moviecatalogservice.model.Movie;
import io.javathought.moviecatalogservice.model.Rating;
import io.javathought.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userID}")
	public ResponseEntity<List<CatalogItems>> getMovieCatalogItems(@PathVariable("userID") String userID){
		
		//UserRating ratingList=restTemplate.getForObject("http://localhost:8083/ratingsdata/user/"+userID, UserRating.class);
		//OR
		UserRating ratingList=webClientBuilder.build()
				.get()
				.uri("http://ratings-data-service/ratingsdata/user/"+userID)
				.retrieve()
				.bodyToMono(UserRating.class)
				.block();
		 List<CatalogItems> ratingListResult=ratingList.getUserRatingList().stream().map(rating->{
			//Movie movie=restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieID(), Movie.class);
			//Using webClient to call micro serivce asynchronously 
			Movie movie=webClientBuilder.build()
			.get()
			.uri("http://movie-info-service/movies/"+rating.getMovieID())
			.retrieve()
			.bodyToMono(Movie.class)
			.block();
			System.out.println(rating.getMovieID());
			return new CatalogItems(movie.getTitle(),movie.getOverview(),rating.getRating());
		}).collect(Collectors.toList());
		 return ResponseEntity.ok(ratingListResult);
	}
}
