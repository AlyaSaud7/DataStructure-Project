package ds;

public class Index {

    Document[] DocumentArrayofIndex;

    public Index() {

        DocumentArrayofIndex = new Document[50];
        for (int i = 0; i < DocumentArrayofIndex.length; i++) {
            DocumentArrayofIndex[i] = new Document();
            DocumentArrayofIndex[i].DocumentIDnumber = i;
        }
    }

    //this method adds a word to the document 
    public void addDocument(int DocumentNumber, String Word) {
        DocumentArrayofIndex[DocumentNumber].addNewWordToDoc(Word);
    }

    public String printDocment(int DocumentNumber) {
        String print="";
        if (DocumentArrayofIndex[DocumentNumber].wordsInDocument.empty()) {
            print="Sorry! The Document Is Empty";
        } else {
            DocumentArrayofIndex[DocumentNumber].wordsInDocument.findFirst();
            for (int i = 0; i < DocumentArrayofIndex[DocumentNumber].wordsInDocument.size; i++) {
               print+=DocumentArrayofIndex[DocumentNumber].wordsInDocument.retrieve() + " \n";
                DocumentArrayofIndex[DocumentNumber].wordsInDocument.findNext();
            }
        }
        return print;
    }

    public boolean[] getDocumentArray(String WordInDocument) {
        boolean[] result = new boolean[50];
        for (int i = 0; i < result.length; i++) {
            result[i] = false;
        }

        for (int i = 0; i < result.length; i++) {
            if (DocumentArrayofIndex[i].foundWordInDoc(WordInDocument)) {
                result[i] = true;
            }
        }

        return result;
    }

//here are the boolean retreival methods that will help me preform the AND OR operations on the index
    public boolean[] EvaluateExpression(String expression) {
        if (!expression.contains(" OR ") && !expression.contains(" AND ")) {
            expression = expression.toLowerCase().trim();
            boolean[] operation1 = getDocumentArray(expression.toLowerCase().trim());
            return operation1;
        } else if (expression.contains(" OR ") && expression.contains(" AND ")) {
            String[] AND_ORs = expression.split(" OR ");
            boolean[] operation1 = ANDOperationArray(AND_ORs[0]);

            for (int i = 1; i < AND_ORs.length; i++) {
                boolean[] operation2 = ANDOperationArray(AND_ORs[i]);

                for (int j = 0; j < 50; j++) {
                    operation1[j] = operation1[j] || operation2[j];
                }
            }
            return operation1;
        } else if (expression.contains(" AND ")) {
            return ANDOperationArray(expression);
        }

        return OROperationArray(expression);
    }

    public boolean[] ANDOperationArray(String expression) {
        String[] ANDs = expression.split(" AND ");
        boolean[] operation1 = getDocumentArray(ANDs[0].toLowerCase().trim());

        for (int i = 1; i < ANDs.length; i++) {
            boolean[] operation2 = getDocumentArray(ANDs[i].toLowerCase().trim());
            for (int j = 0; j < 50; j++) {
                operation1[j] = operation1[j] && operation2[j];
            }
        }
        return operation1;
    }

    public boolean[] OROperationArray(String expression) {
        String[] ORs = expression.split(" OR ");
        boolean[] operation1 = getDocumentArray(ORs[0].toLowerCase().trim());

        for (int i = 1; i < ORs.length; i++) {
            boolean[] operation2 = getDocumentArray(ORs[i].toLowerCase().trim());
            for (int j = 0; j < 50; j++) {
                operation1[j] = operation1[j] || operation2[j];
            }
        }
        return operation1;
    }

}
