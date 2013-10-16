class LongestChain
{
    private Queue q; // k� som anv�nds i breddenf�rsts�kningen
    private String goalWord; // slutord i breddenf�rsts�kningen
    int wordLength;
    final char[] alphabet =
	    { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		    'x', 'y', 'z', '�', '�', '�', '�' };
    int alphabetLength = alphabet.length;

    public LongestChain(int wordLength) {
	this.wordLength = wordLength;
	q = new Queue();
    }

    // IsGoal kollar om w �r slutordet.
    private boolean IsGoal(String w) {
	return w.equals(goalWord);
    }

    // MakeSons skapar alla ord som skiljer p� en bokstav fr�n x.
    // Om det �r f�rsta g�ngen i s�kningen som ordet skapas s� l�ggs det
    // in i k�n q.
    private WordRec MakeSons(WordRec x) {
	for (int i = 0; i < wordLength; i++) {
	    for (int c = 0; c < alphabetLength; c++) {
		if (alphabet[c] != x.word.charAt(i)) {
		    String res = WordList.Contains(x.word.substring(0, i) +
						   alphabet[c] +
						   x.word.substring(i + 1));
		    if (res != null && WordList.MarkAsUsedIfUnused(res)) {
			WordRec wr = new WordRec(res, x);
			if (IsGoal(res)) return wr;
			q.Put(wr);
		    }
		}
	    }
	}
	return null;
    }

    // BreadthFirst utf�r en breddenf�rsts�kning fr�n startWord f�r att
    // hitta kortaste v�gen till endWord. Den kortaste v�gen returneras
    // som en kedja av ordposter (WordRec).
    // Om det inte finns n�got s�tt att komma till endWord returneras null.
    public WordRec BreadthFirst(String startWord, String endWord) {
	WordList.EraseUsed();
	WordRec start = new WordRec(startWord, null);
	WordList.MarkAsUsedIfUnused(startWord);
	goalWord = endWord;
	q.Empty();
	q.Put(start);
	try {
	    while (true) {
		WordRec wr = MakeSons((WordRec) q.Get());
		if (wr != null) return wr;
	    }
	} catch (Exception e) {
	    return null;
	}
    }

    // CheckAllStartWords hittar den l�ngsta kortaste v�gen fr�n n�got ord
    // till endWord. Den l�ngsta v�gen skrivs ut.
    public void CheckAllStartWords(String endWord) {
	int maxChainLength = 0;
	WordRec maxChainRec = null;
	for (int i = 0; i < WordList.size; i++) {
	    WordRec x = BreadthFirst(WordList.WordAt(i), endWord);
	    if (x != null) {
		int len = x.ChainLength();
		if (len > maxChainLength) {
		    maxChainLength = len;
		    maxChainRec = x;
		    // x.PrintChain(); // skriv ut den hittills l�ngsta kedjan
		}
	    }
	}
	System.out.println(endWord + ": " + maxChainLength + " ord");
	if (maxChainRec != null) maxChainRec.PrintChain();
    }
}
