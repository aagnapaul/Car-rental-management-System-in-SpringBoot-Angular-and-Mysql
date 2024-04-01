import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth/auth.service';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8081/api/customer';
  constructor(private http: HttpClient,private authService: AuthService) { }

  getAllCars():Observable<any>{
    return this.http.get(`${this.apiUrl}/cars`);
  }
  getCarById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/car/${id}`);
  }
 bookCar(bookACarDto: any): Observable<any> {
  return this.http.post(`${this.apiUrl}/car/book`, bookACarDto)
    .pipe(
      catchError((error: any) => {
        console.error('Error while booking car:', error);
        return throwError('Failed to book car. Please try again later.');
      })
    );
}
  getBookingsByUserId(): Observable<any> {
    const userId = this.authService.getUserId();
    return this.http.get(`${this.apiUrl}/car/bookings/${userId}`);
  }
}
