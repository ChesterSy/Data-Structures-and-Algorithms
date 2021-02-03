import java.util.*;

public class itrQuickSort {
		
	public static void itrQuikSort(int[] arr, int l, int h) {
		int[] stk = new int[h+1]; //h - l + 1
		int top = -1;
		stk[++top] = l; //push starting range
		stk[++top] = h; //push last range
		
		while(top > -1) { //do this repeatedly till stk is empty
			h = stk[top--]; //pop(get) last range
			l = stk[top--]; //pop(get) start range
			
			int j = partition(arr, l, h);
			
			if (j - 1 > l) { //if more than 1 el
				stk[++top] = l; //push starting range
				stk[++top] = j - 1; //push range before pivot 
			}
			
			if (j + 1 < h) { //if more than 1 el
				stk[++top] = j + 1; //push range after pivot
				stk[++top] = h; //push last range
			}
		}
	}
	
	public static int partition(int[] arr, int l, int h) {
		int sum = 0;
		int len = 0;
		for(int k = l; k <= h; k++) {
			sum += arr[k];
			len++;
		}
		double nearest = sum/len;
		int index = closest(arr, l, h, nearest);
		shift(arr, l, h, index);
		int pivot = arr[h];
		int i = l;//0
		for (int j = l; j < h; j++) {
			if (arr[j] <= pivot) {
				swap(arr, j, i);
				i++;
			}
		}
		swap(arr, i, h);
		return i;
	}
	
	public static void swap(int[] arr, int l, int h) {
		int temp = arr[l];
		arr[l] = arr[h];
		arr[h] = temp;
	}
	
	public static int closest(int[] arr, int l, int h, double near) {
		double diff = Math.abs(near - arr[l]);
		int index = l;
		for (int i = l+1; i <= h; i++) {
			double newDiff = Math.abs(near - arr[i]);
			if (diff > newDiff) {
				diff = newDiff;
				index = i;
			}
		}
		return index;
	}
	
	public static void shift(int[] arr, int l, int h, int index) {
		int hold = arr[index];
		for (int i = index; i <= h; i++) {
			if (i == h) {
				arr[i] = hold;
			} else {
				arr[i] = arr[i+1];
			}
		}
	}
	
	public static void main (String[] args) {
		int arr[] = {5, 1, 2, 10, 6, 7, 7, 12, 8, 3};
        int len = arr.length; 
  
        itrQuikSort(arr, 0, len-1); 
  
        for (int x : arr) {
			System.out.print(x + " ");
		}
	}
}