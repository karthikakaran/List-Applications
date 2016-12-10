//===========================================================================================================================
//	Program : Tp perform merge sort on a list
//===========================================================================================================================
//	@author: Nevhetha,Kritika,Karthika
// 	Date created: 2016/09/01
//	Date modified: 2016/09/10
//===========================================================================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MergeSort<T extends Comparable<? super T>>{

	private List<T> list = new ArrayList<>();
	/** Procedure to recursively divide the list
	 * Runs in time O(nlogn) where n is the size of the list 
	 * @param start : Integer, start index of the array
	 * @param end : Integer, end index of the array 
	 * @variable middle : Integer, find middle index
	 */
	//Divide the list into sublists
	public void mergeSort(int start, int end){

		if(end - start < 1) return ;

		int middle = (start + end)/2;
		//Recursive calling
		mergeSort(start, middle);
		mergeSort(middle + 1, end);
		//Sending the sub lists to merge
		merge((List<T>) list, start, middle, middle + 1, end);
	}
	/** Procedure to sort by merging the two divided lists
	 * @param list : List, list to sort
	 * @param left : Integer, start index of the first list
	 * @param leftEnd : Integer, end index of the first list
	 * @param right : Integer, start index of the second list
	 * @param rightEnd : Integer, end index of the second list
	 * @variable tmpList : List, to store the sorted list and replace 
	in the actual list at end
	 * @variable i,j : helpers for indexing 
	 */
	public void merge(List<T> list, int left, int leftEnd, int right, int rightEnd){
		//Copy only the sub list to sort and merge into Temp arraylist
		List<T> tmpList = new ArrayList<>(right - left + 1);
		int i = left;
		int j = right;

		//Copy which ever is smaller
		while(i <= leftEnd && j <= rightEnd){
			if(list.get(i).compareTo(list.get(j)) < 0){
				tmpList.add(list.get(i++));
			} else {
				tmpList.add(list.get(j++));
			}
		}
		//Copy remaining elements
		while(i <= leftEnd){
			tmpList.add(list.get(i++));
		}
		while(j <= rightEnd){
			tmpList.add(list.get(j++));
		}
		//Replace the actual list with sorted portion in the temp list
		for (int k = 0; k < tmpList.size(); k++)
			list.set(k + left, tmpList.get(k));
	}

	public void println(){
		for(T item : this.list){
			System.out.println(item);
		}
	}

	public static void main(String[] args) {		
		int n = 1000000;
		MergeSort<Integer> ms = new MergeSort<>();
		Scanner in = new Scanner(System.in);

		for (int i = n; i > 0; i--){
			ms.list.add(i);
		}
		in.close();
		Timer timer = new Timer();
		ms.mergeSort(0, ms.list.size() - 1);

		System.out.println(timer.end());
		ms.println();
	}
}
