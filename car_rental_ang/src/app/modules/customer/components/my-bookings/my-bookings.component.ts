import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.css']
})
export class MyBookingsComponent implements OnInit{
  bookings: any[]=[];

constructor(private service :CustomerService){}
  ngOnInit(): void {
    this.getMyBookings(); 
  }
  getMyBookings() {
    this.service.getBookingsByUserId().subscribe((res) => {
      console.log(res);
      this.bookings = res;
    });
  }
 
}
