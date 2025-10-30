import { Component } from '@angular/core';
import {Airline} from "../../data/airline";
import {FlightCreateInput} from "../../data/flight";
import {FormBuilder, Validators} from "@angular/forms";
import {AirlineService} from "../../services/airlineService";
import {FlightService} from "../../services/flightService";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {dateNotAfterTodayValidator} from "../../validators/custom-validators";

@Component({
  selector: 'app-add-flight-form',
  templateUrl: './add-flight-form.component.html',
  styleUrl: './add-flight-form.component.css'
})
export class AddFlightFormComponent {
  airlines: Airline[] = [];
  minDate: string = new Date().toISOString().split('T')[0];
  flightCreateInput! : FlightCreateInput;

  constructor(private formBuilder: FormBuilder, private airlineService: AirlineService, private flightService: FlightService, private router: Router) {}

  ngOnInit(): void {
    this.airlineService.getAll().subscribe(airlines => {
      this.airlines = airlines;
    })
  }

  addFlight= this.formBuilder.group({
    flightNumber: ['', {
      validators: [Validators.required, Validators.minLength(3), Validators.maxLength(10)],
    }],
    airline: ['', Validators.required],
    departureAirport: ['', {
      validators: [Validators.required, Validators.minLength(3), Validators.maxLength(100)],
    }],
    arrivalAirport: ['', {
      validators: [Validators.required, Validators.minLength(3), Validators.maxLength(100)],
    }],
    departureDate: ['', {
      validators: [Validators.required, dateNotAfterTodayValidator()],
    }],
    arrivalDate: ['', {
      validators: [Validators.required, dateNotAfterTodayValidator()],
    }],
    departureTime: ['', Validators.required],
    arrivalTime: ['', Validators.required]
  })

  deleteFlight = this.formBuilder.group({
    flightNumber: ['',
      {
        validators: [Validators.required, Validators.minLength(3), Validators.maxLength(6)],
      }]
  });

  get flightNumberDelete() {
    return this.deleteFlight.controls['flightNumber'];
  }

  get flightNumber() {
    return this.addFlight.controls['flightNumber'];
  }
  get airline() {
    return this.addFlight.controls['airline'];
  }
  get departureAirport() {
    return this.addFlight.controls['departureAirport'];
  }
  get arrivalAirport() {
    return this.addFlight.controls['arrivalAirport'];
  }
  get departureDate() {
    return this.addFlight.controls['departureDate'];
  }
  get arrivalDate() {
    return this.addFlight.controls['arrivalDate'];
  }
  get departureTime() {
    return this.addFlight.controls['departureTime'];
  }
  get arrivalTime() {
    return this.addFlight.controls['arrivalTime'];
  }


  onSubmit() {
    console.log(this.addFlight.value.airline);
    if (this.addFlight.valid) {
      this.flightCreateInput = {
        id: this.addFlight.value.flightNumber ?? '',
        airlineId: this.addFlight.value.airline ?? '',
        departureAirport: this.addFlight.value.departureAirport ?? '',
        arrivalAirport: this.addFlight.value.arrivalAirport ?? '',
        departureDate: new Date(this.addFlight.value.departureDate ?? ''),
        arrivalDate: new Date (this.addFlight.value.arrivalDate ?? ''),
        departureTime: this.addFlight.value.departureTime ?? '',
        arrivalTime: this.addFlight.value.arrivalTime ?? ''
      };
      this.flightService
        .create(this.flightCreateInput)
        .subscribe(() => {
          this.router.navigate(['/'])
          this.displayToast(true, "Vol créé avec succès !");
        });
    } else {
      this.displayToast(false, "Veuillez vérifier votre formulaire de création de vol");
    }
  }

  displayToast(valid : boolean, message : string){
    const Toast = Swal.mixin({
      toast: true,
      position: "top",
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.onmouseenter = Swal.stopTimer;
        toast.onmouseleave = Swal.resumeTimer;
      }
    });
    if(valid){
      Toast.fire({
        icon: 'success',
        title: message
      });
    } else{
      Toast.fire({
        icon: 'error',
        title: message
      })
    }
  }

  onSubmitDelete() {
    if (this.deleteFlight.valid) {
      this.flightService.delete(this.deleteFlight.value.flightNumber ?? '')
        .subscribe( {
          next: () => {
            this.router.navigate(['/'])
            this.displayToast(true, "Vol supprimé avec succès !");
          },
          error: (err) => {
            console.error('Erreur lors de la suppression', err);
            this.displayToast(false, "Aucun vol trouvé avec ce numéro !");
          }
        })
    }
  }

}
