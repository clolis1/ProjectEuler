package projecteuler.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Code obtained from https://www.geeksforgeeks.org/lexicographic-permutations-of-string/
 */

public class Lexography
{
   
    // A utility function two swap two characters a and b
    public static void swap(char[] str, int i, int j)
    {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }
 
    // A utility function to reverse a string str[l..h]
    public static void reverse(char str[], int l, int h)
    {
        while (l < h) {
            swap(str, l, h);
            l++;
            h--;
        }
    }
 
    // This function finds the index of the smallest
    // character which is greater than 'first' and is
    // present in str[l..h]
    public static int findCeil(char str[], char first, int l,
                        int h)
    {
        // initialize index of ceiling element
        int ceilIndex = l;
 
        // Now iterate through rest of the elements and find
        // the smallest character greater than 'first'
        for (int i = l + 1; i <= h; i++)
            if (str[i] > first && str[i] < str[ceilIndex])
                ceilIndex = i;
 
        return ceilIndex;
    }
 
    // Print all permutations of str in sorted order
    public static ArrayList<String> sortedPermutations(String s)
    {
        char[] str = s.toCharArray();
        ArrayList<String> perms = new ArrayList<String>();
        // Get size of string
        int size = str.length;
 
        // Sort the string in increasing order
        Arrays.sort(str);
 
        // Print permutations one by one
        boolean isFinished = false;
        while (!isFinished) {
            // print this permutation
            perms.add(new String(str));
 
            // Find the rightmost character which is
            // smaller than its next character.
            // Let us call it 'first char'
            int i;
            for (i = size - 2; i >= 0; --i)
                if (str[i] < str[i + 1])
                    break;
 
            // If there is no such character, all are
            // sorted in decreasing order, means we
            // just printed the last permutation and we are
            // done.
            if (i == -1)
                isFinished = true;
            else {
                // Find the ceil of 'first char' in
                // right of first character.
                // Ceil of a character is the smallest
                // character greater than it
                int ceilIndex = findCeil(str, str[i], i + 1,
                                         size - 1);
 
                // Swap first and second characters
                swap(str, i, ceilIndex);
 
                // reverse the string on right of 'first
                // char'
                reverse(str, i + 1, size - 1);
            }
        }
        return perms;
    }
     
    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static void combinationUtil(char arr[], int n, int r, int index, char data[], int i, TreeSet<String> combos)
    {
        // Current combination is ready to be printed, print it
        if (index == r) {
            String s = "";
            for (char k : data) s += k;
            combos.add(s);
            return;
        }
     
        // When no more elements are there to put in data[]
        if (i >= n) return;
     
        // current is included, put next at next location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1, combos);
     
        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1, combos);
    }
     
    // The main function that adds all combinations of size r
    // in arr[] of size n to an ArrayList<String>. This function mainly uses combinationUtil()
    public static TreeSet<String> getCombinations(char arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        char data[]=new char[r];
        TreeSet<String> combos = new TreeSet<String>();
        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, n, r, 0, data, 0, combos);
        return combos;
    }
    
    public static void combinations(int[] values, List<Integer> current, Set<Set<Integer>> accumulator, int size, int pos) {
            if (current.size() == size) {
                Set<Integer> toAdd = current.stream().collect(Collectors.toSet());
                if (!accumulator.contains(toAdd)) accumulator.add(toAdd);
                return;
            }
            for (int i = pos; i <= values.length - size + current.size(); i++) {
                current.add(values[i]);
                combinations(values, current, accumulator, size, i + 1);
                current.remove(current.size() - 1);
            }
    }
}