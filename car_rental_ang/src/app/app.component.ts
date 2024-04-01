import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, NavigationStart, NavigationError, NavigationCancel } from '@angular/router';
import { StorageService } from './auth/services/storage/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isAdminLoggedIn: boolean = false;
  isCustomerLoggedIn: boolean = false;

  constructor(private router: Router, private storageService: StorageService) {}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.handleNavigationEnd();
      } else if (event instanceof NavigationError || event instanceof NavigationCancel) {
        console.error('Navigation error or cancellation:', event);
      } else if (event instanceof NavigationStart) {
        console.log('Navigation started:', event);
      }
    });
  }

  private handleNavigationEnd(): void {
    this.isAdminLoggedIn = this.storageService.isAdminLoggedIn();
    this.isCustomerLoggedIn = this.storageService.isCustomerLoggedIn();
    console.log('Admin logged in:', this.isAdminLoggedIn);
    console.log('Customer logged in:', this.isCustomerLoggedIn);

    if (!this.isAdminLoggedIn && !this.isCustomerLoggedIn) {
      // Redirect unauthorized users to login or registration page
      this.router.navigate(['/signup']); // Replace with your desired login route
    }
  }

  onLogout(): void {
    this.storageService.logout();
    this.router.navigate(['/login']);
    // Additional logic, such as redirecting to the login page
  }
}