package com.opthema.egitim.model;


import java.util.List;

public class Movie {
	
	
	private String id;
	private String movieName;
	private String movieImgSrc;
	private String movieDescription;
	private String movieSubtitle;
	private String movieOzetForAltyazi;
	private String movieYear;
	
	

	public String getMovieYear() {
		return movieYear;
	}

	public void setMovieYear(String movieYear) {
		this.movieYear = movieYear;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieImgSrc() {
		return movieImgSrc;
	}

	public void setMovieImgSrc(String movieImgSrc) {
		this.movieImgSrc = movieImgSrc;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMovieSubtitle() {
		return movieSubtitle;
	}

	public void setMovieSubtitle(String movieSubtitle) {
		this.movieSubtitle = movieSubtitle;
	}

	public String getMovieOzetForAltyazi() {
		return movieOzetForAltyazi;
	}

	public void setMovieOzetForAltyazi(String movieOzetForAltyazi) {
		this.movieOzetForAltyazi = movieOzetForAltyazi;
	}

	List<Director> directors;
	List<Writer> writers;
	List<Star> stars;
	List<Subtitle> subtitles;
	
	public List<Subtitle> getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(List<Subtitle> subtitles) {
		this.subtitles = subtitles;
	}

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	public List<Writer> getWriters() {
		return writers;
	}

	public void setWriters(List<Writer> writers) {
		this.writers = writers;
	}

	public List<Star> getStars() {
		return stars;
	}

	public void setStars(List<Star> stars) {
		this.stars = stars;
	} 

	public String getId() {
		return id;
	}


	public void setId(String id2) {
		// TODO Auto-generated method stub
		
	}

	

	
}
