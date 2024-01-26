import java.util.Random;


public class SortingTester {
    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair [] array = new KeyValuePair[size];
        Random rng = new Random();
        int upperbound = 100;
        for(int i = 0; i<size; i++){
            array[i] = new KeyValuePair(rng.nextInt(upperbound), rng.nextInt(upperbound));
        }
        sorter.sort(array);
        for(int i = 0; i < size-1; i++){
            KeyValuePair first = array[i];
            KeyValuePair second = array[i+1];

            if(first.getKey() > second.getKey()){
                return false;
            }
        }
        return true;
    }

    public static boolean isStable(ISort sorter, int size) {
        // TODO: implement this
        Random rng = new Random();
        KeyValuePair [] array = new KeyValuePair[size];
        for(int i = 0; i < size; i++){
            array[i] = new KeyValuePair(rng.nextInt(size/2),i);
        }
        sorter.sort(array);
        for(int i = 0; i<size-1;i++){
            KeyValuePair first = array[i];
            KeyValuePair second = array[i+1];
            if(first.getKey() ==  second.getKey()){
                if(first.getValue() > second.getValue()){
                    return false;
                }
            }
        }
        return true;
    }
    //creates randomArray of size N
    public static KeyValuePair[] randomArray(int N){
        KeyValuePair[] array = new KeyValuePair[N];
        for(int i = 0; i< N; i++){
            Random rng = new Random();
            array[i] = new KeyValuePair(rng.nextInt(N), i);
        }
        return array;
    }

    public static void main(String[] args) {
        // TODO: implement this
        ISort sortingObjectA = new SorterA();
        ISort sortingObjectB = new SorterB();
        ISort sortingObjectC = new SorterC();
        ISort sortingObjectD = new SorterD();
        ISort sortingObjectE = new SorterE();
        ISort sortingObjectF = new SorterF();

        // --------------------------------------------------------------------------------------------
        // This part is to check the stability of each Sort



        System.out.println("Sorter A Stable? " + SortingTester.isStable(sortingObjectA,500));
        System.out.println("Sorter B Stable? " + SortingTester.isStable(sortingObjectB,500));
        System.out.println("Sorter C Stable? " + SortingTester.isStable(sortingObjectC,500));
        System.out.println("Sorter D Stable? " + SortingTester.isStable(sortingObjectD,500));
        System.out.println("Sorter E Stable? " + SortingTester.isStable(sortingObjectE,500));
        System.out.println("Sorter F Stable? " + SortingTester.isStable(sortingObjectF,500));


        // --------------------------------------------------------------------------------------------

        // This part is to find out the identity of SorterA (problem 1.d)

        //creating 3 sorted arrays

        KeyValuePair[] sortedArray1 = new KeyValuePair[500];
        for(int i =0; i<500; i++){
            sortedArray1[i] = new KeyValuePair(i,i);
        }

        KeyValuePair[] sortedArray2 = new KeyValuePair[500];
        for(int i =0; i<500; i++){
            sortedArray2[i] = new KeyValuePair(i,i);
        }

        KeyValuePair[] sortedArray3 = new KeyValuePair[500];
        for(int i =0; i<500; i++){
            sortedArray3[i] = new KeyValuePair(i,i);
        }


        //creating inverse sorted array
        KeyValuePair[] inverseArray1 = new KeyValuePair[500];
        for(int i = 0; i<500; i++){
            inverseArray1[i] = new KeyValuePair(500-i,500-i);
        }

        KeyValuePair[] inverseArray2 = new KeyValuePair[500];
        for(int i = 0; i<500; i++){
            inverseArray2[i] = new KeyValuePair(500-i,500-i);
        }

        KeyValuePair[] inverseArray3 = new KeyValuePair[500];
        for(int i = 0; i<500; i++){
            inverseArray3[i] = new KeyValuePair(500-i,500-i);
        }

        //Testing
        System.out.println("Sorter A cost when passed SORTED array: " + sortingObjectA.sort(sortedArray1));
        System.out.println("Sorter A cost when passed INVERSE SORTED array: " + sortingObjectA.sort(inverseArray1));

        System.out.println("Sorter C cost when passed SORTED array: " + sortingObjectC.sort(sortedArray2));
        System.out.println("Sorter C cost when passed INVERSE SORTED array: " + sortingObjectC.sort(inverseArray2));

        System.out.println("Sorter F cost when passed SORTED array: " + sortingObjectF.sort(sortedArray3));
        System.out.println("Sorter F cost when passed INVERSE SORTED array: " + sortingObjectF.sort(inverseArray3));



        //--------------------------------------------------------------------------------------------
        // This part shows that after some attempts, sorterB will return an unsorted array,
        // hence proving that sorterB is Mr.Evil


        boolean sorterB = true;
        int trial_num = 1;
        do  {
            sorterB = SortingTester.checkSort(sortingObjectB, 5000);
            System.out.println("Trial number: "+ trial_num + " ,Sorter B Sort? " + sorterB);
            trial_num++;

        } while (sorterB == true);



        // --------------------------------------------------------------------------------------------

        //Comparing sorter C and sorter F (problem 1d.)


        for(int i =0; i< 2; i++) {
            KeyValuePair[] randomArray500 = SortingTester.randomArray(500);
            KeyValuePair[] randomArray5000 = SortingTester.randomArray(5000);
            long costCLow = sortingObjectC.sort(randomArray500);
            long costCHigh = sortingObjectC.sort(randomArray5000);
            long costFLow = sortingObjectF.sort(randomArray500);
            long costFHigh = sortingObjectF.sort(randomArray5000);

            System.out.println("Sorter C:");
            System.out.println("Small Sized Array Cost: " + costCLow);
            System.out.println("Large Sized Array Cost: " + costCHigh);
            System.out.println("Ratio: " + costCHigh / costCLow);

            System.out.println("-----------------");

            System.out.println("Sorter F:");
            System.out.println("Small Sized Array Cost: " + costFLow);
            System.out.println("Large Sized Array Cost: " + costFHigh);
            System.out.println("Ratio: " + costFHigh / costFLow);

            System.out.println("-----------------");
        }

    // --------------------------------------------------------------------------------------------------
        //Comparing sorter D and sorter E

        for(int i =0; i< 2; i++) {
            KeyValuePair[] randomArray500 = SortingTester.randomArray(500);
            KeyValuePair[] randomArray5000 = SortingTester.randomArray(5000);
            long costDLow = sortingObjectD.sort(randomArray500);
            long costDHigh = sortingObjectD.sort(randomArray5000);
            long costELow = sortingObjectE.sort(randomArray500);
            long costEHigh = sortingObjectE.sort(randomArray5000);

            System.out.println("Sorter D:");
            System.out.println("Small Sized Array Cost: " + costDLow);
            System.out.println("Large Sized Array Cost: " + costDHigh);
            System.out.println("Ratio:" + costDHigh / costDLow);

            System.out.println("-----------------");

            System.out.println("Sorter E:");
            System.out.println("Small Sized Array Cost: " + costELow);
            System.out.println("Large Sized Array Cost: " + costEHigh);
            System.out.println("Ratio:" + costEHigh / costELow);

            System.out.println("-----------------");
        }

    }
}
