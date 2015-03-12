package CarWash;

import java.util.Observable;
import java.util.Observer;

import Simulator.SimState;
import Simulator.SimView;

public class CarWashView extends SimView implements Observer{
	
	private CarWashState state;
	
	public CarWashView(SimState simState){
		state = (CarWashState) simState;
	}
	
	private double roundVal(double val){
		return Math.round(val*100.0)/100.0;
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
		String time = "time: "+roundVal(state.getCurrentEvent().getTime());
		String fast =  "fast: "+state.getFastMachines();
		String slow =  "slow: "+state.getSlowMachines();
		String ID = "ID: -";
		String name = "name: "+state.getCurrentEvent().getName();
		String idleTime = "idle time: "+roundVal(state.getTimeIdle());
		String queueTime = "queue time: "+roundVal(state.getTimeQueue());
		String queueSize = "queue size: "+state.carQueue.getSize();
		String rejects = "rejects: "+state.getRejects();
		
		
		if(state.getCurrentEvent().getName() == "Start"){
			System.out.println("Fast machines: "+state.getFastMachines());
			System.out.println("Slow machines: "+state.getSlowMachines());
			System.out.println("Fast distribution: ("+state.fastMin+", "+state.fastMax+")");
			System.out.println("Slow distribution: ("+state.slowMin+", "+state.slowMax+")");
			System.out.println("Exponential distribution with lambda = "+state.lambda);
			System.out.println("---- ---- ---- ---- ---- ----");
		}
		
		if(state.getCurrentEvent().getName() == "Arrive")
			ID = "ID: "+((EventArrive)state.getCurrentEvent()).getCarID();
		else if(state.getCurrentEvent().getName() == "Leave")
			ID = "ID: "+((EventLeave)state.getCurrentEvent()).getCarID();

		System.out.println(time+"  "+fast+"  "+slow+"  "+ID+"  "+name+"  "+idleTime+"  "+queueTime+"  "+queueSize+"  "+rejects);
	
		
		if(state.getCurrentEvent().getName() == "Stop"){
			System.out.println("---- ---- ---- ---- ---- ----");
			System.out.println("Total idle machine time: "+state.getTimeIdle());
			System.out.println("Total queueing time: "+state.getTimeQueue());
			double meanQueueTime = state.getTimeQueue()/(Car.counter-state.getRejects());
			System.out.println("Mean queueing time: "+meanQueueTime);
			System.out.println("Rejected cars: "+state.getRejects());
		}
	}

}
