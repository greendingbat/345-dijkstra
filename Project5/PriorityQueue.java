import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PriorityQueue {

	private GraphNode[] data;
	private int n; // number of elements being used in the array
	private int dotNum; // number to keep track of how many .dot files have been created

	/// NOTE: Dot files are only created AFTER operations (build, insert, remove)
	/// generating dot files during bubbleUp and bubbleDown got overwhelming


	public PriorityQueue() {
		// Default constructor
		this.data = new GraphNode[4];
		this.n = 0;
		this.dotNum = 0;
	}

	private void buildMaxHeap() {
		int i = parentOf(n - 1); // this is the index of the first parent node
		while (i >= 0) {
			bubbleDown(i);
			i--;
		}
	}

	private void updateHeapIndex() {
		for (int i = 0; i < n; i ++) {
			data[i].heapIndex = i;
		}
		
	}
	
	public void insert(GraphNode newVal) {
		// grow the array first if this insert will make our data too big
		if (n + 1 > data.length) {
			growArray();
		}
		// add data at the end of the array (last leaf node) and bubble it up into place
		data[n] = newVal;
		bubbleUp(n);
		n++;
		updateHeapIndex();
	}

	private void growArray() {
		// Allocate a new array of twice the original size and populate it
		GraphNode[] newData = new GraphNode[data.length * 2];
		int i = 0;
		for (GraphNode element : data) {
			newData[i] = element;
			i++;
		}
		data = newData;
	}

	public GraphNode removeMin() {
		// Swap max down to last leaf
		GraphNode min = data[0];
		swap(0, n - 1);
		n--;
		// bubble new root down (kick it root down)
		bubbleDown(0);
		updateHeapIndex();
		return min;
	}

	public void bubbleUp(int index) {
		// Swap element up until it's no longer LESS than its parent, or until its
		// the root
		while (data[parentOf(index)].compareTo(data[index]) > 0) {
			swap(parentOf(index), index);
			index = parentOf(index);
		}
		data[index].heapIndex = index;
	}

	private void bubbleDown(int index) {
		// Swap element with its largest child until it is no longer LESS than either
		// child
		int childL = (index * 2) + 1;
		int childR = childL + 1;

		while (childL < n) {
			if (childR >= n) {
				// if there's only a left child, swap with it if it's SMALLER
				if (data[childL].compareTo(data[index]) < 0) {
					swap(index, childL);
				}
				index = childL;
			} else if (data[childL].compareTo(data[childR]) > 0) {
				// if both children exist and the left is bigger than the right, swap left if
				// it's SMALLER
				if (data[childL].compareTo(data[index]) < 0) {
					swap(index, childL);
				}
				index = childL;
			} else {
				// else swap right if it's SMALLER
				if (data[childR].compareTo(data[index]) < 0) {
					swap(index, childR);
				}
				index = childR;
			}
			childL = (index * 2) + 1;
			childR = childL + 1;
		}
	}

	private int parentOf(int index) {
		// Given the index of a "node" in the array, return the index of its parent
		return (index - 1) / 2;
	}

	private void swap(int i1, int i2) {
		// Simple helper function to swap the elements at two given indices
		GraphNode temp = data[i1];
		data[i1] = data[i2];
		data[i2] = temp;
	}
	
	public boolean isEmpty() {
		return (n == 0);
	}


	public void dump(PrintWriter out) {
		// Write current heap to output
		if (n == 0) {
			out.printf("\n");
		} else {
			for (int i = 0; i < n - 1; i++) {
				out.printf("%d ", data[i]);
			}
			out.printf("%d\n", data[n - 1]);
		}

	}

}
