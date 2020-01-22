import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../movie.model';

@Component({
  selector: 'movie-watch-list',
  templateUrl: './watch-list.component.html',
  styleUrls: ['./watch-list.component.css']
})
export class WatchListComponent implements OnInit {

  usewatchListApi = true;
  movies: Array<Movie>;

  constructor(private movieService: MovieService) { 
this.movies= [];

  }

  ngOnInit() {
  
    this.movieService.getMyWatchList().subscribe((movies) =>{
      
      this.movies.push(...movies)
    });
  }

}
