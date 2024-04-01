import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, delay, of, tap } from 'rxjs';
import { StorageService } from '../storage/storage.service';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  [x: string]: any;
  private apiUrl = 'http://localhost:8081/api/auth'; 
  constructor(private http: HttpClient,private storageService: StorageService) {}

  signup(signupData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/signup`, signupData);
  }
  login(loginData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, loginData).pipe(
      tap((response: any) => {
        console.log('Login response:', response);
  
        // Ensure the user role is present in the response
        if (response && response.user) {
          this.storeUserDetails(response.user);  // Update user details in local storage
        } else {
          console.error('User role not found in the login response.');
        }
      })
    );
  }

  // Store user details in local storage
  storeUserDetails(user: any): void {
    this.storageService.saveUser(user);
    this.storageService.saveUserRole(user.userRole);
    this.storageService.saveUserId(user.id);
    
  }

  
  getUserDetails(): any {
    return this.storageService.getUser();
  }

  // Remove user details from local storage
  removeUserDetails(): void {
    this.storageService.removeUser();
    
  }
  getUserId(): string {
    return this.storageService.getUserId();
  }
  getUserRole(): string | null {
    return this.storageService.getUserRole();
  }
  isAdminLoggedIn(): boolean {
    const userRole = this.getUserRole();
    console.log('User role in AuthService:', userRole);
    return userRole === 'Admin';
  }
  isCustomerLoggedIn(): boolean {
    const userRole = this.getUserRole();
    console.log('User role in AuthService:', userRole);
    return userRole === 'Customer';
  }
  
}
