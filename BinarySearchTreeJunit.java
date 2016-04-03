package assignment8;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Ryan Williams 
 * 
 * The purpose of this J unit is to thoroughly test the BinarySearchTree written by Thuy and I. 
 * This J unit includes tests for all the public methods within our BST class, tests there end cases
 * and all the possibility of return types for various methods. 
 *
 */
public class BinarySearchTreeJunit
{
	BinarySearchTree<Integer> BST;
	BinarySearchTree<Integer> BSTEmpty;
	Collection<Integer> collection; 
	Collection<Integer> nothingSimilar;

	@Before
	public void setUp() throws Exception
	{
		BSTEmpty = new BinarySearchTree<>(); //an empty BST for testing purposes
		BST = new BinarySearchTree<>();
		BST.add(4);
		BST.add(5);
		BST.add(1);
		BST.add(19);
		
		collection = new ArrayList<>();
		collection.add(16);
		collection.add(14);
		
		//notice these two numbers we are adding to the arraylist are already in BST
		collection.add(19);
		collection.add(5);
		
		//this collection has nothing similar to our BST
		nothingSimilar = new ArrayList<>();
		nothingSimilar.add(6);
		nothingSimilar.add(7);
		nothingSimilar.add(3);
		nothingSimilar.add(21);
		
	}

	@Test //verify we return true when we add to the BST
	public void test()
	{
		assertEquals(BST.add(3), true);
	}
	
	@Test //make sure we can't add an item to a BST that already has that item
	public void test2()
	{
		assertEquals(BST.add(4), false);
	}
	
	@Test //Test to make sure size gets increased when added and decreased when removed 
	public void test3()
	{
		assertEquals(4, BST.size());
		BST.remove(5);
		assertEquals(3, BST.size());
	}
	
	@Test //Test the boundary cases for size (0 and 1 items in the BST)
	public void test4()
	{
		BSTEmpty.add(6);
		assertEquals(1, BSTEmpty.size());
		BSTEmpty.remove(6);
		assertEquals(0, BSTEmpty.size());
	}
	
	@Test //Test to make sure add() returns true when it adds the item 
	public void test5()
	{
		BST.add(9);
		assertEquals(true, BST.remove(9));
	}
	
	@Test //Test to verify remove() returns false when it cannot find the item to remove 
	public void test6()
	{
		BST.add(9);
		assertEquals(false, BST.remove(6));
	}
	
	//Test that shows we should get a null pointer exception when we try to remove a null item 
	@Test (expected = NullPointerException.class)
	public void test7()
	{
		BST.add(9);
		BST.remove(null);
	}
	
	//same test as above but removing from an empty BST
	@Test (expected = NullPointerException.class)
	public void test8()
	{
		BSTEmpty.remove(null);
	}
	
	@Test //This test shows remove() returns false when trying to remove an non null item from a tree with nothing in it 
	public void test9()
	{
		assertEquals(false, BSTEmpty.remove(6));
	}
	
	@Test //verify isEmpty() works correctly 
	public void test10()
	{
		assertEquals(true, BSTEmpty.isEmpty());
	}
	
	@Test //verify isEmpty() returns false when the tree is not empty
	public void test11()
	{
		BSTEmpty.add(22);
		assertEquals(false, BSTEmpty.isEmpty());
	}

	//test to make sure we can't add null items
	@Test (expected = NullPointerException.class)
	public void test12()
	{
		BSTEmpty.add(null);
		assertEquals(true, BSTEmpty.isEmpty());
	}
	
	@Test //test to verify clear() is working properly 
	public void test13()
	{
		BST.clear();
		
		assertEquals(0, BST.size());
		assertEquals(true, BST.isEmpty());
	}
	
	@Test //make sure we can recreate the same tree after we clear it
	public void test14()
	{
		BST.clear();
		BST.add(14);
		BST.add(13);
		BST.add(11);
		BST.add(0);
		
		assertEquals(4, BST.size());
	}
	
	@Test //test for generics, we want to verify we can take different data types 
	public void test15()
	{
		BinarySearchTree <String> NewBST = new BinarySearchTree<>();
		
		NewBST.add("Mitsubishi");
		NewBST.add("Nissan");
		NewBST.add("Subaru");
		NewBST.add("STI");
		NewBST.add("Evolution");
		NewBST.add("Skyline");
		
		assertEquals(6, NewBST.size());
	}
	
	@Test //test to make sure first() gets the most far left node 
	public void test16()
	{
		assertEquals(1 , BST.first().intValue());
	}
	
	@Test //test to make sure last() gets the most far right node 
	public void test17()
	{
		assertEquals(19 , BST.last().intValue());
	}
	
	@Test //test to make sure first() returns the root node in a tree with just 1 item
	public void test18()
	{
		BSTEmpty.add(10);
		assertEquals(10, BSTEmpty.first().intValue());
	}
	
	@Test //test to make sure last() returns the root node in a tree with just 1 item
	public void test19()
	{
		BSTEmpty.add(10);
		assertEquals(10, BSTEmpty.last().intValue());
	}
	
	//test to verify an exception is thrown when trying to get the smallest value in an empty tree
	@Test (expected = NoSuchElementException.class) 
	public void test20()
	{
		assertEquals(10, BSTEmpty.first().intValue());
	}
	
	//test to verify an exception is thrown when trying to get the largest value in an empty tree
	@Test (expected = NoSuchElementException.class) 
	public void test21()
	{
		assertEquals(10, BSTEmpty.last().intValue());
	}
	
	@Test //test to show addAll() adds only the non duplicate items in the collection 
	public void test22()
	{
		BST.addAll(collection);
		assertEquals(6, BST.size());
	}
	
	@Test //test to show containsAll() returns true when all the items are in the collection are in the BST; false otherwise 
	public void test23()
	{
		//they are not all in the BST so we should see false 
		assertEquals(false, BST.containsAll(collection));
		
		//if we add them all then it should now return true 
		BST.addAll(collection);
		assertEquals(true, BST.containsAll(collection));
	}
	
	@Test //test to show removeAll() removes all the necessary items from the BST 
	public void test24()
	{
		assertEquals(4, BST.size());
		
		//there are two items similar within the two so only two should be removed
		BST.removeAll(collection);
		assertEquals(2, BST.size());
	}
	
	@Test //test to show removeAll() returns true when it removes one or more items
	public void test25()
	{
		assertEquals(true, BST.removeAll(collection));
	}
	
	@Test //test to show removeAll() returns false when nothing gets removed
	public void test26()
	{
		assertEquals(false, BST.removeAll(nothingSimilar));
	}
	
	@Test //test to show containsAll() returns false when not all the items are in the BST
	public void test27()
	{
		assertEquals(false, BST.containsAll(collection));
	}
	
	@Test //test to show addAll() returns false when nothing is added to the BST 
	public void test28()
	{
		collection.remove(16);
		collection.remove(14);
		
		assertEquals(false, BST.addAll(collection));
	}
	
	@Test //test to show addAll() returns true when nothing something is added to the BST 
	public void test29()
	{	
		assertEquals(true, BST.addAll(collection));
	}
	
	@Test //test to show toArrayList() creates and returns an arrayList in ascending order to due an in order traversal 
	public void test30()
	{	
		ArrayList<Integer> inOrderArray = new ArrayList<>();
		inOrderArray.add(1);
		inOrderArray.add(4);
		inOrderArray.add(5);
		inOrderArray.add(19);
		
		assertEquals(inOrderArray, BST.toArrayList());
	}
	
	//testing complete 
}

