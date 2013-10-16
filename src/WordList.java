import java.util.HashMap;
import java.util.Vector;
import java.io.*;

// Klassen WordList inneh�ller en ordlista och en datastruktur som h�ller
// reda p� anv�nda ord.

class WordList
{
    static private HashMap<String, Boolean> list; // ordlista
    //static private Vector<String> used; // databas med anv�nda ord
    static int wordLength;
    static int size; // antal ord i ordlistan

    // Read l�ser in en ordlista fr�n str�mmen input. Alla ord ska ha
    // wordLength bokst�ver.
    static public void Read(int wordLength_, BufferedReader input) throws IOException {
	wordLength = wordLength_;
	size = 0;
	list = new HashMap<String, Boolean>();
	while (true) {
	    String s = input.readLine();
	    if (s.equals("#")) break;
	    if (s.length() != wordLength) System.out.println("Rad " + size +
							     " i filen inneh�ller inte " +
							     wordLength + " tecken.");
	    //list.add(s);
	    list.put(s, false); // used is false before being used
	    size++;
	}
	//used = new HashMap<String, String>(size);
    }

    // WordAt returnerar ordet med angivet index i ordlistan.
    /*static public String WordAt(int index) {
	if (index >= 0 && index < size) return (String) list.elementAt(index);
	else return null;
    }*/

    // Contains sl�r upp w i ordlistan och returnerar ordet om det finns med.
    // Annars returneras null.
    static public String Contains(String w) {
	if (list.containsKey(w)) {
	    return w;
	}
	return null;
    }
    /*static public String Contains(String w) {
	if (list.contains(w)) return w;
	else return null;
    }*/

    // MarkAsUsedIfUnused kollar om w �r anv�nt tidigare och returneras i s�
    // fall false. Annars markeras w som anv�nt och true returneras.
    static public boolean MarkAsUsedIfUnused(String w) {
	if (!list.get(w)) {
	    list.put(w, true);
	    return true;
	}
	return false;
    }
    /*static public boolean MarkAsUsedIfUnused(String w) {
	if (used.contains(w)) return false;
	used.add(w);
	return true;
    }*/

    // EraseUsed g�r s� att inga ord anses anv�nda l�ngre.
    static public void EraseUsed() {
	for (String word : list.keySet()) {
	    list.put(word, false);
	}
    }
    /*static public void EraseUsed() {
	used.clear();
    }*/

}
