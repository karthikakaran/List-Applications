//===========================================================================================================================
//	Program : Circular linked list base class for creating new list and adding elements
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//===========================================================================================================================
import java.util.*;

public class CircularLinkedList implements Iterable<Integer> {

	/** Class Entry holds a single node of the list */
	public class Entry {
		public Integer element;
		public Entry next;

		Entry(Integer x, Entry nxt) {
			element = x;
			next = nxt;
		}
	}

	// Dummy header is used.  tail stores reference of tail element of list
	Entry header, tail;
	int size;

	public CircularLinkedList() {
		header = new Entry(null, null);
		tail = header;
		size = 0;
	}

	public Iterator<Integer> iterator() { return new SLLIterator(header); }

	private class SLLIterator implements Iterator<Integer> {
		Entry cursor, prev;
		SLLIterator(Entry head) {
			cursor = head;
			prev = null;
		}

		public boolean hasNext() {
			if (cursor != tail)
				return cursor.next != null;
			else
				return false;
		}

		public Integer next() {
			prev = cursor;
			cursor = cursor.next;
			return cursor.element;
		}
	}

	// Add new elements to the end of the list
	public void add(Integer x) {
		tail.next = new Entry(x, null);
		tail = tail.next;
		tail.next = header;
		size++;
	}

	void printList() {
		for(Object item: this) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}
