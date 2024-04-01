import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-booking',
  templateUrl: './get-booking.component.html',
  styleUrls: ['./get-booking.component.css']
})
export class GetBookingComponent {
  bookings: any[]=[];
constructor(private adminService:AdminService){
  this.getBookings();
}
getBookings(){
  this.adminService.getCarBookings().subscribe((res)=>{
    console.log(res);
    this.bookings = res;
  })
}
changeBookingStatus(bookingId: number, status: string) {
  console.log(bookingId, status);
  this.adminService.changeBookingStatus(bookingId, status).subscribe(
    () => {
      console.log('Booking status changed successfully.');
      
      this.getBookings();
    },
    (error) => {
      console.error('Failed to change booking status:', error);
      // Handle error appropriately, e.g., show an error message to the user
    }
  );
}
}
