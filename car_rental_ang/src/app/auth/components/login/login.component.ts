import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginFormModel: any = {};

  constructor(private authService: AuthService,private router: Router) {}

  onSubmit(loginForm: any): void {

    if (loginForm.valid) {
      this.authService.login(this.loginFormModel).subscribe(
        (        response: any) => {
          console.log('Login successful:', response);
          alert("Login Successfully")

        this.authService.storeUserDetails(response);

        const userRole = response.userRole;
        console.log('User Role:', userRole); 
       

          if (this.authService.isAdminLoggedIn()) {
            this.router.navigate(['/admin-dashboard']);
          } else {
            this.router.navigate(['/customer-dashboard']); 
          }
  
        },
        
        (        error: any) => {
          console.error('Login failed:', error);
          alert("Login Failed");
         
        }
       
      );
    }
  }
}