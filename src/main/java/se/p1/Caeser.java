package se.p1;

public class Caeser {

    private char key = 1;

    public String encrypt(String text){
        StringBuilder sb = new StringBuilder();
        // convert string to `char[]` array
        char[] chars = text.toCharArray();

        // iterate over `char[]` array using enhanced for-loop
        for (char ch: chars) {
            int nextValue = (int)ch + key;
            char c = (char)nextValue;
            sb.append(String.valueOf(c));
        }


        return sb.toString();
    }

    public String decrypt(String text){
        StringBuilder sb = new StringBuilder();
        // convert string to `char[]` array
        char[] chars = text.toCharArray();

        // iterate over `char[]` array using enhanced for-loop
        for (char ch: chars) {
            int nextValue = (int)ch - key;
            char c = (char)nextValue;
            sb.append(String.valueOf(c));
        }


        return sb.toString();
    }
}
