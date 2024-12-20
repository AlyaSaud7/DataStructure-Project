package ds;

public class RANKED_invertedIndexBST {
           
          
           BST <Integer, BST <String,WordRank>> BSTtreeRanked; 
           FreqInDoc [] WordFrequency = new FreqInDoc[50];
            
            
            
            public RANKED_invertedIndexBST() {
                BSTtreeRanked = new BST <Integer, BST <String,WordRank>>();
                
            }

            public boolean addNewDocumentToBST (int DocumentNumber, String word)
            {
               if (BSTtreeRanked.empty())
               {
                   BST <String,WordRank> FirstRanking= new BST <String,WordRank>();
                   FirstRanking.insert(word, new WordRank (word,1));
                   
                   BSTtreeRanked.insert(DocumentNumber, FirstRanking);
                   return true;
               }
               else
               {
                    if (BSTtreeRanked.find(DocumentNumber))
                    {
                        BST <String,WordRank> FirstRanking= BSTtreeRanked.retrieve();
                        if (FirstRanking.find(word))
                        {
                            // document available , word avialble // rank ++
                            WordRank rank = FirstRanking.retrieve();
                            rank.add_Rank();
                            FirstRanking.update(rank);
                            BSTtreeRanked.update(FirstRanking);
                            return false;
                        }
                        //  document available , word unavailable 
                        FirstRanking.insert(word, new WordRank (word , 1));
                        BSTtreeRanked.update(FirstRanking);
                        return true;
                    }
                    // document unavailable 
                   BST <String,WordRank> FirstRanking= new BST <String,WordRank>();
                   FirstRanking.insert(word, new WordRank (word,1));
                   
                   BSTtreeRanked.insert(DocumentNumber, FirstRanking);
                   return true;
               } 
        }

        public boolean found(int DocumentNumber, String word)
        {
               if (BSTtreeRanked.find(DocumentNumber) )
                  if (BSTtreeRanked.retrieve().find(word))
                      return true;
               return false;
        }
        
        public int getrankofDocumentBST (int DocumentNumber, String word)
        {
            int val = 0;
               if (BSTtreeRanked.find(DocumentNumber) )
                  if (BSTtreeRanked.retrieve().find(word))
                      return BSTtreeRanked.retrieve().retrieve().getWordRanking();
               return val;
            
        }
        public void printDocument()
        {
                BSTtreeRanked.TraverseT();
        }

       
        
        
    public String FrequencyCount(String str) {
    str = str.toLowerCase().trim(); 
    String[] words = str.split(" "); 

    FreqInDoc[] tempWordFrequency = new FreqInDoc[50]; 
    int index = 0;

    for (int docID = 0; docID < 50; docID++) {
        int totalFrequency = 0;

        // Sum the rank of each word for the document
        for (String word : words) {
            totalFrequency += this.getrankofDocumentBST(docID, word);
        }

        // If the document contains any of the words, store its frequency
        if (totalFrequency > 0) {
            tempWordFrequency[index] = new FreqInDoc();
            tempWordFrequency[index].docID = docID;
            tempWordFrequency[index].f = totalFrequency;
            index++;
        }
    }

    
    mergeSort(tempWordFrequency, 0, index - 1);

    // Print the sorted results
    String print="";
    print+= "\nDocID\tScore\n";
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