import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { UpdateCarComponent } from './components/update-car/update-car.component';
import { GetBookingComponent } from './components/get-booking/get-booking.component';
import { SearchCarComponent } from './components/search-car/search-car.component';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    UpdateCarComponent,
    GetBookingComponent,
    SearchCarComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
