package com.stackroute.moviecruiserserverapplication.controllers;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapplication.controller.MovieController;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieService;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

                @Autowired
                private transient MockMvc mock;

                @MockBean
                private transient MovieService movieServices;

                @InjectMocks
                private MovieController movieController;

                private transient Movie movie;

                static List<Movie> movies;

                @Before
                public void beforeStart() {
        MockitoAnnotations.initMocks(this);
                                movies = new ArrayList<Movie>();
                                mock = MockMvcBuilders.standaloneSetup(movieController).build();
                            	movie = new Movie(1, "1", "superman", "good movie", "wwww.abc.com", "2015-03-23", 45.0, 112, "1234");
                                movies.add(movie);
                                movie = new Movie(2, "1", "superman", "good movie", "wwww.abc.com", "2015-03-23", 45.0, 112, "1234");
                                movies.add(movie);
                }

                @Test
                public void testSaveNewMovieSuccess() throws Exception {
                                String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0IiwiaWF0IjoxNTUzMzM3MzU2fQ.zK0LSbjbcj2TtB742mkYCu94BqSbsaiwfXXHEB4VBbY";
                                when(movieServices.saveMovie(movie)).thenReturn(true);
                                mock.perform(post("/api/v1/movie").contentType(MediaType.APPLICATION_JSON)
                                                                .header("authorization", "Bearer " + token).content(jsonToString(movie)))
                                                                .andExpect(status().isCreated());
                                verify(movieServices, times(1)).saveMovie(Mockito.any(Movie.class));
                                verifyNoMoreInteractions(movieServices);

                }

                @Test
                public void testUpdateMovieSuccess() throws Exception {
                                movie.setComments("not so good movie");
                                when(movieServices.updateMovie(movie)).thenReturn(movies.get(0));
                                mock.perform(put("/api/v1/movie/{id}", movie.getId()).contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonToString(movie))).andExpect(status().isOk());
                                verify(movieServices, times(1)).updateMovie(Mockito.any(Movie.class));
                                verifyNoMoreInteractions(movieServices);
                }

                @Test
                public void testDeleteMovieById() throws Exception {
                                when(movieServices.deleteMovieById(1)).thenReturn(true);
                                mock.perform(delete("/api/v1/movie/{id}", 1)).andExpect(status().isOk());
                                verify(movieServices, times(1)).deleteMovieById(1);
                                verifyNoMoreInteractions(movieServices);
                }

                @Test
                public void testGetMovieById() throws Exception {
                                when(movieServices.getMovieById(1)).thenReturn(movies.get(0));
                                mock.perform(get("/api/v1/movie/{id}", 1)).andExpect(status().isOk());
                                verify(movieServices, times(1)).getMovieById(1);
                                verifyNoMoreInteractions(movieServices);
                }

                @Test
                public void testGetMyMovies() throws Exception {
                                String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0IiwiaWF0IjoxNTUzMzM3MzU2fQ.zK0LSbjbcj2TtB742mkYCu94BqSbsaiwfXXHEB4VBbY";
                                when(movieServices.getMyMovies("1234")).thenReturn(null);
                mock.perform(get("/api/v1/movie").contentType(MediaType.APPLICATION_JSON).header("authorization",
                                                                "Bearer " + token)).andExpect(status().isOk());
                                verify(movieServices, times(1)).getMyMovies("1234");
                                verifyNoMoreInteractions(movieServices);
                }

                private String jsonToString(final Object object) {
                                String result;
                                try {
                                                final ObjectMapper mapper = new ObjectMapper();
                                                result = mapper.writeValueAsString(object);
                                } catch (JsonProcessingException e) {
                                                result = "Json processing error";
                                }
                                return result;
                }

}
