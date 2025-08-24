import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  messages: any[] = [];
  messageForm: FormGroup;
  private baseUrl = 'http://localhost:9090';

  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient
  ) {
    this.messageForm = this.formBuilder.group({
      message: ['']
    });
  }

  ngOnInit(): void {
    const pid = this.api.getCurrentPropertyId();
    this.http.get<any[]>(`${this.baseUrl}/chat/dashboard/${pid}`)
      .subscribe(res => {
        this.messages = res;
        console.log(this.messages);
      });
  }

  send(pid: number, uid: number, username: string): void {
    const messageText = this.messageForm.value.message;
    if (!messageText) { alert('Message required'); return; }
    this.http.post<any>(`${this.baseUrl}/chat/send`, {
      pid: pid,
      message: messageText,
      uid: uid,
      username: username,
      oid: this.api.getUserId(),
      ownername: this.api.getUserName(),
      sid: this.api.getUserId()
    }).subscribe({
      next: (res) => {
        alert("Message Sent");
        this.messageForm.reset();
        // Reload messages:
        this.http.get<any[]>(`${this.baseUrl}/chat/dashboard/${pid}`)
          .subscribe(data => this.messages = data);
      },
      error: (err) => {
        alert("Couldn't send message");
      }
    });
  }
}
