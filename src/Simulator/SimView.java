package Simulator;


import java.util.Observable;
import java.util.Observer;

abstract public class SimView implements Observer{
	protected SimState simState;
	public void update(Observable arg0, Object arg1){}
}
