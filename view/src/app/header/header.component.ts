import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private api: ApiService, private router: Router) { }

  ngOnInit(): void { }

  get isLoggedIn(): boolean {
    return this.api.login;
  }

  logout(): void {
    this.api.logout();
    this.router.navigate(['/login']);
  }
}
