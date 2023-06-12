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
        // mid = 3 first iteration if n = 3

        //(1,2,3,4,5)
        //L = 1, R = 5, M = 3 --> mid = true
        // L = 1, R = 2, M = 1 --> mid = false -->
        // L = 2, R = 2, M = 1**
        // L = mid + 1 = 2, R = 2, M = 1. cycle repeats and L is never > R

        while (left <= right) { // take the right in to find a prior version for mid
            if (isBadVersion(mid)) {
                right = mid - 1;
                mid = left + (right - left) / 2; // this can't be just L + R / 2
            } else { // only move left over if mid went past first bad version
                left = mid + 1;
                mid = right + (right - left) / 2; // PROBLEM. mid defined 2 different ways. here its rrighht plus, above
                // its left plus

            }
        }
        return mid;
        // this doesn't work because when mid is found to be a bad version, right = mid -1, and assigns new mid and returns
        // wrong answer
    }
}

//(1,2,3,4,5)
//L = 1, R = 5,
// M = 3 --> mid = true  --> L = 1, R = 3-1 = 2,  FIRST LOOP
// M = 1.5 = 1 = false --> L = 2, R = 2 SECOND LOOP\
    // left is 2, right is 2, M gets defined as 2.
// M = 2 --> L = 3, R = 2 THIRD LOOP, BREAK LOOP, RETURN END + 1**
// problem was that new mid shohuld only be defined at beginning of while loop


public class Solution extends VersionControl {
    public int firstBadVersion(int n) {

        int start = 1; // Start of the search range
        int end = n; // End of the search range
        int mid = 0; // Variable to store the middle version



        while (start <= end) {
            mid = start + (end - start) / 2; // Calculate the middle version

            if (isBadVersion(mid)) {
                end = mid - 1; // Adjust the end to search in the left half (lower versions)
            } else {
                // If the middle version is not bad, all versions before it are good
                start = mid + 1; // Adjust the start to search in the right half (higher versions)
            }
        }

        return end + 1; // Return the index of the first bad version
    }
}