package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;

public interface MovieService {

	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	
	Movie getMovieById(int Id) throws MovieNotFoundException;
	
	List<Movie> getMyMovies(String userId) ;
}
