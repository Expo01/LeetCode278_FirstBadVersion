public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

// concept, given a list of versions, use binary sreach O(log n) to find and return the firs occurence of a bad versionn.

public class Solution extends VersionControl {

    //I originally included a variable for lowestFoundBadVersion but it ends up being unneccary since ethe statemenent
    // will always work until the first bad verrsion found

    public int firstBadVersion(int n) {
        int left = 1; // this is not indexes in an array. don't use zero. these are version numbers
        int right = n;
        int mid = left + (right - left) / 2; //order of operations, parentheses firrst, then division, then add.
        // mid = 3 first iteration if n = 5

        //(1,2,3,4,5)
        //L = 1, R = 5, M = 3 --> mid != true
        // L = 4, R = 5, M = 4 --> mid = true, returned mid

        while (left <= right) { // take the right in to find a prior version for mid
            if (isBadVersion(mid)) {
                right = mid - 1;
                mid = left + (right - left) / 2; // this can't be just L + R / 2
            } else { // only move left over if mid went past first bad version
                left = mid + 1;
                mid = right + (right - left) / 2;
            }
        }
        return mid;
        // this only worrks for half of test cases and i don't know why.... trryr again tomorrow
    }
}