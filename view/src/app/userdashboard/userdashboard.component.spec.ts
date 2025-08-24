import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';  // For routing dependencies
import { HttpClientTestingModule } from '@angular/common/http/testing';  // For HTTPClient dependencies

import { UserdashboardComponent } from './userdashboard.component';

describe('UserdashboardComponent', () => {
  let component: UserdashboardComponent;
  let fixture: ComponentFixture<UserdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserdashboardComponent],
      imports: [
        RouterTestingModule,       // Import if component uses routerLink or navigation
        HttpClientTestingModule    // Import if component makes HTTP requests
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Additional tests can be added here to verify component behavior
});
