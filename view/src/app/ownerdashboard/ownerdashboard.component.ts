import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';
import { Validators, FormGroup, FormBuilder } from "@angular/forms";
import { Booking } from '../model/booking.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ownerdashboard',
  templateUrl: './ownerdashboard.component.html',
  styleUrls: ['./ownerdashboard.component.css']
})
export class OwnerdashboardComponent implements OnInit {
  public propertyList: any[] = [];
  public priceUpdate: FormGroup;
  public bookings: Booking[] = [];
  newPrice: string = "";
  private baseUrl = 'http://localhost:9000';

  constructor(
    private api: ApiService,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.priceUpdate = this.formBuilder.group({
      price: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const owner_id: number = this.api.getUserId();
this.http.get<any[]>(`${this.baseUrl}/owners/myProperties/${owner_id}`)
  .subscribe((res: any[]) => {
    console.log('Properties response:', res);
    this.propertyList = res;
  });


    // Fetch ALL bookings for all properties owned by this owner
    this.http.get<Booking[]>(`${this.baseUrl}/owners/bookingRequests/${owner_id}`)
      .subscribe((bookings: Booking[]) => {
        this.bookings = bookings;
        console.log(this.bookings);
      });
  }

  deleteProperty(pid: number) {
    return this.http.delete<any>(`${this.baseUrl}/owners/delete/${pid}`).subscribe(
      res => {
        alert("Deleted successfully");
        this.ngOnInit(); // refresh data
      },
      err => {
        alert("Failed to delete property");
      }
    );
  }

  updatePrice(pid: number) {
    if (!this.priceUpdate.valid) {
      alert("Please enter a valid price.");
      return;
    }
    this.newPrice = this.priceUpdate.get('price')?.value;
    return this.http.put<any>(`${this.baseUrl}/owners/updatePrice/${pid}/${this.newPrice}`, null).subscribe(
      res => {
        alert("Price Updated successfully");
        this.ngOnInit(); // refresh data
        this.priceUpdate.reset();
      },
      err => {
        alert("Failed to update price");
      }
    );
  }

  approveBooking(bid: number) {
    return this.http.post<any>(`${this.baseUrl}/owners/approve/${bid}`, null).subscribe(
      res => {
        alert("Booking approved");
        this.ngOnInit(); // refresh data
      },
      err => {
        alert("Failed to approve booking");
      }
    );
  }

  disapproveBooking(bid: number) {
    return this.http.post<any>(`${this.baseUrl}/owners/disapprove/${bid}`, null).subscribe(
      res => {
        alert("Booking Disapproved");
        this.ngOnInit(); // refresh data
      },
      err => {
        alert("Failed to disapprove booking");
      }
    );
  }

  viewMessages(pid: number) {
  this.api.getPropertyMessage(pid).subscribe((messages) => {
    this.api.setPropertyMessage(messages);
    this.router.navigate(['/messages']);
  });

  }
}
