package ld.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
	public Car() {
		System.out.println("Car.Car()");
	}

	public void init() {
		System.out.println("Car.init()");
	}

	public void detory() {
		System.out.println("Car.detory()");
	}

}
