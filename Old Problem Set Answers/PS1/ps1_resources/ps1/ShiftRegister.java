///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////
/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    int size;
    int[] array = new int[size];
    int tap = 0;
    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        this.size = size;
        this.tap = tap;
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    static int MysteryFunction(int argA, int argB) {
        int c = 1;
        int d = argA;
        int e = argB;
        while (e > 0) {
            if (2 * (e / 2) != e) {
                c = c * d;
            }
            d = d * d;
            e = e / 2;
        }
        return c;
    }
    @Override
    public void setSeed(int[] seed) {
        // TODO
        int size = seed.length;
        int[] temp = new int [size];
        for(int i=0; i<size; i=i+1){
            temp[i] = seed[size-1-i];
        }
        array = temp;
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
        int xor = array[0]^array[size-tap-1];
        int[] temp = new int[size];
        for(int i =1; i<size;i=i+1){
            temp[i-1] = array[i];
        }
        array = temp;
        temp[size-1] = xor;
        return xor;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:
        int[] arr = new int[k];
        for(int i =0; i<k; i=i+1){
            int num = shift();
            arr[i] = num;
        }
        return toDecimal(arr);
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    int toDecimal(int[] array) {
        // TODO:
        int length = array.length;
        int sum = 0;
        for(int i=0; i<length;i=i+1){
            sum = sum + MysteryFunction(2,i) * array[length-i-1];
        }
        return sum;
    }

}
