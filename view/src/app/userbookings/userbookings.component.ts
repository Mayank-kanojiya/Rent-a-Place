import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';
import { Booking } from '../model/booking.model';

@Component({
  selector: 'app-userbookings',
  templateUrl: './userbookings.component.html',
  styleUrls: ['./userbookings.component.css']
})
export class UserbookingsComponent implements OnInit {
  bookings: Booking[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    const userId = this.api.getUserId();
    this.api.getUserBookings(userId)
      .subscribe((res: Booking[]) => {
        this.bookings = res;
      });
      
  }
}
