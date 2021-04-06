import java.util.*;

class mergeSort {
	public static void mergeSortAlgo(int[] arr, int first, int last) {
		if (first < last) { //if there are at least 2 elements
			int mid = (first+last) / 2; //first+(last+first)/2
			mergeSortAlgo(arr, first, mid);
			mergeSortAlgo(arr, mid+1, last);
			merge(arr, first, mid, last);
		}
	}
	
	public static void merge(int[] arr, int first, int mid, int last) {
		int leftArr_len = mid - first + 1;
		int rightArr_len = last - mid;
		int i = 0;
		int j = 0;
		int k = first;
		
		int[] leftSubArr = new int[leftArr_len];
		int[] rightSubArr = new int[rightArr_len];
		
		for (int l=0; l < leftArr_len; l++) {
			leftSubArr[l] = arr[first + l];
		}
		for (int r=0; r < rightArr_len; r++) {
			rightSubArr[r] = arr[mid + 1 + r];
		}
		
		while (i < leftArr_len && j < rightArr_len) { //sorting the subarray to the original array
			if(leftSubArr[i] <= rightSubArr[j]) {
				arr[k] = leftSubArr[i];
				i++;
			} else {
				arr[k] = rightSubArr[j];
				j++;
			}
			k++;
		}
		
		while (i < leftArr_len) { //if there are still elements left in left subarray
			arr[k] = leftSubArr[i];
			i++;
			k++;
		}
		
		while (j < rightArr_len) { //if there are still elements left in right subarray
			arr[k] = rightSubArr[j];
			j++;
			k++;
		}
	}

	public static void main(String[] args) {
		int[] arr = {10, 5, 12, 1, 3, 7};

		System.out.print("Unsorted Array: " + Arrays.toString(arr) + "\n");
		mergeSortAlgo(arr, 0, arr.length-1);
		System.out.print("Sorted Array: " + Arrays.toString(arr));
	}
}

//Time Complexity - O(nlogn)
//Space Complexity - O(n)