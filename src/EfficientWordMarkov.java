import java.util.*;

public class EfficientWordMarkov extends WordMarkovModel{
	private Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int k) {
		super(k); // we are taking in the other constructor which has default order 3
		myMap = new HashMap<WordGram,ArrayList<String>>(); // bc dont really care about order
	}
	
	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+"); // split the text every one or more whitespace char
		myMap.clear(); // clears the map
		int pos = myOrder-1; // like the efficient markov, we cant start from 0 bc what if order is large
		while (pos < myWords.length) { 
			WordGram wordG = new WordGram(myWords,pos-myOrder+1,myOrder); // this is the wordgram we are looking at rn
			if (pos < myWords.length - 1) { // if we are not at the last word
				if (!myMap.containsKey(wordG)) { // if we dont have that wordgram in the map yet, add it
					myMap.put(wordG, new ArrayList<String>());
					myMap.get(wordG).add(myWords[pos+1]);
				}
				else if (myMap.containsKey(wordG)) // if we already have it, then just add the next word to map
					myMap.get(wordG).add(myWords[pos+1]);
			}
			else if (pos == myWords.length-1) { // are we at the last word
				if (!myMap.containsKey(wordG)) {
					myMap.put(wordG, new ArrayList<String>());
					myMap.get(wordG).add(PSEUDO_EOS);
				}
				else if (myMap.containsKey(wordG))
					myMap.get(wordG).add(PSEUDO_EOS);
			}
			pos++; // increment so we can go to next word
		}
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key) throws NoSuchElementException{
		if (myMap.containsKey(key))
			return myMap.get(key);
		else
			throw new NoSuchElementException("There's no such element!");
	}
}