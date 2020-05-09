package io.javathought.moviecatalogservice.model;

import java.util.List;

public class UserRating {
	private List<Rating> userRatingList;

	public UserRating() {
		
	}

	public List<Rating> getUserRatingList() {
		return userRatingList;
	}

	public void setUserRatingList(List<Rating> userRatingList) {
		this.userRatingList = userRatingList;
	}

}
