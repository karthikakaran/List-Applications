//===========================================================================================================================
//	Program : To reverse print and reverse the linked list
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//	SinglyLinked List source:Dr.Balaji Ragahavachari
//===========================================================================================================================

import java.util.Iterator;

public class ReverseSLL<T> implements Iterable<T> {
	/** Class Entry holds a single node of the list */
	public class Entry<T> {
		T element;
		Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}
	}

	// Dummy header is used. tail stores reference of tail element of list
	Entry<T> header, tail;
	int size;

	ReverseSLL() {
		header = new Entry<>(null, null);
		tail = null;
		size = 0;
	}

	public Iterator<T> iterator() {
		return new SLLIterator<>(header);
	}

	private class SLLIterator<E> implements Iterator<E> {
		Entry<E> cursor, prev;

		SLLIterator(Entry<E> head) {
			cursor = head;
			prev = null;
		}

		public boolean hasNext() {
			return cursor.next != null;
		}

		public E next() {
			prev = cursor;
			cursor = cursor.next;
			return cursor.element;
		}

		// To do: error checking
		public void remove() {
			prev.next = cursor.next;
			prev = null;
		}
	}

	// Add new elements to the end of the list
	void add(T x) {
		if (tail == null) {
			header.next = new Entry<>(x, header.next);
			tail = header.next;
		} else {
			tail.next = new Entry<>(x, null);
			tail = tail.next;
		}
		size++;
	}

	void printList() {
		for (T item : this) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
	/** Procedure To reverse list using recursive function
	 * Runs in time O(n) where n is the size list
	 * @variable  head: Entry : head pointer to th list
	 * @variable  tmp: Entry : helper to traverse list 
	 * @variable  t: Entry : helper to traverse list
	 */
	Entry<T> reverseRecursive(Entry<T> head) {
		if (head == null) return null;
		if (head.next == null) return head;

		Entry<T> tmp = head.next;
		head.next = null;

		Entry<T> t = reverseRecursive(tmp);
		tmp.next = head;

		this.header.next = t;
		return t;

	}
	/** Procedure To print in reverse order iteratively
	 * Runs in time O(n) where n is the size list
	 * @variable  head: Entry : head pointer to th list
	 * @variable  ptr: Entry : helper to traverse list 
	 * @variable  tmp: Entry : helper to traverse list
	 */
	void reverseIterative() {

		Entry<T> head = header.next;
		Entry<T> ptr = head.next;
		Entry<T> tmp;

		head.next = null;
		while(ptr != null) {
			tmp = ptr.next;
			ptr.next = head;
			head = ptr;
			ptr = tmp;
		}
		header.next = head;
	}
	/** Procedure To print in reverse order using recursive function
	 * Runs in time O(n) where n is the size list
	 * @variable  head: Entry : head pointer to th list
	 */
	void printReverseRecursive(Entry<T> head) {
		if (head == null)
			return;
		printReverseRecursive(head.next);
		System.out.print(head.element + " ");
	}

	public static void main(String[] args) {
		int n = 10;

		ReverseSLL<Integer> lst = new ReverseSLL<>();
		for (int i = 1; i <= n; i++) {
			lst.add(new Integer(i));
		}
		System.out.println("Print reverse recursively ::");
		lst.printReverseRecursive(lst.header.next);
		System.out.println();


		System.out.println("Print reverse iteratively ::");
		//Reused the reverse iterative function for printing in reverse iteratively
		//and rever the list again after printing, so that it runs in O(n)
		lst.reverseIterative();
		lst.printList();
		lst.reverseIterative();
		System.out.println();

		System.out.println("Reverse recursively ::");
		lst.reverseRecursive(lst.header.next);
		lst.printList();
		System.out.println();

		System.out.println("Reverse iteratively ::");
		lst.reverseIterative();
		lst.printList();
		System.out.println();
	}
}
