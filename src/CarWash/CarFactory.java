package CarWash;

public class CarFactory {
	
	/**
	 * CarFactory constructor. Sets Car's static counter to 0.
	 */
	CarFactory(){
		Car.counter = 0;
	}
	
	
	
	/**
	 * Create and return new Car instance.
	 * @return new Car instance
	 */
	Car createCar(){
		
		return new Car();
		
	}
}
