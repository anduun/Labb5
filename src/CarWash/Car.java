package CarWash;

public class Car {
	public static int counter;	//Car counter
	private int ID;				//Unique ID 
	
	
	/**
	 * Car constructor.
	 */
	Car(){
		ID = counter++;
	}
	
	
	/**
	 * Returns the ID of the Car instance.
	 * @return ID
	 */
	public int getID(){
		return ID;
	}
}
