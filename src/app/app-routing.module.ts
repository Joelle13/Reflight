import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FlightListComponent} from "./components/flight-list/flight-list.component";
import {ReviewListComponent} from "./components/review-list/review-list.component";
import {AddFlightFormComponent} from "./components/add-flight-form/add-flight-form.component";

const routes: Routes = [
  {path:'', component: FlightListComponent},
  {path:'review-list', component: ReviewListComponent},
  {path: 'add-flight', component: AddFlightFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
