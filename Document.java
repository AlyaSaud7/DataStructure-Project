package ds;

class Document {

    int DocumentIDnumber;
    LinkedList<String> wordsInDocument;

    public Document() {
        DocumentIDnumber = 0;
        wordsInDocument = new LinkedList<String>();
    }

    //this is an important method to add a word to the list
    public void addNewWordToDoc(String NewWord) {
        wordsInDocument.insert(NewWord);
    }
    //this method is to search for a specefic word the words 

    public boolean foundWordInDoc(String Searchword) {
        if (wordsInDocument.empty()) {
            return false;
        }

        wordsInDocument.findFirst();
        for (int i = 0; i < wordsInDocument.size; i++) {
            if (wordsInDocument.retrieve().compareTo(Searchword) == 0) {
                return true;
            }
            wordsInDocument.findNext();
        }
        return false;
    }
}
