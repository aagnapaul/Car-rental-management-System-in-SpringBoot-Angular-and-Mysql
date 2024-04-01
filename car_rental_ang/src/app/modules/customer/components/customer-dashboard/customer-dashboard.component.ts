import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.css']
})
export class CustomerDashboardComponent {
  cars: any[] = [];
constructor(private service:CustomerService,private router: Router){

}
logNavigation() {
  console.log('Book button clicked. Navigating...');
}
ngOnInit(): void {

  this.getAllCars();

}
getAllCars(){
  this.service.getAllCars().subscribe((res) => {
    this.cars = res;
    console.log(res);
    if (Array.isArray(res)) {
      // Process each element and add a processedImg property
      res.forEach((element: any) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
      });

      // Assign the processed array to this.cars
      this.cars = res;
      console.log(this.cars);
    } else {
      console.error('Response is not an array');
    }
  });
}
}
