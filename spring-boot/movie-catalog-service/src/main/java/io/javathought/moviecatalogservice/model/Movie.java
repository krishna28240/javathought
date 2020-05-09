package io.javathought.moviecatalogservice.model;

public class Movie {
	private String movieID;
	private String title;
	private String overview;
	public Movie() {
		
	}
	
	public Movie(String movieID, String title, String overview) {
		this.movieID = movieID;
		this.title = title;
		this.overview = overview;
	}

	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	
}
