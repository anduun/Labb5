package Simulator;



public abstract class Event {
	protected double time;
	protected String name="LOREM";
	static protected SortedSequence sortedSequence;
	static protected Simulator simulator;
	static protected SimState simState;
	
	
	
	
	/**
	 * Binds the Simulator to the Event class static Simulator reference.
	 * @param _simulator The Simulator instance
	 */
	public static void bindSimulator(Simulator _simulator){
		simulator = _simulator;
	}
	
	
	
	/**
	 * Binds the SimState to the Event class static SimState reference.
	 * @param _simState The SimState instance
	 */
	public static void bindSimState(SimState _simState){
		simState = _simState;
	}
	
	
	
	/**
	 * Binds the SortedSequence to the Event class static SortedSequence reference.
	 * @param _sortedSequence The SortedSequence instance
	 */
	public static void bindSortedSequence(SortedSequence _sortedSequence){
		sortedSequence = _sortedSequence;
	}
	
	
	
	/**
	 * Event constructor.
	 * @param time Time at event execution
	 */
	protected Event(double time){
		this.time = time;
	}
	
	
	/**
	 * Returns the Event's time.
	 * @return
	 */
	public double getTime(){
		return time;
	}
	
	protected void setEvent(){
		simState.setEvent(this);
	}
	
	protected void notifyState(){
		simState.makeNotified();
	}
	
	public String getName(){
		return name;
	}
	
	
	
	abstract protected void evaluate();
	abstract protected void execute();
	
	
}
