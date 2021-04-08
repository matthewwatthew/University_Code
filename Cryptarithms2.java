import java.util.*;

public class Cryptarithms2 {

    private static void generate(Map<Integer,Integer> counts, Stack<Integer> perm, List<List<Integer>> result, List<String> equation, ArrayList<String> original){
        if (result.size() == 1) {
            return;
        }
        boolean basecase = true;
        for (int key : counts.keySet()) {
            if (counts.get(key) > 0) {
                basecase = false;
                break;
            }
        }
        if (basecase) {
            List<Integer> p = (List<Integer>) perm.clone();
            System.out.println(p);
            Set<Character> hash_set = new HashSet<>();
            String letters = "";
            for (String s : equation) {
                for (int i = 0; i < s.length(); i++) {
                    hash_set.add(s.charAt(i));
                }
            }
            for (char c : hash_set) {
                StringBuilder sb = new StringBuilder();
                letters = sb.append(letters).append(c).toString();
            }
            HashMap<Character, Integer> mapper = new HashMap<>();
            for (int i = 0; i < hash_set.size(); i++) {
                mapper.put(letters.charAt(i), p.get(i));
            }
            int addition = 0;
            for(int i = 0; i < equation.size() -1; i++){
                addition += mapper.get(equation.get(i).charAt(equation.get(i).length()-1));
            }
            int lastLetter = mapper.get(equation.get(equation.size()-1).charAt(equation.get(equation.size()-1).length()-1));
            if(addition % 10 != lastLetter){
                return;
            }
            int answer = 0;
            int sumNum = 0;
            ArrayList<String> caller = (ArrayList) original.clone();
            String sum = caller.remove(caller.size() - 1);
            int lengthOfSum = sum.length();
            for (int i = 0; i < lengthOfSum; i++) {
                sumNum += mapper.get(sum.charAt(i)) * (Math.pow(10, lengthOfSum - 1 - i));
            }
            for (int i = 0; i < caller.size(); i++) {
                String current = caller.get(i);
                int lenCurrent = current.length();
                for (int j = 0; j < lenCurrent; j++) {
                    answer += mapper.get(current.charAt(j)) * Math.pow(10, lenCurrent - 1 - j);
                }
            }
            if(answer == sumNum){
                result.add((List<Integer>) perm.clone());
            }
            return;
        }
        for (int key : counts.keySet()) {
            if (counts.get(key) > 0) {
                counts.replace(key, counts.get(key) - 1);
                perm.push(key);
                generate(counts, perm, result,equation, original);
                perm.pop();
                counts.replace(key, counts.get(key) + 1);
            }
        }
    }

    /**
     * @param equation a list of strings that make up the equation, last element is the sum, all other elements are addends
     * @return a map from characters to integers representing your final solution
     */
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
        int[] nums = new int[]{0,1,2,3,4,5,6,7,8,9};
        Map <Integer,Integer> counts = new HashMap<>();
        for(int i : nums){
            counts.put(i,counts.getOrDefault(i,0) + 1);
        }
        generate(counts,new Stack<>(),result, equation, original);
        HashMap<Character,Integer> mapper = new HashMap<>();
        for(List<Integer> p : result) {
            for (int i = 0; i < hash_set.size(); i++) {
                mapper.put(letters.charAt(i), p.get(i));
            }
        }
        if(result.size() == 1){
            return mapper;
        }
        else{
            return null;
        }
    }
    static long comboMaker(int arr[], int data[], int start, int end, int index, int r, int sum) {
        int counter = 0;
        if (index == r) {
            if (r % 2 == 0) {
                for(int j = 0; j < r; j+=2){
                    counter += data[j];
                    counter += data[j+1];
                }
            }
            else if(r % 3 == 0){
                for(int j = 0; j < r; j+=3) {
                    counter += data[j];
                    counter += data[j+1];
                    counter += data[j+2];
                }
            }
            else {
                for (int j = 0; j < r; j++) {
                    counter += data[j];
                }
            }
            if(counter == sum){
                return 1;
            }
            else {
                return 0;
            }
        }
        long counters = 0;
        for (int i = start; i <= end && (end - i) + 1 >= (r - index); i++) {
            data[index] = arr[i];
            counters += comboMaker(arr, data, i+1, end, index+1, r, sum);
        }
        return counters;
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