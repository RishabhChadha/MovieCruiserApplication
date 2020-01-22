package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	
	public final transient MovieRepository movieRepo;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepo){
		super();
		this.movieRepo=movieRepo;
	}
	
	@Override
	public boolean saveMovie(final Movie movie) throws MovieAlreadyExistsException {
		// other method
		final Optional<Movie> object=movieRepo.findById(movie.getId());
		if(object.isPresent()){
			throw new MovieAlreadyExistsException("could not save movie");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(final Movie updateMovie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie =movieRepo.findById(updateMovie.getId()).orElse(null);
		
		if(movie==null){
			throw new MovieNotFoundException("Couldn't update movie . Movie not found");
		}
		movie.setComments(updateMovie.getComments());
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie = movieRepo.findById(id).orElse(null);
		
		if(movie== null){
			throw new MovieNotFoundException("movie cannot be deleted . Movie not found");
		}
		movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie =  movieRepo.findById(id).orElse(null);
		if(movie == null){
			throw new MovieNotFoundException("Movie could not be found");
		}
		
		return movie;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		// TODO Auto-generated method stub
		
		return movieRepo.findByUserId(userId);
	}

}
