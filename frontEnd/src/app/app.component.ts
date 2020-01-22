import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
  
  
 /*  `
  <mat-toolbar color="primary">
    <span>Movie Cruiser</span>
    <button mat-button [routerLink]=['./movie/popular']>Popular Movies</button>
    <button mat-button [routerLink]=['./movie/top_rated']>Top Rated Movies</button>
  </mat-toolbar>
  <router-outlet></router-outlet>
  ` */,
  styles: []
})
export class AppComponent {


  title = 'movie-cruiser-capsule-frontend';
}
