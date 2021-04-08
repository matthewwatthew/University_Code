import java.lang.reflect.Array;
import java.util.*;

public class Draft {

    public static long numWays(List<Integer> ranks) {
        int rankSize = ranks.size();
        if(rankSize == 1){
            return 2;
        }
        if(rankSize % 2 != 0){
            rankSize +=1;
        }
        int sum;
        sum = (rankSize / 2) * (1 + rankSize);
        int number1 = (sum /2);
        int divisor1;
        int divisor2 = 0;
        if (rankSize % 2 == 0) {
            divisor1 = (rankSize / 2);
        } else {
            divisor1 = (rankSize / 2);
            divisor2 = (rankSize / 2) + 1;
        }
        if (divisor2 == 0) {
            int[] data = new int[divisor1];
            int[] copyRank = new int[rankSize];
            for (int i = 0; i < rankSize; i++) {
                copyRank[i] = i + 1;
            }
            if (rankSize == 1) {
                return 2;
            }
            long fina = comboMaker(copyRank, data, 0, rankSize - 1, 0, divisor1, number1);
            if(rankSize % 4 != 0){
                fina *=2;
            }
            return fina;
        } else {
            return 0;
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


    public static boolean pick(List<Integer> ranks, int player) {
        int firstNum = 1;
        int lastNum = ranks.size();
        int totalNum = firstNum + lastNum;
        int number1 = totalNum / 2;
        int number2 = (totalNum / 2) + 1;
        int rankNumber = ranks.get(player) - 1;
        if (ranks.size() % 2 == 0) {
            if (ranks.size() % 4 == 0) {
                if (rankNumber < ranks.size() / 2) {
                    if (rankNumber % 2 == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
                else {
                    if (rankNumber % 2 == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                if (rankNumber + 1 == number1) {
                    return true;
                }
                if (rankNumber + 1 == number2) {
                    return false;
                }
                if (rankNumber < ranks.size() / 2) {
                    if (rankNumber % 2 == 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    if (rankNumber % 2 == 0) {
                        return false;
                    }
                    else {
                        return true;
                    }
                }
            }
        }
        else {
            int reg = ranks.size() / 2;
            if (rankNumber == reg) {
                return true;
            }
            if (rankNumber < reg + 1) {
                if (rankNumber % 2 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (rankNumber % 2 == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
    public static void main(String[] args){
        List<Integer> lister = new ArrayList<>();
        lister.add(1);
        lister.add(2);
        lister.add(3);
        lister.add(4);
        System.out.println(numWays(lister));
    }

}