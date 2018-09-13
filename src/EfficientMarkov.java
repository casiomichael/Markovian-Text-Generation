import java.util.ArrayList;
import java.util.Map;
import java.util.*;

public class EfficientMarkov extends MarkovModel {
	private Map<String,ArrayList<String>> myMap;

	public EfficientMarkov(int k) {
		super(k); // since this extends markov model, it is inheriting MarkovModel's other constructor
		myMap = new HashMap<String,ArrayList<String>>();

	}

	public void setTraining(String text){
		myText = text; // sets myText as the text taken in
		myMap.clear(); // clears the map
		int pos = myOrder - 1; // starts at the index where the order is less than one
		int start = myOrder - 1; // this is basically where the k-gram starts
		while (pos < myText.length()) { // this loop is gonna increment pos each time
			if (pos < myText.length() - 1) { // if the position is not the last character
				if (!myMap.containsKey(myText.substring(pos-start, pos+1))) {
					myMap.put(myText.substring(pos-start,pos+1), new ArrayList<String>());
					myMap.get(myText.substring(pos-start,pos+1)).add(Character.toString(myText.charAt(pos+1)));
				}
				else if (myMap.containsKey(myText.substring(pos-start,pos+1)))
					myMap.get(myText.substring(pos-start,pos+1)).add(Character.toString(myText.charAt(pos+1)));
			}
			else if (pos == myText.length()-1) { // if i'm at the last character, there is no next letter, so add PSEUDO
				if (!myMap.containsKey(myText.substring(pos-start, pos+1))) {
					myMap.put(myText.substring(pos-start,pos+1), new ArrayList<String>());
					myMap.get(myText.substring(pos-start,pos+1)).add(PSEUDO_EOS);
				}
				else if (myMap.containsKey(myText.substring(pos-start,pos+1)))
					myMap.get(myText.substring(pos-start,pos+1)).add(PSEUDO_EOS);
			}
			pos++;
		}
	}
	@Override
	public ArrayList<String> getFollows(String key) throws NoSuchElementException{
		//use the map to return the next strings that follow the key
		if (myMap.containsKey(key))
			return myMap.get(key); // gets the values of that key
		else
			throw new NoSuchElementException("There's no such element!");
	}

}
