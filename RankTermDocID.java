package ds;

public class RankTermDocID {
    VocabWord word;
    int  [] DocArray ;

    
    
    public RankTermDocID() {
        DocArray = new int [50];
        for (int i = 0 ; i < DocArray.length ; i++)
            DocArray [i] = 0;
        word = new VocabWord("");
    }

    
    public RankTermDocID(String word, int [] DocArray) {
        this.word = new VocabWord(word);
        this.DocArray = new int [DocArray.length];
        for (int i = 0 ; i < DocArray.length ; i++)
            this.DocArray [i] = DocArray[i];

    }
    
    //if later on found that a word exists in a document the documents index is incremented
    public void IncrementDocument ( int DocArray)
    {
        this.DocArray[DocArray] ++;
    }

    public void setVocabulary(VocabWord word)
    {
        this. word = word; 
    }
    
    public VocabWord getVocabulary()
    {
         return word;
    }
    
    public int [] getDocumentss ()
    {
        int [] test = new int [DocArray.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = DocArray[i];
        return test;
    }
    
    
    
       
     //this is a method if i wanted to display each word and the documents in exists in
      public String toString() {
        String docs = "";
        for (int i = 0, j = 0 ; i < DocArray.length; i++)
            if ( j == 0 && DocArray [i] != 0 )
            {
                docs += " " + String.valueOf(i) ;
                j++;
            }
            else if ( DocArray [i] != 0 )
            {
                docs += ", " + String.valueOf(i) ;
                j++;
            }
        
        return word + "{" + docs + '}';
    } 
    
    
}