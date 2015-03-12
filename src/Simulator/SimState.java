package Simulator;

import java.util.Observable;

abstract public class SimState extends Observable{
	protected Event eventPrevious, eventCurrent;
	protected SimState(){
		Event.bindSimState(this);
		eventPrevious = eventCurrent = null;
	}
	
	void setEvent(Event cEvent){
		this.eventPrevious = this.eventCurrent;
		this.eventCurrent = cEvent;
	}
	
	public Event getCurrentEvent(){
		return eventCurrent;
	}
	
	public Event getPreviousEvent(){
		return eventPrevious;
	}
	
	public void makeNotified(){
		this.setChanged();
		this.notifyObservers();
	}
}
