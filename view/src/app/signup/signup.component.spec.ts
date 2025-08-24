import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { SignupComponent } from './signup.component';

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports: [
        ReactiveFormsModule,        // Import ReactiveFormsModule if using reactive forms
        HttpClientTestingModule     // Import HttpClientTestingModule to mock HTTP requests
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Example test to check initial form validity
  it('form should be invalid when empty', () => {
    expect(component.signupForm.valid).toBeFalsy();
  });

  // Add more tests to simulate user input and form submission as needed
});
