import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetBookingComponent } from './get-booking.component';

describe('GetBookingComponent', () => {
  let component: GetBookingComponent;
  let fixture: ComponentFixture<GetBookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetBookingComponent]
    });
    fixture = TestBed.createComponent(GetBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
