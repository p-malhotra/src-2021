package practice;

import java.util.Arrays;

public class Sorts {
    static void selectionSort(int[] arr){
        if(arr== null || arr.length ==0)
            return ;
        for(int i=0;i<arr.length;i++)
        {
            int min=i;
            for(int j=i+1;j<arr.length;j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int tmp=arr[min];
            arr[min]=arr[i];
            arr[i]=tmp;

        }
        System.out.println("Selection Sort"+ Arrays.toString(arr));
    }

    static void insertionSort(int[] arr){
        if(arr== null || arr.length ==0)
            return ;
        for(int i=1;i<arr.length;i++)
        {
            int key=arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>    key){
                 arr[j+1] = arr[j];
                 j--;
            }
            arr[j+1]=key;
        }
        System.out.println("Insertion Sort"+ Arrays.toString(arr));
    }

    static void bubbleSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int tmp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        System.out.println("Bubble Sort"+ Arrays.toString(arr));
    }
    static void mergeSort(int[] arr){
        mergeSort(arr,new int[arr.length],0,arr.length-1);
        System.out.println("MergeRec "+ Arrays.toString(arr));


    }
    static void mergeSort(int[] arr, int[] aux, int st,int end){
        if(st<end){
            int mid=(end+st) /2 ;
            mergeSort(arr,aux,st,mid);
            mergeSort(arr,aux,mid+1, end);
            merge(arr,aux,st,mid,end);
        }
    }
    public static void mergea(int[] arr,int[] aux, int lo,int mid, int hi ) {

        //copy to aux
        for(int k=lo;k<=hi;k++){
            aux[k]=arr[k];
        }
        int i=lo;
        int j=mid+1;
        int curr=lo;
        while(i<=mid && j<=hi){
            if(aux[i]<aux[j])
                arr[curr++]=aux[i++];
            else
                arr[curr++]=aux[j++];
        }
        while(i<=mid)
            arr[curr++]=aux[i++];
        while(j<=hi)
            arr[curr++]=aux[j++];

    }
    static  void merge(int[] arr, int[] aux, int st,int mid,int end){
        for(int k=st;k<=end;k++){
            aux[k]=arr[k];
        }
        int i=st;
        int j=mid+1;
        int curr=st;
        while(i<=mid && j <= end){
            if(aux[i]<aux[j])
                arr[curr++]=aux[i++];
            else
                arr[curr++]=aux[j++];
        }
        while(i<=mid)
            arr[curr++]=aux[i++];
        while(j<=end)
            arr[curr++]=aux[j++];
    }
    static void mergeIt(int[] arr){
        int[] aux=new int[arr.length];

        for(int size=1;size<arr.length;size *=2){
            for(int lo=size;lo<arr.length-size;lo=lo+2*size){
                int mid=lo+size-1;
                int hi=Math.min(lo+2*size-1,arr.length);
                merge(arr,aux,lo,hi,mid);
            }
        }
        System.out.println("Merge Insert "+ Arrays.toString(arr));
    }
    //Time On(log(n) space O(1)
    public static int[] heapSort(int[] array) {
        //build head
        for(int i=array.length/2-1;i>=0;i--){
            heapify(array,array.length,i);
        }
        System.out.println(" -- >"+Arrays.toString(array));
        for(int i=array.length-1;i>0;i--){
            int tmp=array[0];
            array[0]=array[i];
            array[i]=tmp;
            heapify(array,i,0);
            System.out.println(" >"+Arrays.toString(array));

        }
        return array;
    }
    static void heapify(int[] arr, int len,int idx){
        int max=idx;
        int l=2*idx+1;
        int r=2*idx+2;
        if(l<len && arr[l]>arr[max]){
            max=l;
        }
        if(r<len && arr[r]>arr[max])
            max=r;
        if(max !=idx)// swap
        {
            int tmp=arr[max];
            arr[max]=arr[idx];
            arr[idx]=tmp;
            heapify(arr,len,max);
        }
    }
    public static int[] quickSort(int[] array) {
        quickSort(array,0,array.length-1);
        return array;
    }

    static void ThreeWayquicksort(int[] arr, int l, int r){
        System.out.println("3 way quicksort "+Arrays.toString(arr));
        ThreeWaySort( arr, 0, arr.length-1);
        System.out.println("3 way quicksort "+Arrays.toString(arr));
    }
    public static void ThreeWaySort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = arr[lo]; //pivot
        int i = lo + 1;
        while (i <= gt) {
            if      (arr[i] < v)
                exch(arr, lt++, i++);
            else if (arr[i] > v
            ) exch(arr, i, gt--);
            else              i++;
        }
        ThreeWaySort(arr, lo, lt-1);
        ThreeWaySort(arr, gt+1, hi);
    }
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    static void quickSort(int[] arr, int lo, int hi){
        if(hi <=lo)
            return;
        int j= quickSPartition(arr,lo,hi);
        quickSort(arr,lo,j-1);
        quickSort(arr,j+1,hi);

        System.out.println("Quick "+ Arrays.toString(arr));
    }
    static int quickSPartition(int[] arr, int left, int right){
        int i =left,y=right+1;
        while(true){
            while(arr[++i]<arr[left]){
                if(i==right)
                    break;

            }
            while( arr[left]<arr[--y]) {
                if (y == left)
                    break;
            }
            if(i >= y) break;
            int tmp=arr[i];
            arr[i]=arr[y];
            arr[y]=tmp;

        }
        int tmp=arr[left];
        arr[left]=arr[y];
        arr[y]=tmp;
        return y;
    }
    static void countSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) { //fill count array
            count[arr[i] - min]++;
        }
        System.out.println("1. Count "+ Arrays.toString(count));
        for (int i = 1; i < count.length; i++) { //increment array with prev sum
            count[i] += count[i - 1];
        }
        System.out.println("2. Count "+ Arrays.toString(count));
        for (int i = arr.length - 1; i >= 0; i--) { //
            output[count[arr[i] - min] - 1] = arr[i];
            System.out.println(" Count -> "+ Arrays.toString(output));
            count[arr[i] - min]--;
            System.out.println("  *-> "+ Arrays.toString(count));
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
        System.out.println("Count Sort : "+ Arrays.toString(arr));

    }
    static void countSrt(int[] arr){ //will store count of that minimum element at zero index.
        int max= Arrays.stream(arr).max().getAsInt();
        int min= Arrays.stream(arr).min().getAsInt();
        int[] count= new int[max-min+1];
        int[] res=new int[arr.length];
        for(int i=0;i<arr.length;i++)
            count[arr[i]-min]++;

        for(int i=1;i<count.length;i++)
            count[i]+=count[i-1];
        for(int i=arr.length-1;i>=0;i--){
            res[count[arr[i]-min]-1]=arr[i];
            count[arr[i]-min]--;
        }
        for(int i=1;i<count.length;i++)
            count[i]+=count[i-1];
        System.out.println("Count Srt : "+ Arrays.toString(res));

    }


    public static void main(String[] args) {
        ThreeWayquicksort(new int[]{3,1,5,1,9,6,4},0,7);
        quickSort(new int[]{3,8,5,1,9,6,2,4});
        heapSort(new int[]{3,8,5,1,9,6,2,4});

        selectionSort(new int[]{3,8,5,1,9,6,2,4});
        insertionSort(new int[]{3,8,5,1,9,6,2,4});
        bubbleSort(new int[]{3,8,5,1,9,6,2,4});
        mergeSort(new int[]{3,8,5,1,9,6,2,4});
        mergeIt(new int[]{3,8,5,1,9,6,2,4});
    }

}
