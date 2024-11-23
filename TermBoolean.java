package ds;

public class TermBoolean {

    VocabWord word;
    boolean[] documentNumbersArray;

    public TermBoolean() {
        documentNumbersArray = new boolean[50];
        for (int i = 0; i < documentNumbersArray.length; i++) {
            documentNumbersArray[i] = false;
        }
        word = new VocabWord("");
    }

    public TermBoolean(String word, boolean[] documentNumbersArray) {
        this.word = new VocabWord(word);
        this.documentNumbersArray = new boolean[documentNumbersArray.length];
        for (int i = 0; i < documentNumbersArray.length; i++) {
            this.documentNumbersArray[i] = documentNumbersArray[i];
        }

    }

    public boolean FoundInDoc(int docID) {
        if (!documentNumbersArray[docID]) {
            this.documentNumbersArray[docID] = true;
            return true;
        }
        return false;
    }

    public void setVocab(VocabWord word) {
        this.word = word;
    }

    public VocabWord getVocab() {
        return word;
    }

    public boolean[] getDocs() {
        boolean[] result = new boolean[documentNumbersArray.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = documentNumbersArray[i];
        }

        return result;
    }

    //this is a method that is used to display the boolean retrieval results  
    public String toString() {
        String docs = "";

        for (int i = 0, j = 0; i < documentNumbersArray.length; i++) {
            if (j == 0 && documentNumbersArray[i] == true) {
                docs += " " + String.valueOf(i);
                j++;
            } else if (documentNumbersArray[i] == true) {
                docs += ", " + String.valueOf(i);
                j++;
            }
        }

        return word + "{" + docs + '}';
    }

}
