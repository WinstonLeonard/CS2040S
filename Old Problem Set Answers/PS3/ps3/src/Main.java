public class Main {
    public static void main(String[] args) {
        // Create three key value pairs
        KeyValuePair[] testArray = new KeyValuePair[3];
        testArray[0] = new KeyValuePair(5, 1);
        testArray[1] = new KeyValuePair(7, 2);
        testArray[2] = new KeyValuePair(5, 3);

        //Create a Sorter
        ISort sortingObject = new SorterA();

        // Do the sorting
        long sortCost = sortingObject.sort(testArray);

        SortingTester test = new SortingTester();

        System.out.println(SortingTester.isStable(sortingObject,3));

        System.out.println(testArray[0]);
        System.out.println(testArray[1]);
        System.out.println(testArray[2]);
        System.out.println("Sort Cost: " + sortCost);
    }
}
