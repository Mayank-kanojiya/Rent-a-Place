import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { PropertyComponent } from './property.component';

describe('PropertyComponent', () => {
  let component: PropertyComponent;
  let fixture: ComponentFixture<PropertyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PropertyComponent],
      imports: [
        HttpClientTestingModule,  // For HTTP calls if used
        RouterTestingModule       // For routing/navigation if used
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(PropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // Further tests could be added here to verify component functionality
});
