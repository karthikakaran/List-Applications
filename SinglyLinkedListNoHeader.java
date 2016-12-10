import java.util.*;

public class SinglyLinkedListNoHeader<T> implements Iterable<T> {

	/** Class Entry holds a single node of the list */
	public class Entry<T> {
		public T element;
		public Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}
	}

	// Dummy header is used.  tail stores reference of tail element of list
	Entry<T> header, tail;
	int size;

	public SinglyLinkedListNoHeader() {
		header = new Entry<>(null, null);
		tail = header;
		size = 0;
	}

	public Iterator<T> iterator() { return new SLLIterator<>(header); }

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
		// What should cursor be set to after a remove?
		public void remove() {
			prev.next = cursor.next;
			prev = null;
		}
	}

	// Add new elements to the end of the list
	public void add(T x) {
		if (header.element == null)
			header.element = x;
		else {
			tail.next = new Entry<>(x, null);
			tail = tail.next;
		}
		size++;
	}

	void printList() {
		for(T item: this) {
			System.out.print(item + " ");
		}

		System.out.println();
	}

	public static void main(String[] args) {
		int n = 10;
		if(args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		SinglyLinkedListNoHeader<Integer> lst = new SinglyLinkedListNoHeader<>();
		for(int i=1; i<=n; i++) {
			lst.add(new Integer(i));
		}
		lst.printList();
	}
}
