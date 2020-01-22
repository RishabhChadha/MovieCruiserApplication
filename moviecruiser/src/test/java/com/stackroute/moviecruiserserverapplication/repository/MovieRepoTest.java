package com.stackroute.moviecruiserserverapplication.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;


@Transactional
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MovieRepoTest {

                
                @Autowired
                private transient MovieRepository repo;

                
                public void setrepo(MovieRepository repo) 
                {
                                this.repo = repo;
                }

                @After
                public void tearDown(){
                	repo.deleteAllInBatch();
                }
                @Test
                public void testSaveMovie() throws Exception
                {
                                repo.save(new Movie(1,"1","superman","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                Movie movie = repo.getOne(1);
                                assertEquals(movie.getId(),1);
                                
                                
                }
                
                @Test
                public void testUpdateMovie() throws Exception
                {
                	Movie movie1=new Movie(1,"1","superman","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234");
                                repo.save(movie1);
                                final Movie movie = repo.getOne(1);
                                assertEquals(movie.getTitle(),"superman");
                                movie.setComments("hi");
                                repo.save(movie);
                                final Movie tempMovie = repo.getOne(1);
                                assertEquals("hi",tempMovie.getComments());
                
                }              
                
                @Test
                public void testDeleteMovie() throws Exception
                {
                                repo.save(new Movie(1,"1","superman","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                final Movie movie = repo.getOne(1);
                                assertEquals(movie.getTitle(),"superman");
                                repo.delete(movie);
                                assertEquals(Optional.empty(),repo.findById(1));
                                
                }
                
                @Test
                public void testGetMovie() throws Exception
                {
                                repo.save(new Movie(1,"1","superman","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                final Movie movie = repo.getOne(1);
                                assertEquals(movie.getTitle(),"superman");
                }
                
                @Test
                public void testGetMyMovies() throws Exception
                {
                                repo.save(new Movie(1,"1","superman","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                repo.save(new Movie(2,"2","superman12","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                repo.save(new Movie(3,"3","superman23","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                repo.save(new Movie(4,"4","superman45","good movie","wwww.abc.com","2015-03-23",45.0,112,"1234"));
                                final List<Movie> movies = repo.findAll();
                                assertEquals(movies.get(0).getTitle(),"superman");
                }
                
                
                
}

