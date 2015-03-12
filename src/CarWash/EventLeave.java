package CarWash;

import Simulator.Event;

public class EventLeave extends Event{
	
	
	private Car car;
	private boolean isFast;

	
	/**
	 * EventLeave constructor.
	 * @param time Time of Event Execution
	 * @param isFast True if allocating a fast wash-machine. False if allocating a slow wash-machine
	 * @param car The car allocating the wash-machine
	 */
	protected EventLeave(double time, boolean isFast, Car car) {
		super(time);
		this.isFast = isFast;
		this.car = car;
		name = "Leave";
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
	 * Deallocates wash-machine corresponding to (isFast) by incrementing corresponding <number of wash-machines>.
	 */
	protected void execute() {
		setEvent();
		evaluate();
		deallocate();
		
	}
	
	
	/**
	 * Deallocates wash-machine corresponding to (isFast) by incrementing corresponding <number of wash-machines>.
	 * Called in execute().
	 */
	private void deallocate(){
		CarWashState state = ((CarWashState)simState);
		if(isFast){
			state.incrFastMachines();
		}else{
			state.incrSlowMachines();
		}
		
		if(!state.carQueue.isEmpty()){
			Car nextCar = (Car)state.carQueue.getFirst();
			state.carQueue.removeFirst();
			Event evLeave = new EventLeave(time+2, isFast, nextCar);
			sortedSequence.add(evLeave);
			if(isFast)
				state.decrFastMachines();
			else
				state.decrSlowMachines();
		}
	}
	
	
	
	public int getCarID(){
		return car.getID();
	}

}
