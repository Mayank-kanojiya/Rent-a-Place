// app/Service/api.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Booking } from '../model/booking.model'; // Adjust if necessary

const TOKEN = 'u_token';
const USERNAME = 'u_username';
const USERID = 'u_userid';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:9000';   // Main backend
  private chatBaseUrl = 'http://localhost:9090'; // ChatMicroService

  login = false;

  private _propertySource = new Subject<any>();
  propertyView$ = this._propertySource.asObservable();

  private _propertyMessage = new Subject<any>();
  messageView$ = this._propertyMessage.asObservable();

  // --- Store current propertyId for messaging ---
  private propertyId: number = 0;
  setCurrentPropertyId(id: number) {
    this.propertyId = id;
  }
  getCurrentPropertyId(): number {
    return this.propertyId;
  }
  // ---

  constructor(private http: HttpClient) {}

  signUpUser(signUpData: any) {
    return this.http.post<any>(`${this.baseUrl}/users/signUp`, signUpData);
  }

  loginUser(loginData: any) {
    return this.http.post<any>(`${this.baseUrl}/users/login`, loginData);
  }

  viewPropertyDetails(pid: number) {
    return this.http.get<any>(`${this.baseUrl}/property/${pid}`);
  }

  getOwnerEmails() {
    const userId = this.getUserId();
    return this.http.get<any>(`${this.baseUrl}/email/owner/${userId}`);
  }

  getOwnerProperties(owner_id: string) {
    return this.http.get<any>(`${this.baseUrl}/owners/properties/${owner_id}`);
  }
  
  getPropertyById(pid: number) {
    return this.http.get<any>(`${this.baseUrl}/users/property/${pid}`);
  }

  // --- CHAT: fetch messages for owner dashboard ---
  getPropertyMessage(pid: number) {
    return this.http.get<any[]>(`${this.chatBaseUrl}/chat/dashboard/${pid}`);
  }

  setPropertyView(data: any) {
    this._propertySource.next(data);
  }

  setPropertyMessage(data: any) {
    this._propertyMessage.next(data);
  }

  setUserName(name: string) {
    window.localStorage.setItem(USERNAME, name);
  }

  getUserName(): string | null {
    return window.localStorage.getItem(USERNAME);
  }

  setUserId(userid: number) {
    window.localStorage.setItem(USERID, String(userid));
  }

  getUserId(): number {
    return Number(window.localStorage.getItem(USERID) || 0);
  }

  setToken(token: string) {
    window.localStorage.setItem(TOKEN, token);
  }

  getToken(): string | null {
    return window.localStorage.getItem(TOKEN);
  }

  setLoginStatus(status: boolean) {
    this.login = status;
  }

  logout() {
    window.localStorage.removeItem(USERNAME);
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USERID);
    this.login = false;
  }

  getUserBookings(userId: number) {
    return this.http.get<Booking[]>(`${this.baseUrl}/users/bookings/${userId}`);
  }
}
