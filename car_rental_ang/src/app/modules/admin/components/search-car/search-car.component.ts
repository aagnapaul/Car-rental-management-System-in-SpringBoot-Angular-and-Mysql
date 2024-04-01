import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrls: ['./search-car.component.css']
})
export class SearchCarComponent implements OnInit{
  searchCar = {
    brand: '',
    type: '',
    transmission: '',
    color: ''
  };
  cars: any[] | undefined;

  constructor(private Service: AdminService) {}
  ngOnInit(): void {

    // this.getAllCars();
  
  }
  getAllCars(){
    this.Service.getAllCars().subscribe((res) => {
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
  searchCars() {
    console.log('Search button clicked'); 
    console.log('Search criteria:', this.searchCars); 
    this.Service.searchCars(this.searchCar.brand, this.searchCar.type, this.searchCar.transmission, this.searchCar.color)
    .subscribe((res) => {
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
