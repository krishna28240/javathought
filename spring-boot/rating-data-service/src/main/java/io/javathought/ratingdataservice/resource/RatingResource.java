package io.javathought.ratingdataservice.resource;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javathought.ratingdataservice.model.Rating;
import io.javathought.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
	
	@RequestMapping("/{movieID}")	
	public Rating getRatingsData(@PathVariable("movieID") String movieID) {
		return new Rating(movieID,4);
	}
	
	@RequestMapping("/user/{userID}")	
	public UserRating getUserRatingsData(@PathVariable("userID") String userID) {
		List<Rating> ratingList=Arrays.asList(new Rating("100",2),
				new Rating("150",3));
		
		UserRating userRating=new UserRating();
		userRating.setUserRatingList(ratingList);
		return userRating;
	}
	
}
