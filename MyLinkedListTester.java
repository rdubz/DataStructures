package assignment6;

import java.util.ArrayList;
import java.util.Arrays;

public class MyLinkedListTester
{
	public static void main(String[] args)
	{	
		int size = 1000;
		MyLinkedList<Integer> names = new MyLinkedList<>();
		for(int i = 0; i < size; i++)
		{
			names.add(i, i);
		}
		
		//System.out.println(names.size());
		
		ArrayList<Integer> arraylist = new ArrayList<>();
		for(int i = 0; i < size; i++)
		{
			arraylist.add(i, i);
		}
		
	
		    long startTime, midpointTime, stopTime;

		    // First, spin computing stuff until one second has gone by.
		    // This allows this thread to stabilize.

		    startTime = System.nanoTime();
		    while (System.nanoTime() - startTime < 1000000000) 
		    { 
		    	// empty block
		    }

		    // Now, run the test.

		    long timesToLoop = 1000;

		    startTime = System.nanoTime();

		   // for (long i = 0; i < timesToLoop; i++)
		  //  {
		    	names.remove(names.size() - 1);
		    	//arraylist.remove(3);
		    //}
		        
		    midpointTime = System.nanoTime();

		    // Run an empty loop to capture the cost of running the loop.

		    for (long i = 0; i < timesToLoop; i++) 
		    { 
		    	 //empty block
		    }

		    stopTime = System.nanoTime();


		    double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime));
		       // / timesToLoop;

		    System.out.println("It takes exactly " + averageTime
		        + " nanoseconds to add 4 to the beginning ot the list");
		  
	}

}
