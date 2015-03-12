package CarWash;

import Simulator.Event;


/**
 *Creates a new car using the state machine's car factory. 
 *
 *If there are available wash-machines, make car allocate a wash-machine(by subtracting from currently available wash-machines). 
 *If not, add car to state's car queue (FIFO).
 * 
 * Creates a Leave Event (EventLeave) for when the car is done if it allocates a wash-machine (not if put in FIFO!).
 * 
 * Adds next Arrive Event (EventArrive).
 * 
 * @author Elis
 *
 */

public class EventArrive extends Event{
	
	
	private Car car;

	
	/**
	 * EventArrive constructor.
	 * @param time Time of Event Execution
	 */
	protected EventArrive(double time) {
		super(time);
		name = "Arrive";
	}

	
	
	protected void evaluate(){
		CarWashState state = ((CarWashState)simState);
		
		double timeElapsed = state.getCurrentEvent().getTime() - state.getPreviousEvent().getTime();
		
		//add elapsed queue time
		state.addTimeQueue(timeElapsed*state.carQueue.getSize());
				
		//add elapsed idle time
		state.addTimeIdle(timeElapsed*(state.getFastMachines()+state.getSlowMachines()));
		
		notifyState();
	}
	
	
	
	/**
	 * Creates a new car using the state machine's car factory.
	 * 
	 * If there are available wash-machines, make car allocate a wash-machine(by subtracting from currently available wash-machines). 
	 * If not, add car to state's car queue (FIFO).
	 * 
	 * Creates a Leave Event (EventLeave) for when the car is done if it allocates a wash-machine (not if put in FIFO!).
	 * 
	 * Adds next Arrive Event (EventArrive).
	 */
	protected void execute() {
		/*setEvent();
		evaluate();
		makeCar();
		allocate();
		addNext();*/
		/*
		 * evaluate with current settings to now
		 * set event & make changes due to new event
		 * notify
		 * 
		 * or
		 * 
		 * set new event
		 * evaluate with last event to current event
		 * change state to new event
		 * notify
		 */
		//name = "Arrive";
		setEvent();	//set new Event
		makeCar();	//create car, doesn't change state ((only in event at this moment))
		evaluate(); //evaluate state up-to new Event
		allocate();
		addNext();
	}
	
	
	/**
	 * creates a car. Called in execute().
	 */
	private void makeCar(){
		CarWashState state = ((CarWashState)simState);
		this.car = state.carFactory.createCar();
	}
	
	
	/**
	 * Allocates a wash-machine if available, with the car created and stored in this event (EventArrive).
	 * Puts car in car queue (FIFO), if there are no available wash-machines.
	 * adds a Stop Event (EventStop) to address when the wash-machine will be deallocated (if allocated! not added if put in queue!).
	 * 
	 * Called in execute().
	 */
	private void allocate(){
		
		CarWashState state = ((CarWashState)simState);
		
		
		if(state.getFastMachines()>0){
			state.decrFastMachines();
			double dtime = state.randStreamFastUniform.next();
			Event evLeave = new EventLeave(time+dtime, true, car);
			sortedSequence.add(evLeave);
			
			
		}else if(state.getSlowMachines()>0){
			state.decrSlowMachines();
			double dtime = state.randStreamSlowUniform.next();
			Event evLeave = new EventLeave(time+dtime, false, car);
			sortedSequence.add(evLeave);
			
			
		}else{
			if(state.carQueue.getSize()<state.getMaxQueueSize())
				state.carQueue.add(car);
			else{
				state.incrRejects();;
			}
		}
	}
	
	
	/**
	 * Adds a new Arrive Event (EventArrive). Called in execute().
	 */
	private void addNext(){
		CarWashState state = ((CarWashState)simState);
		double dtime = state.randStreamArriveExponential.next();
		Event evArrive = new EventArrive(time+dtime);
		sortedSequence.add(evArrive);
	}
	
	public int getCarID(){
		return car.getID();
	}
	
	
}
