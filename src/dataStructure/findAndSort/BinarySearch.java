package dataStructure.findAndSort;

//O(log n)
public class BinarySearch {
    public static <T extends Comparable<? super T>> boolean
               binarySearch(T[] data, int min, int max, T target) {
        boolean found = false;
        int midpoint = (min + max) / 2;
        if (data[midpoint] == target) {
            found = true;
        } else if (data[midpoint].compareTo(target) > 0) {
            if (min <= midpoint - 1) {
                found = binarySearch(data, min, midpoint - 1, target);
            }
        } else if (midpoint + 1 <= max) {
            found = binarySearch(data, midpoint + 1, max, target);
        }
        return found;
    }

    public static int commonBinarySearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }

        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                high = middle - 1;
            }else if(arr[middle] < key){
                low = middle + 1;
            }else{
                return middle;
            }
        }
        return -1;
    }
}
