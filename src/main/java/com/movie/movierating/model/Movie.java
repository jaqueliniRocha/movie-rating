package com.movie.movierating.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String year;
	private int duration;
	private double score;
	
	private Director[] directors;	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Director[] getDirector() {
		return directors;
	}
	public void setDirector(Director[] directors) {
		this.directors = directors;
	}	
	
}
