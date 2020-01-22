package com.stackroute.moviecruiserserverapplication.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;
import com.stackroute.moviecruiserserverapplication.service.MovieServiceImpl;

public class MovieServiceImplTest {

	@Mock
	private transient MovieRepository movieRepo;

	@Mock
	private transient Movie movie;

	@InjectMocks
	private transient MovieServiceImpl movieServiceImpl;

	transient Optional<Movie> options;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"1","superman","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234");
		options = Optional.of(movie);
	}

	/**
	 * 
	 */
	@Test
	public void testMockCreation() {
		assertNotNull("movie service implementation failed use @injectMocks  on movierviceImpl", movieServiceImpl);
	}

	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertTrue("saving movie failed, the call", flag);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());

	}

	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertFalse("saving movie failed", flag);

		verify(movieRepo, times(1)).findById(movie.getId());

	}

	@Test
	public void testupdateMovie() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("movie not so good");
		final Movie movie1 = movieServiceImpl.updateMovie(movie);

		assertEquals("saving movie failed", "movie not so good", movie.getComments());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());

	}

	@Test
	public void testDeleteMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);

		final boolean flag = movieServiceImpl.deleteMovieById(1);

		assertTrue("deleting movie failed", flag);
		verify(movieRepo, times(1)).delete(movie);
		verify(movieRepo, times(1)).findById(movie.getId());

	}

	
	@Test
	public void getMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		final Movie movie1 = movieServiceImpl.getMovieById(1);
		assertEquals("Getting Movie by This id failed", movie1, movie);
		verify(movieRepo, times(1)).findById(movie.getId());

	}

	
	@Test
	public void testGetMyMovies() {
		List<Movie> movieList = new ArrayList<>(1);
		when(movieRepo.findByUserId("1234")).thenReturn(movieList);
		final List<Movie> movies1 = movieServiceImpl.getMyMovies("1234");
		assertEquals(movieList, movies1);
		verify(movieRepo, times(1)).findByUserId("1234");

	}

}
