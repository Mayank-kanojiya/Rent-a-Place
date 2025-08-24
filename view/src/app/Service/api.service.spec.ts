import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ApiService } from './api.service';

describe('ApiService', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], // Import HttpClientTestingModule
      providers: [ApiService]
    });
    service = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verify no outstanding requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // Example test for signUpUser()
  it('should send signup request', () => {
    const mockData = { username: 'testuser', password: 'pass123', email: 'test@example.com', role: 'owner' };

    service.signUpUser(mockData).subscribe();

    const req = httpMock.expectOne('http://localhost:9000/users/signUp');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockData);

    req.flush({}); // Simulate response
  });

  // Add more tests for loginUser() or other methods as required
});
