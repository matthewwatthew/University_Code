public class NthPlaceLoser {
    /**
     * TODO: Implement this function using any helper functions you deem necessary.
     *
     */
    private static int partition(Student[] a, int lo, int hi){
        int i = lo, j = hi+1;
        while(true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while(less(a[lo], a[--j])){
                if(j == lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            exch(a,i,j);
        }
        exch(a, lo, j);
        return j;
    }
    public static void sort(Student[] a){
        sort(a, 0, a.length -1);
    }
    public static void sort(Student[] a,int lo, int hi){
        if(hi <= lo){
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }


    public static Student NthPlaceLoser(Student[] aList, int N) {
        sort(aList);
        return aList[N];
    }

    /***************************************************************************
     *  Helper sorting functions.
     *********************************************************************git******/

    // is v < w ?
    private static boolean less(Student v, Student w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Student Matthew = new Student(5, "Matt");
        Student Susan = new Student(2, "Susan");
        Student Powell = new Student(3, "Powell");
        Student Chang = new Student(4, "Chang");
        Student LDJ = new Student(1, "LDJ");
        Student Ethan = new Student(6, "Ethan");
        Student Brian = new Student(7, "Brian");
        Student Corbin = new Student(8, "Corbin");
        Student Korey = new Student(9, "Korey");
        Student Alex = new Student(10, "Alex");
        Student[] list = new Student[]{Matthew, Ethan, Korey, Susan, Chang, LDJ, Alex, Corbin, Powell, Brian};
        System.out.println(NthPlaceLoser(list, 0).name);
    }

}
