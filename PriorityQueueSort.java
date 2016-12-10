//===========================================================================================================================
//	Program : To perform sort using Priority Queue
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//===========================================================================================================================

import java.util.PriorityQueue;
import java.util.ArrayList;

public class PriorityQueueSort<E extends Comparable<? super E>> {

	private PriorityQueue<E> priorityQueue = new PriorityQueue<>();
	/** Procedure to sort by priority queue
	 * @variable pqs : PriorityQueue, list of elements stored
	 * @variable result : ArrayList, used to store sorted elements for printing
	 */
	public static void main(String[] args) {		
		int n = 1000000;
		PriorityQueueSort<Integer> pqs = new PriorityQueueSort<>();

		//Insertion into Priority Queue
		for (int i = n; i > 0; i--){
			pqs.priorityQueue.add(i);
		}

		//Sorting while removing/dequeing items
		Timer timer = new Timer();

		ArrayList<Integer> result = new ArrayList<>();
		while(!pqs.priorityQueue.isEmpty())
			result.add(pqs.priorityQueue.remove());
		System.out.println(timer.end());

		System.out.println("Sorted Output ::");
		for (Integer item : result) {
			System.out.println(item + " ");
		}		
	}
}
