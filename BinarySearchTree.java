package assignment8;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>
{
	private int size;
	private Node root;
	
	public BinarySearchTree()
	{
		size = 0;
		root = null;
	}

	/**
	   * Ensures that this set contains the specified item.
	   * 
	   * @param item
	   *          - the item whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if the input item was actually inserted); otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	@Override
	public boolean add(Type item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		
		//when adding to an empty tree, the first item added becomes the root
		if(isEmpty())
		{
			root = new Node(item);//create the root and give it an item
			size++;
			return true;
		}
		
		boolean hasAdded = add(root, item); //call to the helper method 
		
		if(hasAdded)
		{
			size++;
		}
		
		return hasAdded;
	}
	
	/**
	   * Private helper method for add
	   * 
	   * @param item
	   *          - the item whose presence is ensured in this set
	   *        current
	   *          - the node we are currently on 
	   * @return true if this set changed as a result of this method call (that is,
	   *         if the input item was actually inserted); otherwise, returns false
	   * 
	   */
	private boolean add(Node current, Type item)
	{
		//when the item we are adding is less than the current node
		//we must add if the current nodes left pointer is null
		//other wise we need to continue traversing using recursion
		//until we find the correct place to add the node and its item
		if(item.compareTo(current.data) < 0)
		{
			if(current.left == null)
			{
				current.left = new Node(item);
				return true;
			}
			else
			{
				return add(current.left, item);
			}
		} 
		//same case, but for items that are greater than the current node
		else if(item.compareTo(current.data) > 0) 
		{
			if(current.right == null)
			{
				current.right = new Node(item);
				return true;
			}
			else
			{
				return add(current.right, item);
			}
		}
		
		return false; //if we never find a place to place it we return false (duplicates will cause this)
	}

	 /**
	   * Ensures that this set contains all items in the specified collection.
	   * 
	   * @param items
	   *          - the collection of items whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if any item in the input collection was actually inserted);
	   *         otherwise, returns false
	   * @throws NullPointerException
	   *           if any of the items is null
	   */
	@Override
	public boolean addAll(Collection<? extends Type> items)
	{
		boolean changed = false;
		
		for(Type item: items)
		{
			changed |= add(item); //if we add at least one item we will return true, signifying we have changed the BST
		}
		
		return changed;
	}

	/**
	   * Removes all items from this set. The set will be empty after this method
	   * call.
	   */
	@Override
	public void clear()
	{
		// by making the root null, the tree is no longer existent 
		// due to the fact that all nodes are accessed through the root
		root = null; 
		size = 0;
	}

	/**
	   * Determines if there is an item in this set that is equal to the specified
	   * item.
	   * 
	   * @param item
	   *          - the item sought in this set
	   * @return true if there is an item in this set that is equal to the input
	   *         item; otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	@Override
	public boolean contains(Type item)
	{
		if (item == null) //adding null items to a BST is not allowed 
		{
			throw new NullPointerException();
		}
		
		return contains(root, item); //call to the helper method 
	}
	
	/**
	   * Private helper method for contains to determine if there is an item in this set 
	   * that is equal to the specified item.
	   * 
	   * @param item
	   *          - the item sought in this set
	   *        current
	   *          - the node we are currently on in our tree
	   * @return true if there is an item in this set that is equal to the input
	   *         item; otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	private boolean contains(Node current, Type item)
	{
		//once we have traversed the whole BST, eventually we will meet a null node
		//meaning we never found the item; therefore false 
		if(current == null)
		{
			return false;
		}
		
		//if compareTo() returns zero we know we have found it; therefore true
		if(item.compareTo(current.data) == 0)
		{
			return true;
		}
		
		//if compareTo() returns < 0 then we know the node we are 
		//searching for is to the left subtree; else it is to the right
		//we keep recursively traversing until we find it 
		if(item.compareTo(current.data) < 0)
		{
			return contains(current.left, item);
		}
		else 
		{
			return contains(current.right, item);
		}
	}

	/**
	   * Determines if for each item in the specified collection, there is an item
	   * in this set that is equal to it.
	   * 
	   * @param items
	   *          - the collection of items sought in this set
	   * @return true if for each item in the specified collection, there is an item
	   *         in this set that is equal to it; otherwise, returns false
	   * @throws NullPointerException
	   *           if any of the items is null
	   */
	@Override
	public boolean containsAll(Collection<? extends Type> items)
	{
		for (Type item : items)
		{
			if (!contains(item)) //if at once we don't find an item in the BST, then containsAll() is false; otherwise true
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	   * Returns the first (i.e., smallest) item in this set.
	   * 
	   * @throws NoSuchElementException
	   *           if the set is empty
	   */
	@Override
	public Type first() throws NoSuchElementException
	{
		if(isEmpty()) //no such thing of a smallest item in an empty BST
		{
			throw new NoSuchElementException();
		}
		
		return smallest(root); //call to helper method
	
	}
	
	/**
	 * Private helper method for first(), returning the smallest (farthest most left leaf) node
	 * 
	 * @param current
	 * 			-the node we are currently on in the tree
	 * 
	 * @return the data at the smallest node
	 */
	private Type smallest(Node current)
	{
		//once we encounter a left node that points to null
		//we know we have traversed as far left as possible 
		//we then return the data at that far left node
		if(current.left == null)
		{
			return current.data;
		}
	
			return smallest(current.left); //traverse the tree recursively 
	}

	/**
	   * Returns true if this set contains no items.
	   */
	@Override
	public boolean isEmpty()
	{
		return root == null;
	}

	/**
	   * Returns the last (i.e., largest) item in this set.
	   * 
	   * @throws NoSuchElementException
	   *           if the set is empty
	   */
	@Override
	public Type last() throws NoSuchElementException
	{
		if (isEmpty()) //no such thing of a largest item in an empty BST
		{
			throw new NoSuchElementException();
		}
		
		return largest(root); //call to helper method 
	}
	
	/**
	 * Private helper method for last(), to find the largest (farthest most right leaf) node
	 * 
	 * @param current
	 * 			-the node we are currently on in the tree 
	 * @return the data at the largest node
	 */
	private Type largest(Node current)
	{
		//once we encounter a right node that points to null
		//we know we have traversed as far right as possible 
		//we then return the data at that node
		if (current.right == null)
		{
			return current.data;
		}
		
		return largest(current.right); //traverse the tree recursively until we find a null pointer 
	}

	/**
	   * Ensures that this set does not contain the specified item.
	   * 
	   * @param item
	   *          - the item whose absence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if the input item was actually removed); otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	@Override
	public boolean remove(Type item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		
		
		boolean hasRemoved = remove(root, root, item); //call to remove helper method
		
		if (hasRemoved) //if we remove something, the size of the BST decreases 
		{
			size--;
		}
		
		return hasRemoved;
	
	}
	
	/**
	 * Private helper method for remove -
	 * Recursively finds the node to be removed, then removes that node according to 3 cases:
	 * 1. the node to be removed is a leaf node
	 * 2. the node to be removed is a parent node with one child
	 * 3. the node to be removed is a parent node with two children
	 * 
	 * @param current
	 * 			-the node we are currently on in the tree 
	 * @param parent
	 * 			-the parent node of the node we want to remove
	 * @param item
	 * 			-the item contained in the node we want to remove 
	 * @return true if this set changed as a result of this method call (that is,
	 *         if the input item was actually removed); otherwise, returns false
	 */
	private boolean remove(Node current, Node parent, Type item)
	{
		//signifies that we have traversed the whole tree and did not find the item to be removed 
		if(current == null)
		{
			return false;
		}
		//traverse to the left if the item is less than the current node 
		if(item.compareTo(current.data) < 0)
		{
			return remove(current.left, current, item);
		}
		//traverse to the right if the item is greater than the current node
		else if(item.compareTo(current.data) > 0)
		{
			return remove(current.right, current, item);
		}
		//if its not less than or greater than, we know we have found it 
		else 
		{
			//case: root
			if(current == root)
			{
				removeRoot();
			}
			else
			{
				parent.removeChild(current);
			}
			
			return true;
		}
	}
	
	/**
	 * Private helper method to remove a node that has two children
	 * 
	 * @param current
	 * 			-the current node we are on in the tree 
	 */
	private void removeWithTwoChildren(Node current)
	{
		//place the smallest value of the right subtree in the root
		//and then remove the node whose data we just replaced 
		current.data = smallest(current.right); 
		remove(current.right, current, current.data);
	}

	/**
	   * Ensures that this set does not contain any of the items in the specified
	   * collection.
	   * 
	   * @param items
	   *          - the collection of items whose absence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if any item in the input collection was actually removed);
	   *         otherwise, returns false
	   * @throws NullPointerException
	   *           if any of the items is null
	   */
	@Override
	public boolean removeAll(Collection<? extends Type> items)
	{
		boolean changed = false;
		for (Type item : items)
		{
			changed |= remove(item); //if we remove just one item, we return true, signifying it has changed; otherwise false 
		}
		
		return changed;
	}

	/**
	   * Returns the number of items in this set.
	   */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Private helper method to remove the root 
	 */
	private void removeRoot()
	{
		//if a root has no children we can assume it is the only thing in the BST
		//to remove it we just make it null, signifying the tree is now empty
		if(root.isLeaf())
		{
			root = null;
		}
		
		//if it only has a left child we replace the root with that child 
		else if(root.right == null)
		{
			root = root.left;
		}
		//if it only has a right child we replace the root with that child 
		else if(root.left == null)
		{
			root = root.right;
		}
		else
		{
			//if the root has two children we make a call to our helper method
			removeWithTwoChildren(root);
		}
	}
	
	/**
	   * Returns an ArrayList containing all of the items in this set, in sorted
	   * order.
	   */
	@Override
	public ArrayList<Type> toArrayList()
	{
		ArrayList<Type> array = new ArrayList<>();
		toArrayList(array, root); //call to helper method
		return array;
	}
	
	/**
	 * Private helper method for toArrayList()
	 * This method implements an in order tree traversal, meaning that our 
	 * array will be populated with items in ascending order 
	 * 
	 * @param array
	 * 			-the ArrayList we are populating, with the items in our BST
	 * @param current
	 * 			-the node we are currently on in the tree 
	 */
	private void toArrayList(ArrayList<Type> array, Node current)
	{
		//if it has a left child then we know we need to continue traversing before we add it 
		if(current.left != null)
		{
			toArrayList(array, current.left);
		}
		
		//once we are as far left as possible we begin adding as we come back up 
		array.add(current.data);
		
		//if it has a right child then we know we need to traverse right
		if(current.right != null)
		{
			toArrayList(array, current.right);
		}
		
	}

	/**
	 * Writes a .dot file, in order to graphically view our trees
	 * 
	 * @param filename
	 * 			-the name of the dot file to be created 
	 */
	public void writeDot(String filename)
	{
		try 
		{
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			output.println("graph g {");
			if(root != null)
				output.println(root.hashCode() + "[label=\"" + root + "\"]");
			writeDotRecursive(root, output);
			output.println("}");
			output.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * Private helper function that recursively traverse the tree, outputting
	 * each node to the .dot file
	 * 
	 * @param n
	 * 			-the node to be printed 
	 * @param output
	 * 			-reference to PrintWriter object
	 * @throws Exception
	 */
	private void writeDotRecursive(Node n, PrintWriter output) throws Exception
	{
		if(n == null)
			return;
		if(n.left != null)
		{
			output.println(n.left.hashCode() + "[label=\"" + n.left + "\"]");
			output.println(n.hashCode() + " -- " + n.left.hashCode());
		}
		if(n.right != null)
		{
			output.println(n.right.hashCode() + "[label=\"" + n.right + "\"]");
			output.println(n.hashCode() + " -- " + n.right.hashCode());
		}

		writeDotRecursive(n.left, output);
		writeDotRecursive(n.right, output);
	}

	/**
	 * 
	 * @author Ryan Williams and Thuy Nguyen 
	 * 
	 * This class represents a node in our BST
	 * Every node within the BST contains a left and a right pointer 
	 * to another node (or null if there is no nodes below it)
	 * 
	 * This class also contains local methods that tells the current node about 
	 * the nodes around it (i.e. isLeaf())
	 * 
	 * It also contains methods that help remove nodes depending where they are in terms of 
	 * the current node (i.e. replaceChild())
	 *
	 */
	private class Node
	{
		Type data;
		Node left, right;

		Node(Type data)
		{
			this.data = data;
		}
		
		/**
		 * Converts data to a string with concatenation 
		 */
		public String toString()
		{
			return data +"";
		}
		
		/**
		 * Determines whether the current node is a leaf or not
		 * 
		 * @return true if the current node has no children; otherwise false
		 */
		boolean isLeaf()
		{
			//if a node isn't pointing to a left and a right child, then we know it is a leaf; otherwise false 
			 return (left == null && right == null);
		}
		
		/**
		 * Determines if the node is a left child of a parent node 
		 * @param node
		 * 			-the node check whether it is a left child or not
		 * @return true if it is a left child; otherwise false
		 */
		boolean isLeftChild(Node node)
		{
			return node == left;
		}
		
		/**
		 * Determines if the node is a right child of a parent node 
		 * @param node
		 * 			-the node check whether it is a right child or not
		 * @return true if it is a right child; otherwise false
		 */
		boolean isRightChild(Node node)
		{
			return node == right;
		}
		
		/**
		 * Removes either a left or a right child node 
		 * 
		 * @param child
		 * 			-the child node to be removed
		 */
		void removeChild(Node child)
		{

			if (child.isLeaf())
			{
				deleteChild(child);
			}
			else if (child.left == null)
			{
				replaceChild(child, child.right);
			}
			else if (child.right == null)
			{
				replaceChild(child, child.left);
			}
			else
			{
				removeWithTwoChildren(child);
			}
		}
		
		/**
		 * Replaces a parent node with its child node 
		 * 
		 * @param oldChild
		 * 			-the node to be replaced
		 * @param newChild
		 * 			-the node that will be taking the replaced nodes place
		 */
		void replaceChild(Node oldChild, Node newChild)
		{
			//determine whether the node we are replacing is a left child 
			//or a right child, and then replace the pointer 
			if(isLeftChild(oldChild))
			{
				left = newChild;
			}
			else if(isRightChild(oldChild))
			{
				right = newChild;
			}
		}
		
		void deleteChild(Node child)
		{
			if(isLeftChild(child))
			{
				left = null;
			}
			else if(isRightChild(child))
			{
				right = null;
			}
		}
	}
}
