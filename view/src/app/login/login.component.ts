import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;
  name: string | undefined;
  role: string | undefined;

  private baseUrl = 'http://localhost:9000';

  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required),
    });
  }

  login() {
    if (this.loginForm.invalid) {
      alert('Please fill in both username and password.');
      return;
    }

    const logInRequestBody = this.loginForm.value;

    this.http.post<any>(`${this.baseUrl}/signIn/user`, logInRequestBody).subscribe({
      next: (res) => {
        this.api.setToken(res.token);
        this.api.setUserName(this.loginForm.value.username);
        this.api.setUserId(res.id);
        this.api.login = true;

        this.role = res.role;

        if (this.role === 'user') {
          this.router.navigate(['userdashboard']);
          console.log('user');
        } else {
          this.router.navigate(['ownerdashboard']);
          console.log('owner');
        }
      },
      error: (err) => {
        alert('Invalid Credentials');
      }
    });
  }
}
