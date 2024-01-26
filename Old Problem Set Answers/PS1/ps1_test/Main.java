public class Main {
    public static void main(String[] args) {
        ILFShiftRegister r = new ShiftRegister(5, 3);
        int[] seed = { 1, 1, 0, 0, 1 };
        r.setSeed(seed);
        System.out.println(r.generate(0));
    }
}
