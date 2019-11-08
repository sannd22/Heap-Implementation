import java.util.*;

public class MyHeap {

	int[] heap;
	int cap = 0;
	int size = 0;

	public MyHeap() {
		heap = new int[51];
		// Add value to index 0 since heap starts at index 1
		cap = 50;
	}

	public MyHeap(int capacity) {
		heap = new int[capacity + 1];
		cap = capacity;
	}

	public boolean insert(int newArg) {
		if (size == cap) {
			return false;
		}
		size ++;
		heap[size] = newArg;
		System.out.println(Arrays.toString(heap));
		driftUp(size);
		return true;
	}

	// CHANGE TO USE DRIFT DOWN CONSTRUCTION
	public boolean buildHeap(int[] heapList) {
		if (heapList.length > cap) {
			return false;
		}
		for (int i = 0; i < heapList.length; i++) {
			heap[i + 1] = heapList[i];
		}
		System.out.println(Arrays.toString(heap));
		size = heapList.length;
		for (int i = (size/2); i >= 1; i --) {
			driftDown(i);
		}
		return true;
	}

	public void driftUp(int index) {
		int hole = size;
		int newArg = heap[index];
		while ((hole > 1) && newArg < heap[hole/2]) {
			heap[hole] = heap[hole/2];
			hole = hole/2;
		}
		heap[hole] = newArg;
	}

	public void driftDown(int index) {
		int tmp = heap[index];
		while ((index * 2) <= getHeapSize()) {
			int child = index * 2;
			if ((child != getHeapSize()) && (heap[child + 1] < heap[child])) {
				child ++;
			}
			if (heap[child] < tmp) {
				heap[index] = heap[child];
				index = child;
			}
			else {
				break;
			}
		}
		heap[index] = tmp;
	}

	public int findMin() {
		if (getHeapSize() == 0) {
			return 0;
		}
		return heap[1];
		
	}

	public int deleteMin() {
		if (size == 0) {
			return 0;
		}
		int min = heap[1];
		heap[1] = heap[getHeapSize()];
		heap[getHeapSize()] = 0;
		size --;
		driftDown(1);
		return min;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public boolean isFull() {
		if (size == cap) {
			return true;
		}
		return false;
	}

	public int getHeapCap() {
		return cap;
	}

	public int getHeapSize() {
		return size;
	}

	public static int[] heapSortDecreasing(int[] toSort) {

		MyHeap heap = new MyHeap(toSort.length);
		heap.buildHeap(toSort);
		int[] sorted = new int[heap.getHeapSize()];
		int size = heap.getHeapSize() - 1;
		for (int i = 0; i <= size; i++) {
			sorted[size - i] = heap.deleteMin();
		}
		return sorted;
	}
}