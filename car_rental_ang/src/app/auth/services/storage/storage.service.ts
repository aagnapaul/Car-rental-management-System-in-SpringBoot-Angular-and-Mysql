import { Injectable } from '@angular/core';
const USER ='user'
const USER_ROLE = 'userRole';
const USER_ID = 'userId';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
 
  saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
    this.saveUserId(user.id);
  }

  getUser(): any {
    const userString = window.localStorage.getItem(USER);
    return userString ? JSON.parse(userString) : null;
  }
  getUserId(): string {
    const user = this.getUser();
    return user ? user.id : '';
  }

  saveUserId(userId: string): void {
    window.localStorage.setItem(USER_ID, userId);
  }
  // // Retrieve user ID from local storage
  // getUserId(): string {
  //   const user = this.getUser();
  //   return user ? user.id : '';
  // }

  removeUser(): void {
    window.localStorage.removeItem(USER);
  }

  saveUserRole(userRole: string): void {
    console.log('Saving user role:', userRole);
    window.localStorage.removeItem(USER_ROLE);
    window.localStorage.setItem(USER_ROLE, userRole);
  }

  getUserRole(): string | null {
    const userRole = window.localStorage.getItem(USER_ROLE);
    console.log('Retrieved user role:', userRole);
    return userRole;
  }

  isAdminLoggedIn(): boolean {
    const userRole = this.getUserRole();
    return userRole === 'Admin';
  }

  isCustomerLoggedIn(): boolean {
    const userRole = this.getUserRole();
    return userRole === 'Customer';
  }

  logout(): void {
    this.removeUser();
    this.removeUserRole();
  }

  private removeUserRole(): void {
    window.localStorage.removeItem(USER_ROLE);
  }

  constructor() { }
}
