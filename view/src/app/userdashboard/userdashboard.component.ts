import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faCalendar } from '@fortawesome/free-solid-svg-icons';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { Property } from '../model/property.model';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.css']
})
export class UserdashboardComponent implements OnInit {
  faCalender = faCalendar;
  checkin!: NgbDate;
  checkout!: NgbDate;
  searchText: string = "";
  propertys: Property[] = [];
  a: number = 0;

  private baseUrl = 'http://localhost:9000';

  constructor(
    private api: ApiService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getProperty();
  }

  // Converts backend byte array images to base64 for display
  convertImage(img: any): string {
    return "data:image/jpeg;base64," + img;
  }

  getProperty(): void {
    this.http.get<any>(`${this.baseUrl}/users/statusProperty`)
      .subscribe((res) => {
        this.propertys = [];
        for (let item of res) {
          for (let k = 0; k <= 3; k++) {
            const key = k === 0 ? 'returnedImage' : `returnedImage${k}`;
            if (item[key]) {
              item[key] = this.convertImage(item[key]);
            }
          }
          this.propertys.push(item);
        }
        if (this.propertys.length > 0) {
          this.a = this.propertys[0].pid;
        }
      });
  }

  search(): void {
    if (!this.checkin || !this.checkout) {
      alert('Please select both check-in and check-out dates');
      return;
    }
    const checkInDate = `${this.checkin.year}-${this.formatDate(this.checkin.month)}-${this.formatDate(this.checkin.day)}`;
    const checkOutDate = `${this.checkout.year}-${this.formatDate(this.checkout.month)}-${this.formatDate(this.checkout.day)}`;
    this.http.get<any>(`${this.baseUrl}/users/search/${checkInDate}/${checkOutDate}`)
      .subscribe((res) => {
        this.propertys = [];
        for (let item of res) {
          for (let k = 0; k <= 3; k++) {
            const key = k === 0 ? 'returnedImage' : `returnedImage${k}`;
            if (item[key]) {
              item[key] = this.convertImage(item[key]);
            }
          }
          this.propertys.push(item);
        }
        if (this.propertys.length > 0) {
          this.a = this.propertys[0].pid;
        }
      });
  }

  // Helper to format month/day with leading zero for URLs
  private formatDate(num: number): string {
    return num < 10 ? '0' + num : num.toString();
  }

  logout(): void {
    this.api.logout();
    this.router.navigate(['/login']);
  }
}
