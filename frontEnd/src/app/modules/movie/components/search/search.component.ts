import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie.model';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  movies: Array<Movie>
  constructor(private movieService: MovieService) { }

  ngOnInit() {
  }

  onEnter(searchKey)
  {
    console.log('search-key',searchKey);
    this.movieService.searchMovies(searchKey).subscribe(movies =>{
      this.movies=movies;
    })
  }

}
