import java.util.Scanner;

public class MergeSortSLL<T extends Comparable<? super T>> extends SinglyLinkedListNoHeader<T> {

	public static <T extends Comparable<? super T>> void mergeSort(MergeSortSLL<T> lst) {

		if (lst.header == lst.tail)  return;

		SinglyLinkedListNoHeader<T>.Entry<T> head = lst.header;//.next;
		SinglyLinkedListNoHeader<T>.Entry<T> tail = head;
		SinglyLinkedListNoHeader<T>.Entry<T> middle = head;

		while (tail != null && tail != lst.tail && tail.next != lst.tail && tail.next != null && tail.next.next != null) {
			middle = middle.next;
			tail = tail.next.next;
		}

		//if(head == tail) return ;

		MergeSortSLL<T> fhalf = new MergeSortSLL<>();
		fhalf.header = head;
		fhalf.tail = middle;
		//fhalf.tail.next = null;
		MergeSortSLL<T> lhalf = new MergeSortSLL<>();
		lhalf.header = middle.next;
		if ( tail.next != lst.tail )
			lhalf.tail = tail;
		else
			lhalf.tail = lst.tail;
		//lhalf.tail.next = null;

		mergeSort(fhalf);
		mergeSort(lhalf);
		fhalf.merge(lhalf);
	}

	public void merge(MergeSortSLL<T> lst) {
		Entry<T> head1 = this.header;//.next;
		Entry<T> head2 = lst.header;//.next;
		Entry<T> tail1 = null;
		while (head1 != this.tail.next && head2 != lst.tail.next) {
			if (head1.element.compareTo(head2.element) <= 0) {
				if (tail1 == null)
					tail1 = head1;
				else
					tail1.next = head1;
				tail1 = head1;

				head1 = head1.next;
			} else {
				if (tail1 == null)
					tail1 = head2;
				else
					tail1.next = head2;
				tail1 = head2;
				head2 = head2.next;
			}
		}
		if (head1 != this.tail.next) {
			tail1.next = head1;
			tail1 = tail1.next;
			tail1.next = head2;
		} else if (head2 != lst.tail.next){
			tail1.next = head2;
		}
		//this.header = tail1;
	}

	public static void main(String[] args) {
		int n = 5, inputNum = 0;

		MergeSortSLL<Integer> ms = new MergeSortSLL<>();
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < n; i++){
			inputNum = in.nextInt();
			ms.add(new Integer(inputNum));
		}

		System.out.println("After merge sort ::");
		mergeSort(ms);

		System.out.println();
		in.close();
	}
}
