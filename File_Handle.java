package ds;

import java.io.File;
import java.util.Scanner;

public class File_Handle {

    int Tokens = 0;
    int vocabWords = 0;

    //create objects for all datastructures used 
    Index index;
    Inverted_Index invertedindex;
    Inverted_IndexBST invertedindexBST;
    Inverted_IndexAVL invertedindexAVL;
    RANKED_index indexranked;
    RANKED_invertedIndex invertedindexranked;
    RANKED_invertedIndexBST invertedindexBSTranked;
    RANKED_invertedIndexAVL invertedindexAVLranked;

    //initialize the objects
    public File_Handle() {

        index = new Index();
        invertedindex = new Inverted_Index();
        invertedindexBST = new Inverted_IndexBST();
        invertedindexAVL = new Inverted_IndexAVL();
        indexranked = new RANKED_index();
        invertedindexranked = new RANKED_invertedIndex();
        invertedindexBSTranked = new RANKED_invertedIndexBST();
        invertedindexAVLranked = new RANKED_invertedIndexAVL();

    }

    public void LoadData(String StopWords, String DocumentFile) {
        try {
            //read the documents file and the stop words file
            File StopWordsFile = new File(StopWords);
            Scanner ReadStopWords = new Scanner(StopWordsFile).useDelimiter("\\Z");
            String stops = ReadStopWords.next();
            stops = stops.replaceAll("\n", " ");
            File DocumentWordFile = new File(DocumentFile);
            Scanner ReadDocumentFile = new Scanner(DocumentWordFile);
            String Eachline = ReadDocumentFile.nextLine();

            //manipulate each line so we can get the document number and all the words in each document seperatly
            for (int lineNumber = 0; lineNumber < 50; lineNumber++) {
                Eachline = ReadDocumentFile.nextLine().toLowerCase();
                int Comma_position = Eachline.indexOf(',');
                int documentNumber = Integer.parseInt(Eachline.substring(0, Comma_position));
                String TheSentence = Eachline.substring(Comma_position + 1, Eachline.length() - Comma_position).trim();
                TheSentence = TheSentence.substring(0, TheSentence.length() - 1);
                TheSentence = TheSentence.toLowerCase();
                TheSentence = TheSentence.replaceAll("[\']", "");
                TheSentence = TheSentence.replaceAll("[^a-zA-Z0-9]", " ").trim();

                //split on the space so we can get each word indivisually
                String[] wordsArray = TheSentence.split(" "); // --1

                for (int i = 0; i < wordsArray.length; i++) {
                    String Single_word = wordsArray[i].trim(); //--2

                    if (Single_word.compareToIgnoreCase("") != 0) {
                        Tokens++;
                    }
                    //fill in the words in ALL data structures to make use of them in ranking and retrieval
                    if (!stops.contains(Single_word + " ")) //--3
                    {
                        this.index.addDocument(documentNumber, Single_word);
                        this.invertedindex.addNew(documentNumber, Single_word);
                        this.invertedindexBST.addNew(documentNumber, Single_word);
                        this.invertedindexAVL.addNew(documentNumber, Single_word);
                        this.indexranked.addDocumentToArray(documentNumber, Single_word);
                        this.invertedindexranked.addNewWordToDocumentt(documentNumber, Single_word);
                        this.invertedindexBSTranked.addNewDocumentToBST(documentNumber, Single_word);
                        this.invertedindexAVLranked.addNewWordToAVL(documentNumber, Single_word);
                    }
                }

            }

            //since we filled ALL words into the data structure we can obtain the total number of words through the size of the data structure
            vocabWords = invertedindexBST.InvertedIndexBSTtree.size();

            //close the reading from file
            ReadStopWords.close();
            ReadDocumentFile.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
