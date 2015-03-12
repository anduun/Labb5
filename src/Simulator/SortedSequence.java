package Simulator;

//import java.util.*; //covers vector
import java.util.Vector;

public class SortedSequence {

	private Vector<Event> eventSequence;
	
	
	
	/**
	 * SortedSequence constructor. Initializes sequence and binds Event's static reference.
	 */
	SortedSequence(){
		eventSequence = new Vector<Event>();
		Event.bindSortedSequence(this);
	}
	
	
	/**
	 * Returns true if sequence has more Events.
	 * @return
	 */
	public boolean hasNext(){
		return (eventSequence.size() != 0);
	}
	
	
	/**
	 * Executes next Event in sequence.
	 */
	public void executeNext(){
		sort();
		eventSequence.elementAt(0).execute();
		removeFirst();
	}
	
	
	/**
	 * Sorts Event Sequence by time aspect. Uses Bubble-Sort. This method is called before executing an event.
	 */
	public void sort(){
		 int j;
	     boolean flag = true;   // set flag to true to begin first pass
	     Event temp;   //holding variable

	     while ( flag )
	     {
	            flag= false;    //set flag to false awaiting a possible swap
	            for( j=0;  j < eventSequence.size() -1;  j++ )
	            {
	                   if ( eventSequence.elementAt(j).getTime() > eventSequence.elementAt(j+1).getTime() )   // change to > for ascending sort
	                   {
	                           temp = eventSequence.elementAt(j);                //swap elements
	                           eventSequence.set(j, eventSequence.elementAt(j+1));
	                           eventSequence.set(j+1, temp);
	                          flag = true;              //shows a swap occurred  
	                  } 
	            } 
	      } 
	}
	
	
	/**
	 * Add Event to sequence.
	 * @param event
	 */
	public void add(Event event){
		eventSequence.add(event);
	}
	
	/**
	 * Removes the first Event in sequence. This method is called when executing an event.
	 */
	private void removeFirst(){
		eventSequence.removeElementAt(0);
	}
}
