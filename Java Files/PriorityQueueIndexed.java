
// Ver 0.1:  Sat, Feb 28.  Initial description.
// Ver 1.0:  Tue, Mar 03.  Added more comments.  Modified decreaseKey().

import java.lang.Comparable;
import java.lang.reflect.Array;
import java.util.concurrent.CompletableFuture;
/**
 * 
 * @author dipti
 *indexed priority queue implementation. 
 * @param <T>
 */
public class PriorityQueueIndexed<T extends Comparable<? super T> & PQIndex> {
    T[] queue;
    static int size=0; // to check number of elements
 
    /** Build a priority queue with a given array q */
    PriorityQueueIndexed(T[] q) {
    	
    	queue=q; 
    	size=q.length-1;  
    	
    }
    
    

    /** Create an empty priority queue of given maximum size */
    @SuppressWarnings("unchecked")
	PriorityQueueIndexed(int n) {
    	
    	queue=(T[])Array.newInstance(queue.getClass().getComponentType(),n); 
    	
    	
    	size=n;
    	
    	
    }
/**
 * 
 * @param x : value to be inserted in the queue
 * check whether queue is full or not. if queue is full resize the queue
 * call add function to locate the exact position of x.
 */
    void insert(T x) {
    	
    	if(size== queue.length-1)
    	{
    		resizeQueue(queue.length*2+1);
    	}
	add(x);
    }
/**
 * Resize the queue if queue is full
 * @param n : new size 
 */
    private void resizeQueue(int n) {
		// TODO Auto-generated method stub
    	T[] oldQ = queue;
    	T[]Newqueue= (T[])Array.newInstance(queue.getClass().getComponentType(),n);
    	for(int i=1; i<oldQ.length;i++)
    	{
    		Newqueue [i]=oldQ[i];
    		
    	}
    	
    	
    	queue=Newqueue;
    	
		
	}


/**
 * Add the value in the priority queue
 * call percolate up to locate the exact position of the new element
 * @param x: value to be added
 */
	void add(T x) {
    	queue[0]=x;  // to avoid null pointer excpetions
    	
    	//size+1 is hole
    	int hole =size+1;
    	queue[hole]=x;
    	percolateUp(hole);
    	
    	size++;
    }

    T remove() {
       return deleteMin();
    }

    T deleteMin() {
    	T val = queue[1];
    	queue[1]=queue[size];
    	size--;
    	percolateDown(1);
    	
	return val;
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
    	queue[0]=x;
	percolateUp(x.getIndex());
	//buildHeap(queue);
    }
    
/**
 *  
 * @return minimum value of the queue.
 */
    T min() { 
	return queue[1]; // returning minimum value
    }

    /** Priority of element at index i of queue has decreased.  It may violate heap order.
     *  Restore heap property */
    void percolateUp(int i) {
    	
    	
    	T val = queue[i];
    	int hole=i;			// create hole 
    
    	while(queue[hole/2].compareTo(val) >0 )
    	{
    		
    	
    		
    		queue[hole]=queue[hole/2];			// swap the nodes
    		
    		
    		queue[hole].putIndex(hole);		//set the new index 
    		
    								
    		hole=hole/2;						// move to parent node
    	}
    	
    	queue[hole]=val;					// set the location 
    	queue[hole].putIndex(hole);			// set new index
    }

    /** Create heap order for sub-heap rooted at node i.  Precondition: Heap order may be violated 
     *  at node i, but its children are roots of heaps that are fine.  Restore heap property */
    void percolateDown(int i)
    {
    	  	if(2*i > size) return;	//case 1
    	
    	if(2*i == size)
    		{	
    		if(queue[i].compareTo(queue[2*i]) >0)   //case 2
    		{
    		T temp=queue[i];
    		queue[i]=queue[2*i];
    		queue[2*i]=temp;
    		
    		queue[2*i].putIndex(2*i);		//set new index values
    		queue[i].putIndex(i);
    			
    		}
    		return;
    		}
    	 if(2*i < size)
    		{
    			int child = (queue[2*i].compareTo(queue[(2*i)+1])>0? (2*i+1):(2*i)); // to avoid null pointer exception put this in last loop
    			
    			if(queue[i].compareTo(queue[child]) >0)
    			{
    				T temp=queue[i];
    	    		queue[i]=queue[child];
    	    		queue[child]=temp;
    	    		
    	    		queue[i].putIndex(i);		//set new index values
    	    		queue[child].putIndex(child);
    	    			
    	    		
    				percolateDown(child);
    			}
    		}
    }

    /** Create a heap.  Precondition: none.  Array may violate heap order in many places. */
    void buildHeap(T[] arr) {

    	//System.out.println("size"+size);
    	
    	for(int i=(size+1)/2;i>=1;i--)
    	percolateDown(i);
    	
    	
    	
    }


/** 
 * checks whether queue is empty or not
 * @return
 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size==0);
	}




}
