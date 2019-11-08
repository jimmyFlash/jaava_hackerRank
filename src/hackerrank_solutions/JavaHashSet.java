package hackerrank_solutions;

import java.util.*;

public class JavaHashSet {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }
        // let's convert this array to HashSet
        // if array contains any duplicate than that would be lost
//        HashSet<String> hashSet_left = new HashSet<>(Arrays.asList(pair_left));
//        HashSet<String> hashSet_right = new HashSet<>(Arrays.asList(pair_right));
        HashSet<String> merged = new HashSet<>();

        for (int i = 0 ; i < t ; i++){
            String name = pair_left[i] + " " + pair_right[i];
            merged.add(name);
            System.out.println(merged.size());
        }

    }
}
