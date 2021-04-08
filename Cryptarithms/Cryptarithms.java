import java.util.*;

public class Cryptarithms {
    public static void permute(String str, int l, int r)
    {
        int counter = 0;
        if (l == r) {
            counter += 1;
            int size = str.length();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i = 0; i < size; i++){
                char c = str.charAt(i);
                arr.add(i,Character.getNumericValue(c));
            }
            System.out.println(arr);
        }
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }
    public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static Map<Character, Integer> solve(List<String> equation) {
        Set<Character> hash_set = new HashSet<>();
        String letters = "";
        for(String s: equation){
            for(int i = 0; i < s.length(); i++){
                hash_set.add(s.charAt(i));
            }
        }
        for(char c: hash_set){
            letters += c;
        }
        ArrayList<String> original = new ArrayList<>(equation);
        List<List<Integer>> result = new ArrayList<>();
        String nums = "0123456789";
        permute(nums,0,9);
        return null;
//        HashMap<Character,Integer> mapper = new HashMap<>();
//        for(List<Integer> p : result) {
//            for (int i = 0; i < hash_set.size(); i++) {
//                mapper.put(letters.charAt(i), p.get(i));
//            }
//        }
//        if(result.size() == 1){
//            return mapper;
//        }
//        else{
//            return null;
//        }
    }


    public static void main(String args[]) {
        System.out.println("Welcome to Cryptarithms!");
        List<String> input = new ArrayList<>();
        input.add("MARS");
        input.add("SATURN");
        input.add("URANUS");
        input.add("MERCURY");
        System.out.println(solve(input));
    }
}