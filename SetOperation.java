//===========================================================================================================================
//	Program : To find the union ,difference and intersection of two sets given as sorted lists
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//	SinglyLinked List source:Dr.Balaji Ragahavachari
//===========================================================================================================================

import java.util.Iterator;
import java.util.List;

public class SetOperation {
	/** Procedure to intersection of two sets stored as linked lists
	 * Runs in time O(n) where n is the size of the shortest list
	 *
	 * @param l1: SinglyLinkedList : T. Precondition : Sorted
	 * @param l2: SinglyLinkedList : T. Precondition : Sorted
	 * If l1,l2 are not sorted, behavior of procedure is arbitrary
	 * @param outlist: SinglyLinkedList : stores the common elements from the processed part of l1 and l2 
	 * @param it1 : Iterator : iterator on the list l1
	 * @param it2 : Iterator : iterator on the list l2
	 * @param  previous: T :stores the tail of outList
	 * @param  x: T : current element being processed in list l1
	 * @param  y: T : current element being processed in list l2
	 */
	public static<T extends Comparable<? super T>>
	void intersect(SinglyLinkedList<T> l1, SinglyLinkedList<T> l2, SinglyLinkedList<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2=l2.iterator();
		T x;
		T y;
		x=it1.next();
		y=it2.next();
		T previous=null;
		while(it1.hasNext()&&it2.hasNext()) {
			if(x.compareTo(y)==0){
				if((previous!=null&&previous.compareTo(x)!=0)||previous==null){
					outList.add(x);
					previous=x;
					x=it1.next();
					y=it2.next();
				}
			}
			else if(x.compareTo(y)>=0){
				y=it2.next();
			}
			else{
				x=it1.next();
			}
		}
		if(it1.hasNext()){
			if(x.compareTo(y)==0) {
				outList.add(x);

			} 
		}
		if(it2.hasNext()){
			if(x.compareTo(y)==0) {
				outList.add(y);
			}		  
		}
	}
	/** Procedure to difference of two sets stored as linked lists
	 * Runs in time O(n) where n is the size of the list l1
	 *
	 * @param l1: SinglyLinkedList : T. Precondition : Sorted
	 * @param l2: SinglyLinkedList : T. Precondition : Sorted
	 * If l1,l2 are not sorted, behavior of procedure is arbitrary
	 * @param outlist: SinglyLinkedList : stores the elements from l1 that are not in l2
	 * @param it1 : Iterator : iterator on the list l1
	 * @param it2 : Iterator : iterator on the list l2
	 * @param  previous: T :stores the tail of outList
	 * @param  x: T : current element being processed in list l1
	 * @param  y: T : current element being processed in list l2
	 * @param  z: int : holds the result of comparing an element from l1 with an element from l2
	 */
	public static<T extends Comparable<? super T>>
	SinglyLinkedList<T> difference(SinglyLinkedList<T> l1, SinglyLinkedList<T> l2, SinglyLinkedList<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2=l2.iterator();
		T x;
		T y;
		int z;
		x=it1.next();
		y=it2.next();
		T previous=null; 
		while(it1.hasNext()){
			z=x.compareTo(y);
			if((z<0&&previous==null)||(z<0&&previous.compareTo(x)!=0)) {
				outList.add(x);
				previous=x;
				x=it1.next();
			}
			else if(z>0)
				y=it2.next();
			else {
				x=it1.next();
				y=it2.next();
			}
		}
		return outList;
	}
	/** Procedure to union of two sets stored as linked lists
	 * Runs in time O(n) where n is the size of the longest list
	 *
	 * @param l1: SinglyLinkedList : T. Precondition : Sorted
	 * @param l2: SinglyLinkedList : T. Precondition : Sorted
	 * If l1,l2 are not sorted, behavior of procedure is arbitrary
	 * @param outlist: SinglyLinkedList : stores the distinct elements from processed part of l1 and l2
	 * @param it1 : Iterator : iterator on the list l1
	 * @param it2 : Iterator : iterator on the list l2
	 * @param  previous: T :stores the tail of outList
	 * @param  x: T : current element being processed in list l1
	 * @param  y: T : current element being processed in list l2
	 * @param  z: int : holds the result of comparing an element from l1 with an element from l2
	 */
	public static<T extends Comparable<? super T>>
	SinglyLinkedList<T> union(SinglyLinkedList<T> l1, SinglyLinkedList<T> l2, SinglyLinkedList<T> outList) {	
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2=l2.iterator();
		T x;
		T y;
		x=it1.next();
		y=it2.next();
		T previous=null;
		int z;
		while(it1.hasNext()&&it2.hasNext()) {
			z=x.compareTo(y);
			if(z<0) {
				if(previous==null||previous.compareTo(x)!=0) {
					outList.add(x);
					previous=x;
				}
				x=it1.next();
			}
			else if(z>0) {
				if(previous==null||previous.compareTo(y)!=0) {
					outList.add(y);
					previous=y;
				}
				y=it2.next();
			}
			else {
				if(previous==null||previous.compareTo(y)!=0) {
					outList.add(y);
					previous=y;
				}
				y=it2.next();
				x=it1.next();
			}
		}
		while(it1.hasNext()) {
			if(previous==null||previous.compareTo(x)!=0) {
				outList.add(x);
				previous=x;
			}
			x=it1.next();	 
		}
		while(it2.hasNext()) {
			if(previous==null||previous.compareTo(y)!=0) {
				outList.add(y);
				previous=y;
			}
			y=it2.next();	 
		}
		if(previous==null||previous.compareTo(x)!=0) 
			outList.add(x);
		if(previous==null||previous.compareTo(y)!=0) 
			outList.add(y);
		return outList; 
	}
	public static void main(String[] args){
		SinglyLinkedList<Integer> list1=new SinglyLinkedList<>();
		SinglyLinkedList<Integer> list2=new SinglyLinkedList<>();
		int n1=1000;
		int n2=1000;
		if(args.length>0) {
			n1=Integer.parseInt(args[0]);
			if(args.length>1)
				n2=Integer.parseInt(args[1]);
		}
		for(int i=0;i<n1;i++){
			list1.add(new Integer(2*i));
		}
		for(int i=0;i<n2;i++) {
			list2.add(new Integer(3*i));
		}
		Timer timer = new Timer();
		timer.start();
		SinglyLinkedList<Integer> output=new SinglyLinkedList<>();
		intersect(list1,list2,output);
		System.out.println("Intersection of two sets:");
		output.printList();
		output=new SinglyLinkedList<>();
		output=difference(list1,list2,output);
		System.out.println("Difference of two sets:");
		output.printList();
		output=new SinglyLinkedList<>();
		output=union(list1,list2,output);
		System.out.println("Union of two sets:");
		output.printList();
		timer.end();
		System.out.println(timer); 
	}

}
