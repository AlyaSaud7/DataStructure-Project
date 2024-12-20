package ds;

public class RANKED_index {

  
       
       
    Document [] DocumentArrayofIndex;
    FreqInDoc [] WordFrequency;

    
    public RANKED_index() {
        WordFrequency = new FreqInDoc [50];
        DocumentArrayofIndex = new Document [50];
        for ( int i = 0 ; i < DocumentArrayofIndex.length ; i++)
        {
            DocumentArrayofIndex [i] = new Document();
            DocumentArrayofIndex [i].DocumentIDnumber = i;
        } 
    }
     
    
    public void addDocumentToArray ( int DocumentNumber, String word)
    {
            DocumentArrayofIndex[DocumentNumber].addNewWordToDoc(word);
    }  
    
    
    
    
    public String printDocment (int DocumentNumber)
    {
        String print="";
        if ( DocumentArrayofIndex[DocumentNumber].wordsInDocument.empty())
            print="Empty Document";
        else 
        {
            DocumentArrayofIndex[DocumentNumber].wordsInDocument.findFirst();
            for ( int i = 0; i< DocumentArrayofIndex[DocumentNumber].wordsInDocument.size ; i++)
            {
                print+= DocumentArrayofIndex[DocumentNumber].wordsInDocument.retrieve() + " \n";
                DocumentArrayofIndex[DocumentNumber].wordsInDocument.findNext();
            }
        }return print;
    }
    
     
public  boolean [] getBooleanDocsArray (String Word)
{ 
    boolean [] result = new boolean [50];
    for (int i = 0 ; i < result.length ; i++)
        result[i] = false;
    
    for (int i = 0 ; i < result.length ; i++)
        if (DocumentArrayofIndex[i].foundWordInDoc(Word))
            result[i] = true;
  
    return result;
}
 
public String FrequencyCount (String str) {
    str = str.toLowerCase().trim(); 
    String[] words = str.split(" "); 
    WordFrequency = new FreqInDoc[50];

    for (int i = 0; i < 50; i++) {
        WordFrequency[i] = new FreqInDoc(); // Initialize each `FreqInDoc` object
        WordFrequency[i].docID = i;
        WordFrequency[i].f = 0; // Set initial frequency to 0
    }
     int docID = 0;
    while (docID < 50) { 
        for (String word : words) { 
            DocumentArrayofIndex[docID].wordsInDocument.findFirst(); // Start at the beginning of the document's word list
            int wordCount = 0; // Initialize count for the current word

            for (int x = 0; x < DocumentArrayofIndex[docID].wordsInDocument.size(); x++) {
                
                if (DocumentArrayofIndex[docID].wordsInDocument.retrieve().equals(word)) {
                    wordCount++; 
                }
                DocumentArrayofIndex[docID].wordsInDocument.findNext(); 
            }

            if (wordCount > 0) {
                WordFrequency[docID].f += wordCount; // Add the count to the frequency
            }
        }  
        docID++;
    }

    // Sort the WordFrequency array using mergeSort
    mergeSort(WordFrequency, 0, WordFrequency.length - 1);

    // Print the results
    String print="";
    print+="\nDocID\tScore\n";
    for (FreqInDoc freq : WordFrequency) {
        if (freq.f != 0) { // Only print documents with non-zero frequencies
           print+= freq.docID + "\t\t" + freq.f+"\n";
        }
    }
    return print;
}


        
        ////////////MERGE SORT METHOD TO ORGANIZE//////////////////// 
        
        public static void mergeSort(FreqInDoc[] array, int left, int right) {
    if (left >= right) {
        return; // Base case
    }

    // Find the middle point
    int mid = left + (right - left) / 2;

   
    mergeSort(array, left, mid);          // Sort first half
    mergeSort(array, mid + 1, right);     // Sort second half

    // Merge the sorted halves
    merge(array, left, mid, right);
}
   
private static void merge(FreqInDoc[] array, int left, int mid, int right) {
    
    int leftSize = mid - left + 1;
    int rightSize = right - mid;

    FreqInDoc[] leftArray = new FreqInDoc[leftSize];
    FreqInDoc[] rightArray = new FreqInDoc[rightSize];

 
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