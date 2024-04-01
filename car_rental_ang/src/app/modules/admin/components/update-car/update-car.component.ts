import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.css']
})
export class UpdateCarComponent implements OnInit{
 
  carId: number = 0;
  carDto: any = {
    brand: '',
    color: '',
    name: '',
    type: '',
    transmission: '',
    description: '',
    price: null,
    year: null,
    imageFile: null
   
  };
  
  transmissions = ['Automatic', 'Manual'];
  errorMessage!: string;

  constructor(private adminService: AdminService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    
    this.carId = +this.route.snapshot.params['id'];
    this.getCarDetails();
  }

  getCarDetails() {
    this.adminService.getCarById(this.carId).subscribe(
      (carData) => {
        this.carDto = carData;
      },
      (error) => {
        console.error('Error retrieving car details:', error);
      }
    );
  }

  onSubmit() {
    
    this.adminService.updateCar(this.carId, this.carDto).subscribe(
      () => {
        this.router.navigate(['/admin/dashboard']);
        console.log('Car updated successfully');
       
      },
      (error: HttpErrorResponse) => {
        if (error.status === 404) {
          this.errorMessage = 'Car not found. Please check the car ID.';
        } else {
          this.errorMessage = 'Error updating car. Please try again later.';
        }
      }
    );
}
}