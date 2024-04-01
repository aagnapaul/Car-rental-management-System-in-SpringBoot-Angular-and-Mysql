import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupFormModel: any = {};

  constructor(private signupService: AuthService,private router: Router) {}

  onSubmit(signupForm: any): void {
    if (signupForm.valid) {
      this.signupService.signup(this.signupFormModel).subscribe(
        (        response: any) => {
          console.log('Signup successful:', response);
          alert("Signup Succesfully")
          this.router.navigateByUrl('/login');
    
        },
        (        error: any) => {
          console.error('Signup failed:', error);
          alert("Signup Failed")
        
        }
      );
    }
  }

}
