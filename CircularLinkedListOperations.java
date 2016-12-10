//===========================================================================================================================
//	Program : To merge circular linked list with another circular list using indexing 
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//===========================================================================================================================
import java.util.HashMap;
import java.util.Iterator;

public class CircularLinkedListOperations extends CircularLinkedList {

	HashMap<Integer, Entry> ht = new HashMap<Integer, Entry>();
	/** Procedure to create index for the circular linked list.
	 * Runs in time O(n) where n is the size of the first circular linked list 
	 * in order create index of all n elements
	 * If l1,l2 are not sorted, behavior of procedure is arbitrary
	 * @variable head: Entry : header node of the circular list  
	 * @variable prev : Entry : helper node to get the reference value
	 * @variable it : Iterator : iterator on the list 
	 */
	public void createIndex(){
		Entry head = this.header.next;
		Entry prev = this.header;

		//Hash indexing the element and it previous element address
		Iterator it = this.iterator();
		while (it.hasNext()) {
			this.ht.put((Integer) it.next(), prev);
			prev = head;
			head = head.next;
		}
	}
	/** Procedure to merge the second circular linked list in appropriate position.
	 * Runs in time O(1) as the position to insert is fetched from the index  
	 * If l1,l2 are not sorted, behavior of procedure is arbitrary
	 * @param indexEntry : index to merge
	 * @param inpCircLst : the second list to merge
	 * @variable temp: Entry : temporary node to help swapping  
	 */
	public void mergeList(Entry indexEntry, CircularLinkedListOperations inpCirLst) {
		//Merging the new input circular list to existing list
		Entry temp = indexEntry.next;
		indexEntry.next = inpCirLst.header.next;
		inpCirLst.tail.next = temp;
	}

	public static void main(String[] args) {
		int n = 10;

		CircularLinkedListOperations cirLst = new CircularLinkedListOperations();
		for(int i=1; i<=n; i++) {
			cirLst.add(new Integer(i));
		}
		System.out.println("Given circular list :: ");
		cirLst.printList();

		//Input list to merge
		CircularLinkedListOperations inpCirLst = new CircularLinkedListOperations();
		for(int i=4; i<=15; i++) {
			inpCirLst.add(new Integer(i));
		}
		System.out.println("Second circular list :: ");
		inpCirLst.printList();
		cirLst.createIndex();
		Integer firstElement = inpCirLst.header.next.element;
		Entry indexEntry = cirLst.ht.get(firstElement);
		if (indexEntry == null) {
			System.err.println("Cannot merge");
			System.exit(0);
		}
		cirLst.mergeList(indexEntry, inpCirLst);
		System.out.println("Final circular list :: ");
		cirLst.printList();
	}
}
