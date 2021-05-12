package practice;

public class Search {

    static int binarySearch(int[] arr, int num){
        return binarySearch(arr, num,0,arr.length -1);

    }
    static int binarySearch(int[] arr, int num, int l, int r){
        if(l<=r){
            int mid= (r+l)/2;
            if(arr[mid]<num)
                return binarySearch(arr, num,mid+1,r);
            else if(arr[mid]>num)
                return binarySearch(arr, num,l,mid-1);
            else
                return mid;



        }
        return -1;
    }

    static int binarySearchIt(int[] arr, int num){
        int st=0;int end=arr.length-1;
        while(st<=end){
            int mid = st+(end-st) /2;
            if(num>arr[mid]){
                st=mid+1;

            }
            else if(num<arr[mid]){
                end=mid-1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int indx=searchLinear(new int[]{2,4,6,7,8,11,14},1);
        System.out.println("Binary Indx "+binarySearch(new int[]{2,4,6,7,8,11,14},1));
        System.out.println("Iterative Binary Indx "+binarySearchIt(new int[]{2,4,6,7,8,11,14},14));
        System.out.println("Indx "+indx);
    }
    static int searchLinear(int[] arr, int num){
        int step = (int)Math.floor(Math.sqrt(arr.length));
        int prev=0;
        int next=step;
        while(next< arr.length){
            if(arr[next]<num){
                prev=next;
                next+=step;
            }else if(arr[next]>num){
                while(prev<=next){
                    if(arr[prev]==num)
                        return prev;
                    prev++;
                }
                return -1;
            }else
                return next;
        }
        return -1;
    }
}
