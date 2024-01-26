import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no character
	public static final char NOCHARACTER = (char) 0;

	private int order = 1;

	private HashMap<String, int[]> hashmap;
	private HashMap<String, Integer> hashmapFrequency;
	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of characters to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		this.order = order;
		this.hashmap = new HashMap<>();
		this.hashmapFrequency = new HashMap<>();
		// Initialize the random number generator
		generator.setSeed(seed);
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model here

		for(int i =0; i<= text.length()-(this.order+1); i++) {
			int[] countArray = new int[256];
			String str = text.substring(i,i+this.order);
			char nextLetter = text.charAt(i+this.order);
			int ascii = nextLetter;

			if(this.hashmap.containsKey(str)) {
				int[] arr = this.hashmap.get(str);
				arr[ascii] = arr[ascii] + 1;
				this.hashmap.replace(str, arr);
			} else {
				countArray[ascii] = countArray[ascii] + 1;
				this.hashmap.put(str, countArray);
			}

			if(this.hashmapFrequency.containsKey(str)) {
				int frequency = this.hashmapFrequency.get(str);
				frequency = frequency +1;
				this.hashmapFrequency.replace(str, frequency);
			} else {
				this.hashmapFrequency.put(str, 1);
			}
		}

	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		if(this.hashmapFrequency.containsKey(kgram)) {
			return this.hashmapFrequency.get(kgram);
		} else {
			return 0;
		}
	}

	/**
	 * Returns the number of times the character c appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, char c) {
		if(this.hashmap.containsKey(kgram)) {
			int [] arr = this.hashmap.get(kgram);
			int ascii = c;
			return arr[ascii];
		} else {
			return 0;
		}
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOCHARACTER if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public char nextCharacter(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.
		if(!this.hashmap.containsKey(kgram)) {
			return NOCHARACTER;
		}
		HashMap<Character, Integer> hm = new HashMap<>();
		int frequency = this.hashmapFrequency.get(kgram);
		Character[] list = new Character[frequency];
		int j = 0;
		int[] arr = this.hashmap.get(kgram);
		for(int i = 0; i < 256; i++) {
			char c = (char) i;
			if(arr[i]!=0) {
				hm.put(c, arr[i]);
				for(int k = 0; k < arr[i]; k++) {
					list[j] = c;
					j++;
				}
			}
		}
		Arrays.sort(list);
		int randomNumber = generator.nextInt(frequency);
		//System.out.println(Arrays.toString(list));
		return list[randomNumber];
	}
	public static void main(String[]args) {
		MarkovModel mm = new MarkovModel(2,2);
		mm.initializeText("qwertyqwerty");

	}
}
