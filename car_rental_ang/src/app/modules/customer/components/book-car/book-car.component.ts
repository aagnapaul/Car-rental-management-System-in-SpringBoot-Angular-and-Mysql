import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { StorageService } from 'src/app/auth/services/storage/storage.service';
import { AuthService } from 'src/app/auth/services/auth/auth.service';

@Component({
  selector: 'app-book-car',
  templateUrl: './book-car.component.html',
  styleUrls: ['./book-car.component.css']
})
export class BookCarComponent implements OnInit {
  carId: number = this.route.snapshot.params["id"];
  car: any;
  fromDate: any; 
  toDate: any; 
  processedImage: any;
 
  

  constructor(
    private service: CustomerService,
    private route: ActivatedRoute, 
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.carId = this.route.snapshot.params["id"]; 
    this.getCarById();
    this.fromDate = this.formatDate(new Date());
    this.toDate = this.formatDate(new Date()); 
    
  }

  formatDate(date: Date): string {
    return date.toISOString().substring(0, 10);
  }

  getCarById(): void {
    this.service.getCarById(this.carId).subscribe((res) => {
      console.log(res);
      this.processedImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.car = res;
    });
  }

  bookCar(): void {
    console.log("Car ID:", this.carId); 
    console.log('From Date:', this.fromDate);
    console.log('To Date:', this.toDate);
    console.log("User ID:", this.authService.getUserId());
    const userId = this.authService.getUserId();
   
    
    const bookACarDto = {
      carId: this.carId,
      fromDate: this.fromDate,
      toDate: this.toDate,
      userId:userId,
     
    };
    console.log('Book Car DTO:', bookACarDto);

    this.service.bookCar(bookACarDto).subscribe((res)=>{
      console.log(res);
      alert("Book Success");
      this.router.navigate(['/customer-dashboard']); 
     })
  }
  }
