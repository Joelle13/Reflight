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

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    FlightListComponent,
    FlightListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    HttpClientModule
  ],
  providers: [
    FlightService,
    AirlineService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
