//===========================================================================================================================
//	Program : To add and subtract two Large Integers of a given base stored as separate lists 
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//===========================================================================================================================

import java.util.*;

public class LargeIntegers {
	/** Procedure to addition of two Large Integers stored as list in reverse.
	 * Runs in time O(n) where n is the size of the longest list 
	 *
	 * @param x: LinkedList : Integer. Precondition : Digits of the list < base
	 * @param y: LinkedList : Integer. Precondition : Digits of the list < base
	 * If x,y have digits greater than base, behavior of procedure is arbitrary
	 * @param z: LinkedList : Integer : stores the result of the adding l1 and l2 
	 * @object it1 : Iterator : iterator on the list l1
	 * @object it2 : Iterator : iterator on the list l2
	 * @object  a: Integer : current element being processed in list l1
	 * @object  b: Integer : current element being processed in list l2
	 * @variable  sum: int : holds the result of adding current element of l1 with current element of l2
	 * @variable  carry: int : holds the overflow if sum>base
	 * @param  base: int : base in which the given integers are represented
	 */
	public static <T> void add(List<Integer> x,List<Integer>  y,List<Integer> z, int base) {
		int carry=0;
		int sum;
		Iterator<Integer> it1 = x.iterator();
		Iterator<Integer> it2 = y.iterator();
		boolean h1=it1.hasNext();
		boolean h2=it2.hasNext();
		Integer a=it1.next();
		Integer b=it2.next();
		while(h1&&h2) {
			sum=a+b+carry;
			z.add(sum%base);
			if(sum/base>=1)
				carry=1;
			else
				carry=0;
			h1=it1.hasNext();
			h2=it2.hasNext();
			if(h1)
				a=it1.next();
			if(h2)
				b=it2.next();
		}
		while(h1)
		{
			sum=a+carry;
			z.add(sum%base);
			if(sum/base>=1)
				carry=1;
			else
				carry=0;
			h1=it1.hasNext();
			if(h1)
				a=it1.next();
		}
		while(h2)
		{
			sum=b+carry;
			z.add(sum%base);
			if(sum/base>=1)
				carry=1;
			else
				carry=0;
			h2=it2.hasNext();
			if(h2)
				b=it2.next();
		}
		if(carry==1)
			z.add(1);
	}
	/** Procedure to subtraction of two Large Integers stored as lists in reverse.
	 * Runs in time O(n) where n is the size of the first list.
	 *
	 * @param x: LinkedList : Integer. Precondition : Digits < base
	 * @param y: LinkedList : Integer. Precondition : Digits < base
	 * If x,y have digits greater than base or if l1 < l2, behavior of procedure is arbitrary
	 * @param z: LinkedList : Integer : stores the result of the adding l1 and l2 
	 * @objectit1 : Iterator : iterator on the list l1
	 * @object it2 : Iterator : iterator on the list l2
	 * @object  a: Integer : current element being processed in list l1
	 * @object  b: Integer : current element being processed in list l2
	 * @variable  difference: int : holds the result of subtracting current element of l1 with current element of l2
	 * @variable  borrow : int : 1 if current element of l1 < current element of l2
	 * @param  base: int : base in which the given integers are represented
	 */

	public static void subtract(List<Integer> x, List<Integer>  y,List<Integer> z, int base) {
		int borrow=0;
		int difference;
		Iterator<Integer> it1 = x.iterator();
		Iterator<Integer> it2 = y.iterator();
		boolean h1=it1.hasNext();
		boolean h2=it2.hasNext();
		Integer a=it1.next();
		Integer b=it2.next(); 
		while(h1&h2) {
			if(a-borrow<b) {
				a=a+base-borrow;
				difference=a-b;
				z.add(difference);
				borrow=1;
			}
			else
			{
				difference=a-b-borrow;
				z.add(difference);
				borrow=0;
			}
			h1=it1.hasNext();
			h2=it2.hasNext();
			if(h1)
				a=it1.next();
			if(h2)
				b=it2.next();
		}
		while(h1) {
			if(a-borrow<0) {
				a=a+base-borrow;
				z.add(a);
				borrow=1;
			}
			else {
				z.add(a-borrow);
				borrow=0;
			}
			h1=it1.hasNext();
			if(h1)
				a=it1.next();
		}
		for(int item=z.size()-1;item>=0;item--) {
			if(z.get(item)==0) {
				z.remove(item);
			}
		}
	}
	public static void printlist(List<Integer> l1) {
		for(int i=l1.size()-1;i>=0;i--)
			System.out.print(l1.get(i)+" ");
		System.out.println();

	}
	public static void main(String[] args) {
		List<Integer> list1=new LinkedList<>();
		List<Integer> list2=new LinkedList<>();
		List<Integer> list3=new LinkedList<>();
		Scanner in=new Scanner(System.in);
		int base;
		System.out.println("Enter the base");
		base=in.nextInt();
		in.nextLine();
		System.out.println("Enter the first Big Integer- digits separated by spaces");
		String l1,l2;
		l1=in.nextLine();
		System.out.println("Enter the Second Big Integer- digits separated by spaces");
		String[] str1 = l1.trim().split("\\s+");
		l2=in.nextLine();
		String[] str2 = l2.trim().split("\\s+");
		int i=0;
		for(i=0;i<str1.length;i++) {
			if((new Integer(str1[1]))>=base){

			}
			list1.add(0,(new Integer(str1[i])));
		}
		for(i=0;i<str2.length;i++) {
			list2.add(0,(new Integer(str2[i])));
		}
		Timer timer = new Timer();
		timer.start();
		LargeIntegers li=new LargeIntegers();
		add(list1, list2, list3, base);
		System.out.println("Addition result");
		printlist(list3);
		subtract(list1, list2, list3, base);
		list3=new LinkedList<>();
		subtract(list1, list2, list3, base);
		System.out.println("Subtraction result");
		printlist(list3);
		timer.end();
		System.out.println(timer); 
	}
}
