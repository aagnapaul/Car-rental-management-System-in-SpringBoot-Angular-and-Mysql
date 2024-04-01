import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8081/api/admin';
  constructor(private http: HttpClient) { }

  postCar(carData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/car`, carData);
  }

  getAllCars():Observable<any>{
    return this.http.get(`${this.apiUrl}/cars`);
  }

  getCarById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/car/${id}`);
  }
  deleteCar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/car/${id}`);
  }
  
  updateCar(carId: number, carData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/car/${carId}`, carData);
  }
  getCarBookings():Observable<any>{
    return this.http.get(`${this.apiUrl}/car/bookings`);
  }
  changeBookingStatus(bookingId: number, status: string):Observable<any>{
    return this.http.get(`${this.apiUrl}/car/booking/${bookingId}/${status}`);
  }
  
  searchCars(brand: string, type: string, transmission: string, color: string): Observable<any[]> {
    let params = new HttpParams();
    if (brand) params = params.set('brand', brand);
    if (type) params = params.set('type', type);
    if (transmission) params = params.set('transmission', transmission);
    if (color) params = params.set('color', color);

    return this.http.get<any[]>(`${this.apiUrl}/search`, { params });
  }
 
}
