package ds;

public class RANKED_invertedIndexAVL {
          
    
           AVL <Integer, AVL <String,WordRank>> AVLTreeRanked; 
           FreqInDoc [] WordFrequency = new FreqInDoc[50];
            
            
            
            public RANKED_invertedIndexAVL() {
                AVLTreeRanked = new AVL <Integer, AVL <String,WordRank>>();
                
            }

            public boolean addNewWordToAVL (int DocumentNumber, String word)
            {
               if (AVLTreeRanked.empty())
               {
                   AVL <String,WordRank> FirstRanking= new AVL <String,WordRank>();
                   FirstRanking.insert(word, new WordRank (word,1));
                   
                   AVLTreeRanked.insert(DocumentNumber, FirstRanking);
                   return true;
               }
               else
               {
                    if (AVLTreeRanked.find(DocumentNumber))
                    {
                        AVL <String,WordRank> FirstRanking= AVLTreeRanked.retrieve();
                        if (FirstRanking.find(word))
                        {
                            // document available , word avialble // rank ++
                            WordRank rank = FirstRanking.retrieve();
                            rank.add_Rank();
                            FirstRanking.update(rank);
                            AVLTreeRanked.update(FirstRanking);
                            return false;
                        }
                        //  document available , word unavailable 
                        FirstRanking.insert(word, new WordRank (word , 1));
                        AVLTreeRanked.update(FirstRanking);
                        return true;
                    }
                    // document unavailable 
                   AVL <String,WordRank> FirstRanking= new AVL <String,WordRank>();
                   FirstRanking.insert(word, new WordRank (word,1));
                   
                   AVLTreeRanked.insert(DocumentNumber, FirstRanking);
                   return true;
               } 
        }

        public boolean found(int DocumentNumber, String word)
        {
               if (AVLTreeRanked.find(DocumentNumber) )
                  if (AVLTreeRanked.retrieve().find(word))
                      return true;
               return false;
        } 
        
        public int getrankofDocumentAVL (int DocumentNumber, String word)
        {
            int value = 0;
               if (AVLTreeRanked.find(DocumentNumber) )
                  if (AVLTreeRanked.retrieve().find(word))
                      return AVLTreeRanked.retrieve().retrieve().getWordRanking();
               return value;
            
        }
        public void printDocument()
        {
                AVLTreeRanked.TraverseT();
        }

        
public String FrequencyCount(String str) {
    str = str.toLowerCase().trim();
    String[] words = str.split(" "); 

    FreqInDoc[] tempWordFrequency = new FreqInDoc[50]; 
    int index = 0;

  
    for (int docID = 0; docID < 50; docID++) {
        int totalFrequency = 0;

        // Check the rank of each word in the document
        for (String word : words) {
            totalFrequency += this.getrankofDocumentAVL(docID, word);
        }

        // If the document contains the word store the frequency
        if (totalFrequency > 0) {
            tempWordFrequency[index] = new FreqInDoc();
            tempWordFrequency[index].docID = docID;
            tempWordFrequency[index].f = totalFrequency;
            index++;
        }
    }

   
    mergeSort(tempWordFrequency, 0, index - 1);

    //print sorted results
    String print="";
    print+="\nDocID\tScore\n";
    for (int i = 0; i < index; i++) {
       print+=tempWordFrequency[i].docID + "\t\t" + tempWordFrequency[i].f+"\n";
    }
    return print;
}    
    ////////////MERGE SORT METHOD TO ORGANIZE////////////////////
        
        
    public static void mergeSort(FreqInDoc[] array, int left, int right) {
    if (left >= right) {
        return; // Base case: single element or invalid range
    }

    // Find the middle point
    int mid = left + (right - left) / 2;

    // Sort the two halves
    mergeSort(array, left, mid);          // Sort first half
    mergeSort(array, mid + 1, right);     // Sort second half

    // Merge the sorted halves
    merge(array, left, mid, right);
}
  
private static void merge(FreqInDoc[] array, int left, int mid, int right) {
    // Create temporary arrays for left and right halves
    int leftSize = mid - left + 1;
    int rightSize = right - mid;

    FreqInDoc[] leftArray = new FreqInDoc[leftSize];
    FreqInDoc[] rightArray = new FreqInDoc[rightSize];

    // Copy data into temporary arrays
    for (int i = 0; i < leftSize; i++) {
        leftArray[i] = array[left + i];
    }
    for (int i = 0; i < rightSize; i++) {
        rightArray[i] = array[mid + 1 + i];
    }

    // Merge the temporary arrays back into the original array
    int i = 0, j = 0, k = left;

    while (i < leftSize && j < rightSize) {
        // Compare frequencies (descending order)
        if (leftArray[i].f >= rightArray[j].f) {
            array[k] = leftArray[i];
            i++;
        } else {
            array[k] = rightArray[j];
            j++;
        }
        k++;
    }

    // Copy remaining elements of leftArray, if any
    while (i < leftSize) {
        array[k] = leftArray[i];
        i++;
        k++;
    }

    // Copy remaining elements of rightArray, if any
    while (j < rightSize) {
        array[k] = rightArray[j];
        j++;
        k++;
    }
}   
    

}