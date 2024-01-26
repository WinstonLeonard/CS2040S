/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int FindPeak(int[]A, int start, int end){
        int mid = (start + end)/2;
        if(mid == end){
            return A[mid];
        } else if (A[mid] > A[mid +1] && A[mid] > A[mid -1]){
            return A[mid];
        } else if (A[mid +1] > A[mid]){
            return FindPeak(A, mid+1, end);
        } else {
            return FindPeak(A, start,mid-1);
        }
    }
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this
        if(dataArray.length == 0) {
            return 0;
        }else if(dataArray.length == 1){
            return dataArray[0];
        } else{
            boolean increasing = false;
            if(dataArray[0]<dataArray[1]){
                increasing = true;
            }
            if(increasing) {
                if (dataArray.length == 2) {
                    return dataArray[1];
                } else {
                    return FindPeak(dataArray, 0, dataArray.length - 1);
                }
            }else{
                if(dataArray[0] > dataArray[dataArray.length-1]){
                    return dataArray[0];
                } else{
                    return dataArray[dataArray.length-1];
                }
            }
        }
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
