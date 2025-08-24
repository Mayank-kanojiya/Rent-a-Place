import { Component, OnInit } from '@angular/core';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-emails',
  templateUrl: './emails.component.html',
  styleUrls: ['./emails.component.css']
})
export class EmailsComponent implements OnInit {
  emails: any = [];

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.api.getOwnerEmails().subscribe(res => {
      this.emails = res;
      console.log(res);
    });
  }
}
