import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';



@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})


export class AdminDashboardComponent  implements OnInit{
  cars: any[] = [];
  constructor(private adminService: AdminService) { }
  ngOnInit(): void {

    this.getAllCars();
  
  }
  getAllCars(){
    this.adminService.getAllCars().subscribe((res) => {
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
deleteCar(id:number){
console.log(id);
this.adminService.deleteCar(id).subscribe((res)=>{
this.getAllCars();
alert("Delete Succesfully");
})
}
}

