import java.util.*;

public class WordGram implements Comparable<WordGram>{ // implements comparable class so we can compare wordgrams
	
	private int myHash;
	private String[] myWords;
	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size]; // words in wordgram is constructed with a size size
		ArrayList<String> list = new ArrayList<String>();
		for (int i = index; i < size+index; i++) 
			list.add(words[i]); // makes the string of words into a list first
		int ind = 0;
		for (String s:list) { // then adds those words into the array
			myWords[ind] = s; // makes into a list first because we only wanna get the certain words
			ind++;
		}
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < myWords.length; i++) {
			hash += myWords[i].hashCode() * Math.pow(i, i); // to avoid collisions we multiply by Math.pow(i,i)
		}
		myHash = hash;
		return myHash;
	}
	
	@Override
	public String toString() {
		return String.join(" ", myWords); // String.join concatenates the words in a wordgram
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) // if this object is the same as the param, then true
			return true;
		if (other == null || ! (other instanceof WordGram)) // instanceof checks to see if they are the same types
			return false; // if the things being compared aren't word grams then false
		WordGram wg = (WordGram) other; // make a new wordgram other
		// Are the contents of this and wg the same?
		if (myWords.length != wg.myWords.length) // are they the same size?
			return false;
		for (int k = 0; k < myWords.length; k++)
			// check if myWords[k] is equal to wg.myWords[k]
			if (!myWords[k].equals(wg.myWords[k])) // checks the contents of the wordgrams
				return false;
		return true; // we've done the checks, so just return true since we've considered the cases where it'll be false
	}
	
	@Override
	public int compareTo(WordGram wg) { // returns an integer
		if (this.equals(wg)) // if they are the exact same, then yeah return 0
			return 0;
		// ["a", "b", "c"] to ["a", "bob", "c"] have the same size arrays, but do they have the same words
		if (this.myWords.length == wg.myWords.length) { 
			for (int k = 0; k < this.myWords.length; k++) {
				// compare this.myWords[k] to wg.myWords[k]
				int cmp = this.myWords[k].compareTo(wg.myWords[k]); // not recursion, just comparing between a string and another string
				if (cmp != 0) // if they're not the same, then return whether it is greater or less
					return cmp;
			}
		}
		if (this.myWords.length > wg.myWords.length) // if the length of this wordgram is more, then it is greater
			return 1;
		if (this.myWords.length < wg.myWords.length) // else it is less
			return -1;
		return 0;
	}
	
	public int length() {
		return myWords.length; // just checking the length of the array
	}
	
	public WordGram shiftAdd(String last) { // first entry disappears, everything else shifts
		String[] shifted = new String[myWords.length];
		for (int i = 0; i < myWords.length-1; i++) { // shift everything over to the left
			shifted[i] = myWords[i+1];
		}
		shifted[myWords.length-1] = last; // the last element is gonna be whatever word i passed through the method
		return new WordGram(shifted,0,shifted.length);
	}
}
