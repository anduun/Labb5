package Simulator;

import CarWash.EventStart;

public class EventQueue {
	
	private SortedSequence sortedSequence;

	
	/**
	 * EventQueue constructor.
	 */
	EventQueue(){
		sortedSequence = new SortedSequence();
	}
	
	
	/**
	 * Returns true if sequence has more Events.
	 * @return
	 */
	boolean hasNext(){
		return sortedSequence.hasNext();
	}
	
	
	/**
	 * Executes next Event in sequence.
	 */
	void executeNext(){
		sortedSequence.executeNext();
	}
	
	
	/**
	 * Adds EventStart to Event Sequence at time 0. EventStart will add EventStop at time timeStop.
	 */
	void start(double timeStop){
		Event evStart = new EventStart(0, timeStop);
		sortedSequence.add(evStart);
	}
	
	
	
}
