import java.util.ArrayList;
import java.util.Arrays;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';
    public TrieNode root;

    public class TrieNode { //switched it to public
        // TODO: Create your TrieNode class here.
        public int size = 76; //75 possible childrens + 1 blank
        public TrieNode[] children = new TrieNode[size];
        public char key;
        public boolean end = false;
        int[] presentChars = new int[62];
        public TrieNode(){
            for(int i =0; i<size; i++){
                children[i] = null;
            }
        }
        void printNode(TrieNode node, ArrayList<String> results, int limit, String prefix){
            if(results.size() < limit) {
                if (node == null) {
                    return;
                }
                if (node.end) {
                    results.add(prefix);
                }
                ArrayList<TrieNode> existChildren = new ArrayList<>();
                for (int i = 0; i < 76; i++) {
                    if (node.children[i] != null) {
                        existChildren.add(node.children[i]);
                    }
                }
                for (int i = 0; i < existChildren.size(); i++) {
                    String sChildren = String.valueOf(existChildren.get(i).key);
                    StringBuilder sb = new StringBuilder("");
                    sb.append(prefix).append(sChildren);
                    printNode(existChildren.get(i), results, limit, sb.toString());
                }
            }
        }
    }

    public Trie() {
        // TODO: Initialise a trie class here.
        root = new TrieNode();
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        // TODO
        int length = s.length();
        TrieNode node = root;
        if(length == 0){
            node.children[75] = new TrieNode();
        }
        for(int i = 0; i < length; i++){

            char character = s.charAt(i);
            int ascii = character;
            int index = ascii - 48;
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
                node.children[index].key = (char) ascii;
            }
            node = node.children[index];
        }
        node.end = true;
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        int length = s.length();
        TrieNode node = root;
        if(length == 0 && node.children[75] == null){
            return false;
        } else {
            // TODO

            for (int i = 0; i < length; i++) {
                int index = s.charAt(i) - 48;
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            return node.end;
        }
    }

    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */
    public TrieNode findNode(String s){
        int length = s.length();
        TrieNode node = root;
        for(int i = 0; i<length; i++){
            int index = s.charAt(i) - 48;
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
    void prefixSearch(String s, ArrayList<String> results, int limit) {
        // TODO
        int length = s.length();
        TrieNode node = root;
        boolean hasWildCard = false;
        for(int i = 0; i < length; i++){
            char character = s.charAt(i);
            if(character == WILDCARD){
                hasWildCard = true;
                break;
            }
        }
        if(hasWildCard == false){
            boolean valid = true;
            for(int i = 0; i<length; i++){
                int index = s.charAt(i) - 48;
                if(node.children[index] == null){
                    valid = false;
                    break;
                } else {
                    node = node.children[index];
                }
            }
            if(valid){
                node.printNode(node, results, limit, s);
            }


        } else{
            int wildCardIndex = 0;
            for(int i =0; i<length;i++){
                if(s.charAt(i) == WILDCARD){
                    wildCardIndex = i;
                    break;
                }
            }
            String front = s.substring(0,wildCardIndex);
            TrieNode frontNode = findNode(front);
            if(frontNode == null){
                return;
            }
            String back = s.substring(wildCardIndex+1, length);
            ArrayList<TrieNode> existChildren = new ArrayList<>();
            for (int i = 0; i < 76; i++) {
                if (frontNode.children[i] != null) {
                    existChildren.add(frontNode.children[i]);
                }
            }
            for(int i = 0; i < existChildren.size(); i++) {
                String sChildren = String.valueOf(existChildren.get(i).key);
                StringBuilder sb = new StringBuilder("");
                sb.append(front).append(sChildren).append(back);
                System.out.println(sb);
                if(results.size() < limit) {
                    prefixSearch(sb.toString(), results, limit);
                }
            }

        }
    }


    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {

        //DEFAULT INPUT:

        Trie t = new Trie();
        //t.prefixSearch("lol",10);

        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("of");
        t.insert("pickled");
        t.insert("peppers");
        //t.insert("aaa");
        //t.insert("pepppito");
        //t.insert("pepi");
        //t.insert("pik");
        //t.insert("abbde");
        //t.insert("abcd");
        //t.insert("abcdef");
        //t.insert("abed");
        //t.insert("abba");

        //String[] result1 = t.prefixSearch("", 10);
        //System.out.println(Arrays.toString(result1));
        String[] result2 = t.prefixSearch("..", 3);
        System.out.println(Arrays.toString(result2));




        // result1 should be:
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}
