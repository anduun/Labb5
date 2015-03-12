package CarWash;

import Simulator.SimState;

public class CarWashState extends SimState{
	
	
	private int slowMachines, fastMachines;	//Number of available washing-machines.
	private double timeIdle, timeQueue;		//Elapsed time.
	private int rejects;
	private int maxQueueSize = 2;//TODO
	
	public ExponentialRandomStream randStreamArriveExponential;
	public UniformRandomStream randStreamSlowUniform;
	public UniformRandomStream randStreamFastUniform;
	
	public double fastMin, fastMax, slowMin, slowMax;
	public double lambda;
	
	
	public CarFactory carFactory;			//Car Factory, used to create cars.
	public FIFO carQueue;					//Car queue, handles cars when there are no available wash-machines.
	
	
	/**
	 * CarWashState constructor.
	 * @param numSlowMachines Number of slow washing-machines
	 * @param numFastMachines Number of fast washing-machines
	 */
	public CarWashState(int numSlowMachines, int numFastMachines){
		
		slowMachines = numSlowMachines;
		fastMachines = numFastMachines;
		
		fastMin = 2.8;
		fastMax = 4.6;
		
		slowMin = 3.5;
		slowMax = 6.7;
		
		lambda = 2.0;
		
		randStreamArriveExponential = new ExponentialRandomStream(lambda);	//TODO: adjustable values
		randStreamSlowUniform = new UniformRandomStream(slowMin, slowMax);		//TODO 4.5, 6.7
		randStreamFastUniform = new UniformRandomStream(fastMin, fastMax);		//TODO 2.8, 5.6
		
		timeIdle = timeQueue = 0;
		
		rejects = 0;
		
		carQueue = new FIFO();
		carFactory = new CarFactory();
	}
	
	
	/**
	 * Increase number of available fast machines.
	 */
	void incrFastMachines(){
		fastMachines++;
	}
	
	
	/**
	 * Decrease number of available fast machines.
	 */
	void decrFastMachines(){
		fastMachines--;
	}
	
	
	/**
	 * Increase number of available slow machines.
	 */
	void incrSlowMachines(){
		slowMachines++;
	}
	
	
	/**
	 * Decrease number of available slow machines.
	 */
	void decrSlowMachines(){
		slowMachines--;
	}
	
	
	/**
	 * Add time to elapsed idle time.
	 * @param time
	 */
	void addTimeIdle(double time){
		timeIdle+=time;
	}
	
	double getTimeIdle(){
		return timeIdle;
	}
	
	
	/**
	 * Add time to elapsed queue time.
	 * @param time
	 */
	void addTimeQueue(double time){
		timeQueue+=time;
	}
	
	double getTimeQueue(){
		return timeQueue;
	}
	
	
	/**
	 * Returns number of available fast machines.
	 * @return Number of available fast machines.
	 */
	public int getFastMachines(){
		return fastMachines;
	}
	
	
	/**
	 * Returns number of available slow machines.
	 * @return Number of available slow machines.
	 */
	public int getSlowMachines(){
		return slowMachines;
	}
	
	public void incrRejects(){
		rejects++;
	}
	
	public int getRejects(){
		return rejects;
	}
	
	int getMaxQueueSize(){
		return maxQueueSize;
	}
	
	
	
}
