import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/components/signup/signup.component';
import { LoginComponent } from './auth/components/login/login.component';
import { AdminDashboardComponent } from './modules/admin/components/admin-dashboard/admin-dashboard.component';
import { PostCarComponent } from './modules/admin/components/post-car/post-car.component';
import { CustomerDashboardComponent } from './modules/customer/components/customer-dashboard/customer-dashboard.component';
import { BookCarComponent } from './modules/customer/components/book-car/book-car.component';
import { GetBookingComponent } from './modules/admin/components/get-booking/get-booking.component';
import { MyBookingsComponent } from './modules/customer/components/my-bookings/my-bookings.component';
import { UpdateCarComponent } from './modules/admin/components/update-car/update-car.component';
import { SearchCarComponent } from './modules/admin/components/search-car/search-car.component';

const routes: Routes = [
  { path:'register', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'customer-dashboard', component: CustomerDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'car', component: PostCarComponent },
  { path: 'customer/book/:id', component: BookCarComponent } ,
  { path:'bookings', component:GetBookingComponent},
  { path:'my-bookings',component:MyBookingsComponent},
  {path:'admin/car/:id',component:UpdateCarComponent},
  {path:'search-car',component:SearchCarComponent},
  {path:'searchcar',component:SearchCarComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
