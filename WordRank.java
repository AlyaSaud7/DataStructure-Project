package ds;


public class WordRank {
    VocabWord word;
    int WordRanking ;

    public WordRank() {
        WordRanking = 0;
        word = new VocabWord("");
    }

    public WordRank(String word, int ranking) {
        this.word = new VocabWord(word);
        this.WordRanking = ranking ;
    }
    
    //increments ranking 
    public int add_Rank ()
    {
        return ++WordRanking;
    }
   
    public void setVocab(VocabWord word)
    {
        this. word = word; 
    }
    
    public VocabWord getVocab()
    {
         return word;
    }
    
    public int getWordRanking ()
    {
        return this.WordRanking;
    }
    
    
    public String toString() {
        return "(" + word + ", " + WordRanking + ")" ;
    }
    
    
}
