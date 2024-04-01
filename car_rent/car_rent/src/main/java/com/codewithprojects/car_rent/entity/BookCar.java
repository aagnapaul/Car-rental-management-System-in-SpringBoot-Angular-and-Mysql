package com.codewithprojects.car_rent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.codewithprojects.car_rent.dto.BookACarDto;
import com.codewithprojects.car_rent.enums.BookCarStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookCar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fromDate;
	private Date toDate;
	private Long days;
	private Long price;
	@Enumerated(EnumType.STRING)
	 private BookCarStatus bookCarStatus;
	    
	    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "user_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Users user;
	    
	    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "car_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Car car;
	    
	    public BookACarDto getBookACarDto() {
	        BookACarDto bookACarDto = new BookACarDto();
	        bookACarDto.setId(id);
	        bookACarDto.setDays(days);
	        bookACarDto.setBookCarStatus(bookCarStatus);
	        bookACarDto.setPrice(price);
	        bookACarDto.setToDate(toDate);
	        bookACarDto.setFromDate(fromDate);
	        bookACarDto.setEmail(user.getEmail());
	        bookACarDto.setUsername(user.getName());
	        bookACarDto.setUserId(user.getId());
	        bookACarDto.setCarId(car.getId());
	        return bookACarDto;
	    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public BookCarStatus getBookCarStatus() {
		return bookCarStatus;
	}
	public void setBookCarStatus(BookCarStatus bookCarStatus) {
		this.bookCarStatus = bookCarStatus;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
}
