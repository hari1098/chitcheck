import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });
  }

  // Profile API calls
  getProfile(): Observable<any> {
    return this.http.get(`${this.baseUrl}/profile`, { headers: this.getHeaders() });
  }

  updateProfile(profileData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/profile`, profileData, { headers: this.getHeaders() });
  }

  // Defaulters API calls
  getDefaulters(): Observable<any> {
    return this.http.get(`${this.baseUrl}/personal-details`, { headers: this.getHeaders() });
  }

  createDefaulter(defaulterData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/personal-details`, defaulterData, { headers: this.getHeaders() });
  }

  searchDefaulters(searchParams: any): Observable<any> {
    return this.http.get(`${this.baseUrl}/personal-details/search`, { 
      params: searchParams,
      headers: this.getHeaders() 
    });
  }

  // Dashboard API calls
  getDashboardStats(): Observable<any> {
    return this.http.get(`${this.baseUrl}/dashboard/stats`, { headers: this.getHeaders() });
  }
}