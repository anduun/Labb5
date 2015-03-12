package Simulator;

//bind from main?
import CarWash.CarWashState;
import CarWash.CarWashView;

public class Simulator {
	
	private EventQueue eventQueue;	//The Event Queue
	private SimState simState;		//The State Machine
	private SimView simView;
	
	
	private boolean running;		//keeps looping main-loop while (running==true)
	
	
	/**
	 * Simulator constructor. Initializes simulator and binds Event's static reference.
	 * @param time Time length of simulation (time between EventStart and EventStop).
	 */
	public Simulator(int time){
		
		Event.bindSimulator(this);			//bind static reference to Event
		
		eventQueue = new EventQueue();		//create eventQueue
		simState = new CarWashState(2, 2);	//create simState	TODO:((2,2) are test values for now)!
		simView = new CarWashView(simState);
		
		simState.addObserver(simView);
		
		running = true;						//main-loop boolean
		eventQueue.start(time);				//method adds EventStart to the Event Sequence.
		run();								//runs the main-loop
	}
	
	
	/**
	 * Runs the main-loop.
	 */
	private void run(){
		
		while(running){
			//TODO try catch
			if(eventQueue.hasNext())
				eventQueue.executeNext();
			else{
				//This should probably not occur unless something is wrong(?).
				running = false;
				System.out.print("Ran out of events\n");
			}
		}
		
	}
	
	
	/**
	 * Called to end the main-loop, running=false;
	 */
	public void stop(){
		running = false;
	}
}
