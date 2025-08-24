import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { AddpropertyComponent } from './addproperty.component';

describe('AddpropertyComponent', () => {
  let component: AddpropertyComponent;
  let fixture: ComponentFixture<AddpropertyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddpropertyComponent],
      imports: [
        ReactiveFormsModule,      // For reactive forms if used in component
        HttpClientTestingModule,  // For HTTP requests mocking
        RouterTestingModule       // For routing related functionality
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AddpropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Add more tests for form validation, submission, etc., as needed
});
