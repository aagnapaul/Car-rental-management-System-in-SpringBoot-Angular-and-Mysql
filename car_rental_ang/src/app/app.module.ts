import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { PostCarComponent } from './modules/admin/components/post-car/post-car.component'
import { CommonModule, DatePipe } from '@angular/common'; 
import { AdminDashboardComponent } from './modules/admin/components/admin-dashboard/admin-dashboard.component';  // Import AdminDashboardComponent
import { CustomerDashboardComponent } from './modules/customer/components/customer-dashboard/customer-dashboard.component';
import { BookCarComponent } from './modules/customer/components/book-car/book-car.component';
import { MyBookingsComponent } from './modules/customer/components/my-bookings/my-bookings.component';
import { GetBookingComponent } from './modules/admin/components/get-booking/get-booking.component';
import { UpdateCarComponent } from './modules/admin/components/update-car/update-car.component';
import { SearchCarComponent } from './modules/admin/components/search-car/search-car.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    PostCarComponent,
    AdminDashboardComponent,
    CustomerDashboardComponent,
    BookCarComponent,
    MyBookingsComponent,
    GetBookingComponent,
    UpdateCarComponent,
    SearchCarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    HttpClientModule
  ],
  providers:  [
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
