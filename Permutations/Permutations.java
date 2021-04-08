import java.util.*;

public class Permutations{
    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        generate(countOccurances(nums), new Stack<>(), result);
        return result;
    }

    public static Map<Integer, Integer> countOccurances(int[] nums){
        Map<Integer,Integer> counts = new HashMap<>();
        for(int i : nums){
            counts.put(i,counts.getOrDefault(i,0) + 1);
        }
        return counts;
    }
    public static void generate(Map<Integer,Integer> counts, Stack<Integer> perm, List<List<Integer>> result){
        boolean basecase = true;
        for(int key: counts.keySet()){
            if(counts.get(key) > 0) {
                basecase = false;
                break;
            }
        }
        if(basecase){
            result.add((List<Integer>) perm.clone());
            return;
        }
        for(int key: counts.keySet()){
            if(counts.get(key) > 0){
                counts.replace(key,counts.get(key) - 1);
                perm.push(key);
                generate(counts,perm,result);
                perm.pop();
                counts.replace(key,counts.get(key) + 1);
            }
        }
    }
    public static void main(String[] args){
        List<List<Integer>> perms = permute(new int[]{1,2,3,4,5,6});
        HashSet<List<Integer>> hasher = new HashSet<>();
        for(List<Integer> l : perms){
            if(l.get(0) + l.get(1) + l.get(2) == 10 || l.get(0) + l.get(1) + l.get(2) == 11 ){
                List<Integer> fin = new ArrayList<Integer>();
                fin.add(l.get(0));
                fin.add(l.get(1));
                fin.add(l.get(2));
                Collections.sort(fin);
                hasher.add(fin);
            }
        }
        System.out.println(hasher.size());
    }
}