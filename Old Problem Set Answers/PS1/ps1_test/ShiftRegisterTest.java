import  static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }
    @Test
    public void testShift2(){
        ILFShiftRegister r = getRegister(4, 2);
        int[] seed = { 1,0, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 0, 1, 0 };
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], r.shift());
        }
    }
    @Test //tests for seed with all zeroes entries and tao = 0
    public void testShift3(){
        ILFShiftRegister r = getRegister(5, 0);
        int[] seed = { 0, 0, 0, 0, 0 };
        r.setSeed(seed);
        int[] expected = {0, 0, 0, 0, 0};
        for(int i =0; i<5; i++){
            assertEquals(expected[i],r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    @Test
    public void testGenerate2(){
        ILFShiftRegister r = getRegister(5, 3);
        int[] seed = {1, 1, 0, 0, 1};
        r.setSeed(seed);
        int[] expected = {10, 7 };
        for (int i = 0; i < 2; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(4));
        }
    }
    @Test
    public void testGenerate3(){
        ILFShiftRegister r = getRegister(7, 6);
        int[] seed = {1, 0, 0, 1, 1, 0, 1, 1};
        r.setSeed(seed);
        int[] expected = {0, 0, 0 };
        for (int i = 0; i < 3; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(2));
        }
    }

    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }
}
// A Proper response would be to compare the length of the input array and the size. If they are not equal,
// then it should print out an error message.