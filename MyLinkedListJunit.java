package assignment6;

import static org.junit.Assert.*;


import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Ryan Williams and Thuy Nguyen 
 * 
 * The purpose of this class is to test the functionality of all the 
 * methods implemented by us, the programmers in our MyLinkedList<E> class which 
 * represents the LinkedList data structure provided by the java API. 
 *
 */
public class MyLinkedListJunit
{
	private MyLinkedList<String> strg;
	private MyLinkedList<Integer> ints, ints2, ints3;

	@Before
	public void setUp() throws Exception
	{
		strg = new MyLinkedList<>();
		//show that add will shift elements when you keep adding at the same spot
		strg.add(0, "Computer");
		strg.add(0, "Science");
		strg.add(0, "Is");
		strg.add(0, "Cool");
		
		ints = new MyLinkedList<>();
		ints.addFirst(3);
		ints.addLast(5);
		ints.addFirst(2);
		ints.addFirst(1);
		
		
		ints2 = new MyLinkedList<>();
		ints2.addFirst(3);
		ints2.addLast(7);
		ints2.addFirst(2);
		ints2.addFirst(1);
		
		ints3 = new MyLinkedList<>();
		ints3.add(0, 14);
		ints3.add(1, 3);
		ints3.add(2, 6);
		ints3.add(3, 3);
		ints3.add(4, 7);
	}

	/**
	 * This method tests MyLinkedList<E> add(), size(), and toArray() methods 
	 * by comparing it to javas built in LinkedList that we know works
	 */
	@Test 
	public void test1()
	{
		MyLinkedList<Integer> integers = new MyLinkedList<>();
		LinkedList<Integer> check = new LinkedList<>(); //javas linked list 
		check.add(4);
		check.add(3);
		check.add(9);
		check.add(11);
		check.add(1);
		check.add(10);
		
		//linearly add things to our linked list 
		for(int i = 0; i < 6; i++)
		{
			integers.add(i, check.get(i));
		}
		
		assertEquals(integers.size(), 6); //check to make sure size is incrementing 
		assertArrayEquals(integers.toArray(), check.toArray());
	}
	
	/**
	 * This method tests MyLinkedList<E> getFirst() and getLast() methods
	 * by verifying the methods throw an exception when trying to access first 
	 * or last elements of an empty list
	 * 
	 * We will also switch to creating a LinkedList to prove our implementations 
	 * ability to work with generics
	 */
	@Test(expected = NoSuchElementException.class) //testing for the thrown exception 
	public void test2()
	{
		MyLinkedList<String> strg = new MyLinkedList<>();
		
		strg.getFirst(); //should throw the exception since we haven't added anything 
		strg.getLast(); //also should throw an exception 
	}
	
	/**
	 * This method tests MyLinkedList<E> getFirst() method
	 * by verifying the method 
	 * 
	 * We will also switch to creating a LinkedList to prove our implementations 
	 * ability to work with generics
	 * 
	 * Here we do not check the exception
	 */
	@Test  
	public void test3()
	{	
		assertEquals("Cool", strg.getFirst());
	}
	
	/**
	 * This method tests MyLinkedList<E> getLast() method
	 * by verifying the method 
	 * 
	 * We will also switch to creating a LinkedList to prove our implementations 
	 * ability to work with generics
	 * 
	 * Here we do not check the exception
	 */
	@Test  
	public void test4()
	{
		assertEquals("Computer", strg.getLast());
	}
	
	/**
	 * This test verifies that the clear() method properly clears the LinkedList<E>
	 * by showing that getLast() will throw an exception when called after calling 
	 * clear on a populated LinkedList<E>
	 */
	@Test(expected = NoSuchElementException.class)  
	public void test5()
	{	
		strg.clear();
		
		strg.getLast(); //should throw an exception since we cleared the list 
	}
	
	/**
	 * This test verifies that the clear() method properly clears the LinkedList<E>
	 * by showing that the new size of the list is 0 when cleared
	 */
	@Test  
	public void test6()
	{	
		strg.clear();
		
		 assertEquals(0, strg.size());
	}
	
	/**
	 * This test verifies that the clear() method properly clears the LinkedList<E>
	 * and tests the isEmpty() method to make sure it returns true when the list is empty 
	 */
	@Test  
	public void test7()
	{
		strg.clear();
		
		assertTrue(strg.isEmpty());
	}
	
	/**
	 * This test verifies the size() method returns the correct number of elements 
	 * in our LinkedList<E> class
	 */
	@Test  
	public void test8()
	{	
		assertEquals(4, strg.size());
	}
	
	/**
	 * Same test as above, but we want to verify size() returns 0 
	 * when the LinkedList is empty 
	 */
	@Test  
	public void test9()
	{
		MyLinkedList<String> strg = new MyLinkedList<>();
		
		assertEquals(0, strg.size());
	}
	
	/**
	 * This test verifies that remove() throws the appropriate exception 
	 * when you try to remove an index that is less than 0
	 */
	@Test(expected = IndexOutOfBoundsException.class)   
	public void test10()
	{
		ints.remove(-1);
	}
	
	/**
	 * This test verifies that remove() throws the appropriate exception 
	 * when you try to remove an index that is greater than the size of 
	 * our actual list 
	 */
	@Test(expected = IndexOutOfBoundsException.class)   
	public void test11()
	{
		
		ints.remove(8);
	}
	
	/**
	 * This test verifies that remove() returns the correct 
	 * element at the specified index and that the element is truly removed
	 * (meaning this also tests the indexOf() method
	 */
	@Test   
	public void test12()
	{
		
		assertTrue(5 == ints.remove(3));
		
		// now verify the size is correct and the element is really gone 
		
		assertEquals(3, ints.size());
		
		assertEquals(-1, ints.indexOf(5)); //the element shouldn't be found since it was removed
	}
	
	/**
	 * This test verifies that removeLast() returns the correct 
	 * element at the specified index and that the element is truly removed
	 * (meaning this also tests the indexOf() method
	 */
	@Test   
	public void test13()
	{
		
		assertTrue(7 == ints2.removeLast());
		
		// now verify the size is correct and the element is really gone 
		
		assertEquals(3, ints2.size());
		
		assertEquals(-1, ints2.indexOf(7)); //the element shouldn't be found since it was removed
	}
	
	/**
	 * This test verifies that removeFirst() returns the correct 
	 * element at the specified index and that the element is truly removed
	 * (meaning this also tests the indexOf() method
	 */
	@Test   
	public void test14()
	{	
		assertTrue(1 == ints2.removeFirst());
		
		// now verify the size is correct and the element is really gone 
		
		assertEquals(3, ints2.size());
		
		assertEquals(-1, ints2.indexOf(1)); //the element shouldn't be found since it was removed
	}
	
	/**
	 * This test verifies that indexOf() returns the correct index of the specified element 
	 */
	@Test   
	public void test15()
	{	
		//test to make sure indexOf() works for every index in the list 
		assertEquals(0, ints2.indexOf(1));
		assertEquals(1, ints2.indexOf(2));
		assertEquals(2, ints2.indexOf(3));
		assertEquals(3, ints2.indexOf(7));
		
	}
	
	/**
	 * This test verifies that lastIndexOf() returns the correct index of 
	 * the first element specified as we traverse through the back of the list 
	 */
	@Test   
	public void test16()
	{
		//the last index of the element 3 in the list should be 3
		assertEquals(3, ints3.lastIndexOf(3));
	}
	
	/**
	 * This test verifies the toArray() method truly returns of a properly 
	 * populated array from our LinkedList<E>
	 */
	@Test   
	public void test17()
	{
		
		Integer[] array = new Integer[5];
		array[0] = 14;
		array[1] = 3;
		array[2] = 6;
		array[3] = 3;
		array[4] = 7;
		
		assertArrayEquals(array, ints3.toArray());
	}
	
	/**
	 * This test verifies the get() method throws the appropraiate exception when 
	 * indexing with a negative number  
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void test18()
	{	
		ints3.get(-1);
	}
	
	/**
	 * This test verifies the get() method throws the appropriate exception when 
	 * indexing with a number that is greatest than the size of the list
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void test19()
	{
		
		ints3.get(ints.size() + 2); //a number that is out of the size 
	}
	
	/**
	 * This test verifies the get() method returns the correct element 
	 * at the specified index
	 */
	@Test
	public void test20()
	{	
		assertTrue(14 == ints3.get(0));
		assertTrue(3 == ints3.get(1));
		assertTrue(6 == ints3.get(2));
		assertTrue(3 == ints3.get(3));
		assertTrue(7 == ints3.get(4));
	}
	
	//------- Testing Complete --------------------------
}
