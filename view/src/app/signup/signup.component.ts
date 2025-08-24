import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public signupForm!: FormGroup;
  private baseUrl = 'http://localhost:9000';  // Correct backend port

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, Validators.required),
      name: new FormControl(null, Validators.required),
      phone: new FormControl(null, Validators.required),
      role: new FormControl('user')
    });
  }

  signUp() {
    if (this.signupForm.valid) {
      const signUpRequestBody = this.signupForm.value;

      this.http
        .post<any>(`${this.baseUrl}/users/signUp`, signUpRequestBody)
        .subscribe({
          next: (res) => {
            console.log(res);
            alert('Sign Up Is Done Successfully');
            this.signupForm.reset();
            this.router.navigate(['login']);
          },
          error: (err) => {
            if (err.status === 409) {
              alert('Username already exists. Please choose a different username.');
            } else {
              alert('Registration failed. Please try again later.');
            }
          }
        });
    } else {
      alert('Please fill all required details correctly.');
    }
  }
}
