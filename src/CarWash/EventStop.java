package CarWash;

import Simulator.Event;

/**
 * Stop Event - Quits the main-loop of the program when executed from the event sequence.
 * @author Elis
 *
 */

public class EventStop extends Event{

	
	/**
	 * EventStop constructor.
	 * @param time Time of Event Execution
	 */
	protected EventStop(double time) {
		super(time);
		name = "Stop";
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
	 * Quits the main-loop, calls simulator.stop()
	 */
	protected void execute(){
		setEvent();
		evaluate();
		stopWash();
	}
	
	
	/**
	 * Quits the main-loop, calls simulator.stop(). Called from execute().
	 */
	private void stopWash(){
		simulator.stop();
		/*CarWashState state = ((CarWashState)simState);
		System.out.print("\n\n--------\n\n");
		System.out.println("IDLE:::" + state.getTimeIdle());
		System.out.println("QUEUE::" + state.getTimeQueue());*/
	}
}
