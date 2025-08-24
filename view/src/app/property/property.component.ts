import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { faCalendar } from '@fortawesome/free-solid-svg-icons';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-property',
  templateUrl: './property.component.html',
  styleUrls: ['./property.component.css']
})
export class PropertyComponent implements OnInit {
  pid!: number;
  property: any = {};
  images: string[] = [];
  messageForm: FormGroup;
  faCalender = faCalendar;
  checkin!: NgbDate;
  checkout!: NgbDate;
  checkInDate!: Date;
  checkOutDate!: Date;
  bookingdto = {
    pid: 0,
    user_id: 0,
    checkin: new Date(),
    checkout: new Date()
  };
  messages: any[] = [];
  private baseUrl = 'http://localhost:9000';
  private chatBaseUrl = 'http://localhost:9090';

  constructor(
    private formBuilder: FormBuilder,
    private api: ApiService,
    private http: HttpClient,
    private route: ActivatedRoute
  ) {
    this.messageForm = this.formBuilder.group({
      pid: [''],
      message: [''],
      uid: [''],
      username: [''],
      oid: [''],
      ownername: [''],
      sid: ['']
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.pid = +params['id'];
      this.api.getPropertyById(this.pid).subscribe(res => {
        this.images = [];
        if (res.returnedImage) this.images.push(this.convertImage(res.returnedImage));
        if (res.returnedImage1) this.images.push(this.convertImage(res.returnedImage1));
        if (res.returnedImage2) this.images.push(this.convertImage(res.returnedImage2));
        if (res.returnedImage3) this.images.push(this.convertImage(res.returnedImage3));
        this.property = res;
        this.getMessages(res.pid, this.api.getUserId());
      });
    });
  }

  convertImage(img: any): string {
    return "data:image/jpeg;base64," + img;
  }

  reserve(pid: number): void {
    this.bookingdto.user_id = this.api.getUserId();
    this.checkInDate = new Date(this.checkin.year, this.checkin.month - 1, this.checkin.day);
    this.checkOutDate = new Date(this.checkout.year, this.checkout.month - 1, this.checkout.day);
    this.bookingdto.checkin = this.checkInDate;
    this.bookingdto.checkout = this.checkOutDate;
    this.bookingdto.pid = pid;
    this.http.post<any>(`${this.baseUrl}/users/booking`, this.bookingdto).subscribe({
      next: res => {
        alert("Booking successful!");
        this.sendEmail(pid, this.api.getUserId());
      },
      error: err => {
        alert("Booking failed. Please try again.");
      }
    });
  }

  sendEmail(pid: number, user_id: number): void {
    this.http.post<any>(`${this.baseUrl}/email/owner/${pid}/${user_id}`, null).subscribe({
      next: res => {},
      error: err => {}
    });
  }

  send(): void {
    this.http.post<any>(`${this.chatBaseUrl}/chat/send`, {
      pid: this.property.pid,
      message: this.messageForm.value.message,
      uid: this.api.getUserId(),
      username: this.api.getUserName(),
      oid: this.property.owner_id,
      ownername: "owner1",
      sid: this.api.getUserId()
    }).subscribe({
      next: res => {
        alert("Message Sent");
        this.messageForm.reset();
        this.messages = [];
        this.getMessages(this.property.pid, this.api.getUserId());
      },
      error: err => {
        alert("Couldn't send message");
      }
    });
  }

  getMessages(pid: number, uid: number): void {
    this.http.get<any>(`${this.chatBaseUrl}/chat/get/${pid},${uid}`).subscribe({
      next: res => {
        this.messages = res;
      },
      error: err => {}
    });
  }
}
