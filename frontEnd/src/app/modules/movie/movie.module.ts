import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';

import { ContainerComponent } from './components/container/container.component';
import { RouterModule, Routes } from '@angular/router';
import { MovieRouterModule } from './movie.router';
import { MovieService } from './movie.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule, MatInputModule } from '@angular/material';
import { SearchComponent } from './components/search/search.component';




@NgModule({
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    WatchListComponent,
    TmdbContainerComponent,
    MovieDialogComponent,
    SearchComponent],

  entryComponents: [MovieDialogComponent],


  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    FormsModule,
    MatDialogModule,
    MatInputModule

  ],
  exports: [ThumbnailComponent,
    MovieRouterModule, ContainerComponent, TmdbContainerComponent, WatchListComponent,
    MovieDialogComponent,
    SearchComponent],

  providers: [MovieService]
})
export class MovieModule { }
