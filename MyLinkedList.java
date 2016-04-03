package assignment6;

import java.util.NoSuchElementException;

/**
 * This class MyLinkedList<E> is a generic class that implements List<E>.
 * MyLinkedList<E> provides the user with a doubly linked list to store, add,
 * remove, and find elements within the list.
 * 
 * @author Ryan Williams and Thuy Nguyen
 * 
 *
 * @param <E>
 */
public class MyLinkedList<E> implements List<E>
{
	// fields
	private int size;
	private Node head;
	private Node tail;

	public MyLinkedList()
	{
		size = 0;
		head = tail = null;
	}
	
	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addFirst(E element)
	{
		if (isEmpty())
		{
			Node node = new Node(null, element, null);
			head = node;
			tail = node;
		}
		else
		{
			Node node = new Node(null, element, head);
			head.previous = node;
			head = node;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addLast(E element)
	{
		if (isEmpty())
		{
			Node node = new Node(null, element, null);
			head = node;
			tail = node;
		}
		else
		{
			Node node = new Node(tail, element, null);
			tail.next = node;
			tail = node;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}

		if (index == 0)
		{
			addFirst(element);
		}
		else if (index == size)
		{
			addLast(element);
		}
		else
		{

			Node current = getTargetNode(index);

			Node node = new Node(current.previous, element, current);
			current.previous.next = node;
			current.previous = node;
			size++;
		}
	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		return head.data;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0)
		{
			return head.data;
		}
		else if(index == size - 1)
		{
			return tail.data;
		}
		else
		{
			Node current = getTargetNode(index);

			return current.data;
		}
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		E data = head.data;

		if (size == 1)
		{
			clear();
			return data;
		}

		head = head.next;
		head.previous = null;

		size--;
		return data;
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		E data = tail.data;

		if (size == 1)
		{
			clear();
			return data;
		}

		tail = tail.previous;
		tail.next = null;

		size--;

		return data;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}

		if (index == 0)
		{
			return removeFirst();
		}
		else if (index == size - 1)
		{
			return removeLast();
		}
		else
		{

			Node current = getTargetNode(index);
			current.previous.next = current.next;
			current.next.previous = current.previous;

			size--;
			return current.data;
		}

	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int indexOf(E element)
	{
		Node current = head;
		for (int i = 0; i < size; i++)
		{
			if (element.equals(current.data))
			{
				return i;
			}
			current = current.next;
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element)
	{
		Node current = tail;
		for (int i = size - 1; i >= 0; i--)
		{
			if (element.equals(current.data))
			{
				return i;
			}
			current = current.previous;
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray()
	{
		E[] array = (E[]) new Object[size];

		Node current = head;
		int index = 0;
		while (index < size)
		{
			array[index] = current.data;
			current = current.next;
			index++;
		}

		return array;
	}

	
	// private helper methods and class
	/**
	 * Returns the node at the index passed in
	 */
	private Node getTargetNode(int index)
	{
		Node current = head;
		for (int i = 0; i < index; i++)
		{
			current = current.next;
		}

		return current;
	}


	/**
	 * The Node class represents a node in the MyLinkedList class, storing
	 * data, and pointers to the next and previous neighboring nodes.
	 * 
	 * @author Thuy Nguyen and Ryan Williams
	 *
	 */
	private class Node
	{
		E data;
		Node next, previous;

		public Node(Node previous, E data, Node next)
		{
			this.data = data;
			this.previous = previous;
			this.next = next;
		}

		public Node()
		{

		}
	}
}
