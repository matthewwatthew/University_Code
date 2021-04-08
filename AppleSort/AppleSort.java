import java.util.ArrayList;
import java.util.PriorityQueue;

public class AppleSort {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                a[k] = aux[j++];
            }
            else if(j > hi){
                a[k] = aux[i++];
            }
            else if(less(aux[j], aux[i])){
                a[k] = aux[j++];
            }
            else{
                a[k] = aux[i++];
            }
        }
    }
    private static void sort(Comparable[]a, Comparable[] aux, int lo, int hi){
        if(hi <= lo){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    /**
     * Implement this method that takes in an arraylist of Apples and sorts them into one giant array of Apples.
     * You are free to implement any helper methods you need.
     * @param apples
     * @return
     */

    public static Comparable[] sort(ArrayList<Apple[]> apples){
        int size = 0;
        for(Apple[] appleList: apples){
            size += appleList.length;
        }
        Comparable[] list = new Comparable[size];
        int counter = 0;
        for(Apple[] appleList: apples){
            for(int i = 0; i < appleList.length; i++){
                list[counter] = appleList[i];
                counter +=1;
            }
        }
        System.out.println(list[0]);
        Comparable[] lastList = new Comparable[list.length];
        sort(list,lastList, 0, list.length-1);
        return lastList;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        //Apple[] basket0 = {new Apple(1,1), new Apple(2,1)};
        //Apple[] basket1 = {new Apple(1,10), new Apple(2,10),new Apple(1,10)};
        //ArrayList<Apple[]> list = new ArrayList();
        //list.add(basket0);
        //list.add(basket1);
        //Comparable[] apples =  AppleSort.sort(list);
        //Apple a1 = (Apple)apples[4];
        //System.out.println(a1.type);
        //System.out.println(a1.deliciousness);
    }

}
