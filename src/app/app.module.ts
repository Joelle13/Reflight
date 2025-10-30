import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import {HttpClientModule} from "@angular/common/http";
import {FlightService} from "./services/flightService";
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { FlightListItemComponent } from './components/flight-list-item/flight-list-item.component';
import {AirlineService} from "./services/airlineService";
import { ReviewListComponent } from './components/review-list/review-list.component';
import {UserService} from "./services/userService";
import { ReviewListItemComponent } from './components/review-list-item/review-list-item.component';
import {ReviewService} from "./services/reviewService";
import { AddFlightFormComponent } from './components/add-flight-form/add-flight-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { FlightSearchComponent } from './components/flight-search/flight-search.component';
import { ReviewSearchComponent } from './components/review-search/review-search.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    FlightListComponent,
    FlightListItemComponent,
    ReviewListComponent,
    ReviewListItemComponent,
    AddFlightFormComponent,
    FlightSearchComponent,
    ReviewSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    FlightService,
    AirlineService,
    UserService,
    ReviewService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
