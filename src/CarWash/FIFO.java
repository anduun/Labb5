package CarWash;

import java.util.Vector;

/**
 * Java Object FIFO-queue.
 * @author Elis
 *
 */
public class FIFO {
	private Vector vector;
	
	
	/**
	 * FIFO constructor.
	 */
	FIFO(){
		vector = new Vector();
	}
	
	
	/**
	 * Add object to FIFO queue.
	 * @param obj
	 */
	void add(Object obj){
		vector.add(obj);
	}
	
	
	/**
	 * Remove first object in FIFO queue.
	 */
	void removeFirst(){
		vector.remove(0);
	}
	
	
	/**
	 * Returns first Object in FIFO queue.
	 * @return First Object in FIFO queue
	 */
	Object getFirst(){
		return vector.elementAt(0);
	}
	
	
	/**
	 * Returns whether FIFO queue is empty.
	 * @return whether FIFO queue is empty
	 */
	boolean isEmpty(){
		return (vector.size()==0);
	}
	
	int getSize(){
		return vector.size();
	}


}
