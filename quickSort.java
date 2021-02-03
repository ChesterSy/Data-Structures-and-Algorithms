import java.util.*;

public class quickSort {
		
	public static void quikSort(int[] arr, int l, int h) {
		if (l < h) { //at least 2 elements
			int j = partition(arr, l, h);
			quikSort(arr, l, j-1);
			quikSort(arr, j+1, h);
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
		int arr[] = {99,88,77,66,55,33,22,44,11,20,60,30,40,10,50,80,90,70,1,6,8,3,2,4,9,7,123,654,789,741,852,963,100,120,150,987,963,258,741,147,852,369,410,520,630,740,850,960,714,825,936,396,285,174,79,46,13,31,64,97,74,85,96,41,52,63,14,25,36,47,58,69,1000,2000,3000,4000,7000,8000,5000,9000,6000,1010,2020,3030,4040,8080,9090,6060,5050,7070,7410,9630,8520,7890,6540,1230,3210,4560,9870,7845,1232,6598,8956,2312,4578,7711,2288,9933,3399,8822,1177,4455,6655,5566,5544,1122,2233,3322,2211,7788,8899,9988,8877,1144,4477,7744,4411,2255,5588,8855,5522,3366,6699,9966,6633,1111,2222,5555,4444,7777,8888,9999,3333,6666};
        int len = arr.length; 
        quikSort(arr, 0, len-1); 
		System.out.println("Sorted Array: " + Arrays.toString(arr));
	}
}