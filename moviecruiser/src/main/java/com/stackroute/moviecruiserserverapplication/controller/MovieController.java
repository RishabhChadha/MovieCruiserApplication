package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

import io.jsonwebtoken.Jwts;



@CrossOrigin
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private MovieService movieService;
	

	public MovieController(final MovieService movieService) {
		this.movieService=movieService;
		
	}
	
    @PostMapping("")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie, HttpServletRequest request, HttpServletResponse response) {
    	ResponseEntity<?> responseEntity;
		
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("securitykey").parseClaimsJws(token).getBody().getSubject();
		try{
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
		}
		catch (MovieAlreadyExistsException e) {
			// TODO: handle exception
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" +e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final int id,@RequestBody final Movie movie){
		ResponseEntity<?> responseEntity;
		try{
			final Movie fetchedMovie = movieService.updateMovie(movie);
			
			responseEntity=new ResponseEntity<Movie>(fetchedMovie,HttpStatus.OK);
			
		}catch(MovieNotFoundException e){
			
			responseEntity=new ResponseEntity<String>("message: "+e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final Integer id){
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<String>("movie deleted successfully",HttpStatus.OK);
		return responseEntity;
	} 
	
	

	
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> fetchedMovieById(@PathVariable("id") final int id){
		ResponseEntity<?> responseEntity;
		try{
			final Movie fetchedMovie = movieService.getMovieById(id);
			
			responseEntity=new ResponseEntity<Movie>(fetchedMovie,HttpStatus.OK);
			
		}catch(MovieNotFoundException e){
			
			responseEntity=new ResponseEntity<String>("message: "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	
    @GetMapping("")
    public ResponseEntity<List<Movie>> getAllMovies(final HttpServletRequest req, HttpServletResponse response) {
          final HttpServletRequest request=(HttpServletRequest) req;

		final String authHeader= request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId= Jwts.parser().setSigningKey("securitykey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId::"+userId);
		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId),HttpStatus.OK);
	}
	
	

}
