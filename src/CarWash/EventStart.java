package CarWash;

import Simulator.Event;

/**
 * Start Event - Creates Stop Event (EventStop) when constructed. 
 * Adds the first Arrive Event (EventArrive) when executed from the event sequence.
 * @author Elis
 *
 */

public class EventStart extends Event{

	
	
	/**
	 * EventStart constructor. Creates EventStop at time <timeStop>.
	 * @param timeStart Time of Event Execution for EventStart
	 * @param timeStop Time of Event Execution for EventStop
	 */
	public EventStart(double timeStart, double timeStop) {
		super(timeStart);
		addEventStop(timeStop);
		name = "Start";
	}
	
	
	protected void evaluate(){
		/*CarWashState state = ((CarWashState)simState);
		double timeElapsed = time - state.getTimeLast();
		
		//add elapsed queue time
		state.addTimeQueue(timeElapsed*state.carQueue.getSize());
		
		//add elapsed idle time
		state.addTimeIdle(timeElapsed*(state.getFastMachines()+state.getSlowMachines()));
		
		state.setTimeLast(time);*/
		notifyState();
	}
	
	
	/**
	 * Create EventStop at time <timeStop>
	 * @param timeStop
	 */
	private void addEventStop(double timeStop){
		Event evStop = new EventStop(timeStop);
		sortedSequence.add(evStop);
	}
	
	/**
	 * Adds first EventArrive to the sorted event sequence.
	 */
	protected void execute() {
		setEvent();
		evaluate();
		addEventArrive();
	}
	
	
	
	/**
	 * Adds first EventArrive to the sorted event sequence. Called from execute().
	 */
	private void addEventArrive(){
		CarWashState state = ((CarWashState)simState);
		double dtime = state.randStreamArriveExponential.next();
		Event evArrive = new EventArrive(time+dtime);
		sortedSequence.add(evArrive);
	}

}
