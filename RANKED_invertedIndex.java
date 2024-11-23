package ds;

public class RANKED_invertedIndex {
            
           
            LinkedList <RankTermDocID> invertedindexWordDocNum; 
            FreqInDoc [] WordFrequency;
            
            public RANKED_invertedIndex() {
                invertedindexWordDocNum = new LinkedList <RankTermDocID>();
                WordFrequency = new FreqInDoc[50];
            }
            
            public int size()
            {
                return invertedindexWordDocNum.size();
            }

            public boolean addNewWordToDocumentt (int DocumentNumber, String word)
            {
                if (invertedindexWordDocNum.empty())
               {
                   RankTermDocID t = new RankTermDocID ();
                    t.setVocabulary(new VocabWord (word));
                    t.IncrementDocument(DocumentNumber);
                    invertedindexWordDocNum.insert(t);
                    return true;
               }
               else
               {
                    invertedindexWordDocNum.findFirst();
                    while ( ! invertedindexWordDocNum.last())
                    {
                        if ( invertedindexWordDocNum.retrieve().word.word.compareTo(word) == 0)
                        {
                            RankTermDocID t = invertedindexWordDocNum.retrieve();
                            t.IncrementDocument(DocumentNumber);
                            invertedindexWordDocNum.update(t);
                            return false;
                        }
                       invertedindexWordDocNum.findNext();
                    }
                    if ( invertedindexWordDocNum.retrieve().word.word.compareTo(word) == 0)
                    {
                        RankTermDocID t = invertedindexWordDocNum.retrieve();
                        t.IncrementDocument(DocumentNumber);
                        invertedindexWordDocNum.update(t);
                        return false;
                    }
                    else
                    {
                        RankTermDocID t = new RankTermDocID ();
                        t.setVocabulary(new VocabWord (word));
                        t.IncrementDocument(DocumentNumber);
                        invertedindexWordDocNum.insert(t);
                    }
                    return true;
           }
        }

  public boolean found(String word)
        {
               if (invertedindexWordDocNum.empty())
                   return false;

               invertedindexWordDocNum.findFirst();
               for ( int i = 0 ; i < invertedindexWordDocNum.size ; i++)
               {
                   if ( invertedindexWordDocNum.retrieve().word.word.compareTo(word) == 0)
                       return true;
                  invertedindexWordDocNum.findNext();
               }
               return false;
        }
 
        public String printDocment(){
            String print="";
            if (this.invertedindexWordDocNum.empty())
               print="Empty Inverted Index";
            else
            {
                this.invertedindexWordDocNum.findFirst();
                while ( ! this.invertedindexWordDocNum.last())
                {
                    print+=invertedindexWordDocNum.retrieve();
                    this.invertedindexWordDocNum.findNext();
                }
                print+=invertedindexWordDocNum.retrieve();
            }
            return print;
        }

        
        
        
 public String FrequencyCount(String str) {
    str = str.toLowerCase().trim();
    String[] words = str.split(" "); 
    WordFrequency = new FreqInDoc[50]; 

    // Initialize `WordFrequency` objects
    for (int i = 0; i < 50; i++) {
        WordFrequency[i] = new FreqInDoc();
        WordFrequency[i].docID = i;
        WordFrequency[i].f = 0;
    }

    // Iterate through each word
    for (String word : words) {
        if (found(word)) { // Check if the word exists in the inverted index
            RankTermDocID termDocID = invertedindexWordDocNum.retrieve(); 
            int[] docs = termDocID.getDocumentss(); 

            
            for (int docID = 0; docID < docs.length; docID++) {
                if (docs[docID] != 0) {
                    WordFrequency[docID].f += docs[docID];
                }
            }
        }
    }

   
    mergeSort(WordFrequency, 0, WordFrequency.length - 1);

    // Print  sorted results
    String print="";
    print +="\nDocID\tScore\n";
    for (FreqInDoc freq : WordFrequency) {
        if (freq.f != 0) { // Only print non-zero frequencies
            print +=freq.docID + "\t\t" + freq.f+"\n";
        }
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