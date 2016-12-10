//===========================================================================================================================
//	Program : To unzip the given list at the given value of k
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
// 	SinglyLinked List source:Dr.Balaji Ragahavachari
//===========================================================================================================================


import java.util.*;

public class SinglyLinkedList<T> implements Iterable<T> {

	/** Class Entry holds a single node of the list */
	public class Entry<T> {
		T element;
		Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}
	}

	// Dummy header is used.  tail stores reference of tail element of list
	Entry<T> header, tail;
	int size;
	private static Scanner in;

	SinglyLinkedList() {
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
	void add(T x) {
		tail.next = new Entry<>(x, null);
		tail = tail.next;
		size++;
	}

	void printList() {
		/* Code without using implicit iterator in for each loop:

        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
		 */

		for(T item: this) {
			System.out.print(item + " ");
		}

		System.out.println();
	}
	void multiUnzip(int k) {
		// Rearrange elements of a singly linked list by chaining
		// together elements that are k apart.  k=2 is the unzip
		// function discussed in class.  If the list has elements
		// 1..10 in order, after multiUnzip(3), the elements will be
		// rearranged as: 1 4 7 10 2 5 8 3 6 9.  Instead if we call
		// multiUnzip(4), the list 1..10 will become 1 5 9 2 6 10 3 7 4 8.
		//==============================================================================================
		//Invariants : buckets[i] is the tail of the elements with i%k th index
		//			   dummyBuckets is the head of the elements with i%k th index
		//			   ptr indicates the current element to be processed
		//			   state indicates the state of the finite state machine
		//			   state takes the values 0,...k-1 
		//			   state = i means that the element is added to the tail of the ith bucket
		//==============================================================================================
		Entry<T>[]buckets=new Entry[k];
		int state=0;
		Entry<T> ptr=header.next;
		Entry<T>[] dummybuckets=new Entry[k];
		ptr=header.next;
		while(state<k)
		{
			dummybuckets[state]=ptr;
			buckets[state]=ptr;
			ptr=ptr.next;
			state++;
		}
		state=0;
		while(ptr!=null) {
			buckets[state].next=ptr;
			ptr=ptr.next;
			buckets[state]=buckets[state].next;
			state++;
			if(state==k)
				state=0;
		}
		for(int i=0;i<k;i++) {
			buckets[i].next=null;
			if(i<k-1)
				buckets[i].next=dummybuckets[i+1];
		}
	}
	public static void main(String[] args) {
		int n = 10;
		if(args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
		for(int i=1; i<=n; i++) {
			lst.add(new Integer(i));
		}
		lst.printList();
		System.out.println("Enter the k value");
		in = new Scanner(System.in);
		int k=in.nextInt();
		lst.multiUnzip(k);
		System.out.println("After UnZipping");
		lst.printList();
	}
}
