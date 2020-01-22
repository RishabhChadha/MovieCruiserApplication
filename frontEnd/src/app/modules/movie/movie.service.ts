import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'
import { Movie } from './components/movie.model';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';
import { ObserveOnMessage } from 'rxjs/internal/operators/observeOn';



@Injectable({
  providedIn: 'root'
})
export class MovieService {

  tmdbEndpoint: string;
  imageprefix: string;
  apiKey: string;
  watchListEndPoint: string;
  springEndPoint: string;
  search: string;

  constructor(private http: HttpClient) {
    this.apiKey = "api_key=7df67ad3f0c9f62cb411a6d687ef25ac"
    this.tmdbEndpoint = "https://api.themoviedb.org/3/movie";
    this.imageprefix = "https://image.tmdb.org/t/p/w500";
    this.watchListEndPoint = 'http://localhost:3000/watchList';
    this.springEndPoint="http://localhost:8082/api/v1/movie";
    this.search="https://api.themoviedb.org/3/search/movie"


  }


  searchMovies(searchKey:string):Observable<Array<Movie>>{
    if(searchKey.length>0){
      const url=`${this.search}?${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
    return this.http.get(url).pipe(retry(3),map(this.pickMovieResults),
    map(this.transformPosterPath.bind(this)))
    }
  }
  saveWatchListMovies(movie){
    return this.http.post(this.springEndPoint,movie)
  }

 getMyWatchList(): Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.springEndPoint)
  }

  deleteFromWatchList(movie:Movie){
    const url = ` ${this.springEndPoint}/${movie.id}`;
    return this.http.delete(url,{responseType: 'text'});
  }

  updateComments(movie){
    const url=`${this.springEndPoint}/${movie.id}`;
    return this.http.put(url,movie);
  }

  getMovies(type: string, page: number = 1): Observable<Array<Movie>> {
    const endPoint = `${this.tmdbEndpoint}/${type}?${this.apiKey}&language=en-US&page=${page}`
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
  }





  transformPosterPath(movies): Array<Movie> {
    console.log(this.imageprefix);
    return movies.map(movie => {
      movie.poster_path = `${this.imageprefix}${movie.poster_path}`;

      return movie
    })
  }

  pickMovieResults(response) {
    return response['results'];
  }


  addMovieToWatchList(movie) {
    return this.http.post(this.watchListEndPoint,movie )
  }

  getWatchListedMovie(): Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(this.watchListEndPoint);
  }



}

