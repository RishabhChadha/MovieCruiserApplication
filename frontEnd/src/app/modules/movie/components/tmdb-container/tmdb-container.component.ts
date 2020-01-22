import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import { Movie } from '../movie.model';

@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {

  movies: Array<Movie>;

  movieType;

  constructor(private movieService: MovieService, private route: ActivatedRoute) {
    this.route.data.subscribe((data) => this.movieType=data.movieType);
    this.movies = [];
   }
  ngOnInit(){
    
    this.movieService.getMovies(this.movieType).subscribe((movies) => {
      this.movies.push(...movies);
    });
  }

}
