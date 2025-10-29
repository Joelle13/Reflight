import {Component, Input} from '@angular/core';
import {Flight} from "../../data/flight";

@Component({
  selector: 'app-flight-list-item',
  templateUrl: './flight-list-item.component.html',
  styleUrl: './flight-list-item.component.css'
})
export class FlightListItemComponent {
  @Input()
  flight!: Flight;

}
