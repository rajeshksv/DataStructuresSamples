public class Heap {
	// Creating MaxHeap
	int [] heap = new int [100];
	int lastElementIndex = -1;

	void insertNode(int[] heap,int element){
		// Very good function to understand the complexity of recursion
		heap[++lastElementIndex] = element;
		heapify(lastElementIndex);
	}

	void insert(int element){
		insertNode(heap, element);
	}

	int getTopElement(){
		int topElement = heap[0];
		heap[0] = heap[lastElementIndex];
		heap[lastElementIndex--] = 0;
		removeTopNode(0);
		return topElement;
	}


	private void removeTopNode(int index) {
		if(index <= lastElementIndex){
			int child1 = heap[2*index+1];
			int child2 = heap[2*index+2];
			if(heap[index] < child1 || heap[index] < child2){
				if(child1 > child2){
					exchange(heap, index, 2*index+1);
					removeTopNode(2*index+1);
				} else {
					exchange(heap, index, 2*index+2);
					removeTopNode(2*index+2);
				}
			}
		}
	}

	private void heapify(int lastElementIndex) {
		int parentIndex = getParentIndex(lastElementIndex);
		if(-1 != parentIndex && heap[lastElementIndex] > heap[parentIndex]){
			exchange(heap, lastElementIndex, parentIndex);
			heapify(parentIndex);
		}
	}

	public String toString(){
		String arrayString = "";
		for(int i=0; i<= lastElementIndex; i++){
			arrayString += heap[i] + ",";
		}
		
		if(lastElementIndex < 0) return "";
		return arrayString.substring(0, arrayString.length()-1);
	}

	int getParentIndex(int index){
		if(0 == index){
			return -1;
		} else if(0 == index%2){
			return index/2 - 1;
		} else {
			return ((index+1)/2)-1;
		}
	}

	void exchange(int [] array, int pos1, int pos2){
		int dummy = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = dummy;
	}

	public static void main(String[] args) {
		Heap heap = new Heap();
		heap.insert(19);
		heap.insert(20);
		heap.insert(4);
		heap.insert(26);
		System.out.println(heap);
		System.out.println("Top = " + heap.getTopElement() + " Rem = " + heap);
		System.out.println("Top = " + heap.getTopElement() + " Rem = " + heap);
		System.out.println("Top = " + heap.getTopElement() + " Rem = " + heap);
		System.out.println("Top = " + heap.getTopElement() + " Rem = " + heap);
	}
}
