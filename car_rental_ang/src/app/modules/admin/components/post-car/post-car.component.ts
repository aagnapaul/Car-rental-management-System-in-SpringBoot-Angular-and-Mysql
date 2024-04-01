import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrls: ['./post-car.component.css']
})
export class PostCarComponent {
 
  car: any = {
    brand: '',
    color: '',
    name: '',
    type: '',
    transmission: '',
    description: '',
    price: null,
    year: null,
    imageData: null
  };
  transmissions = ['Automatic', 'Manual'];
  onImageChange(event: any) {
    const file = event.target.files[0];
  
    if (file) {
      this.readFile(file);
    }
  }
  
  readFile(file: File) {
    const reader = new FileReader();
  
    reader.onloadend = () => {
      this.car.imageData = reader.result as string;
    };
  
    reader.readAsDataURL(file);
  }
  constructor(private adminService: AdminService , private router: Router ) { }
  onSubmit() {
    const formData = new FormData();

    formData.append('brand', this.car.brand);
    formData.append('color', this.car.color);
    formData.append('name', this.car.name);
    formData.append('type', this.car.type);
    formData.append('transmission', this.car.transmission);
    formData.append('description', this.car.description);
    formData.append('price', this.car.price.toString());
    formData.append('year', this.car.year.toString());

    // Append the file
    if (this.car.imageData) {
      const blob = this.dataURItoBlob(this.car.imageData);
      formData.append('imageFile', blob, 'car-image');
    }

    this.adminService.postCar(formData).subscribe(
      () => {
        console.log('Car posted successfully');
        alert('Car Posted successfully');
        this.router.navigateByUrl('/admin-dashboard');
      },
      error => {
        console.error('Error posting car:', error);
        alert('Error posting car');
      }
    );

    console.log('Car posted:', this.car);
  }

  dataURItoBlob(dataURI: string): Blob {
    const byteString = atob(dataURI.split(',')[1]);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);

    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ab], { type: 'image/jpeg' });
  }
}
