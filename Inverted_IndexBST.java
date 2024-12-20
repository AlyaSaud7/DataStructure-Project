package ds;

public class Inverted_IndexBST {

    BST< String, TermBoolean> InvertedIndexBSTtree;
    int counter = 0;

    public Inverted_IndexBST() {
        InvertedIndexBSTtree = new BST< String, TermBoolean>();
    }

    public int size() {
        return InvertedIndexBSTtree.count;
    }

    public boolean addNew(int DocumentNumber, String word) {
        if (InvertedIndexBSTtree.empty()) {
            counter++;
            TermBoolean t = new TermBoolean();
            VocabWord voc = new VocabWord(word);
            t.setVocab(voc);
            t.FoundInDoc(DocumentNumber);
            InvertedIndexBSTtree.insert(word, t);
            return true;
        } else {
            if (InvertedIndexBSTtree.find(word)) {
                TermBoolean t = this.InvertedIndexBSTtree.retrieve();
                t.FoundInDoc(DocumentNumber);
                InvertedIndexBSTtree.update(t);
                return false;

            }

            counter++;
            TermBoolean t = new TermBoolean();
            VocabWord voc = new VocabWord(word);
            t.setVocab(voc);
            t.FoundInDoc(DocumentNumber);
            InvertedIndexBSTtree.insert(word, t);
            return true;
        }
    }

    public boolean found(String word) {
        return InvertedIndexBSTtree.find(word);
    }

    public void printDocument() {
        InvertedIndexBSTtree.Traverse();
    }

    //here are the boolean retreival methods that will help me preform the AND OR operations on the index
    public boolean[] EvaluateExpression(String expression) {
        if (!expression.contains(" OR ") && !expression.contains(" AND ")) {
            boolean[] r1 = new boolean[50];
            expression = expression.toLowerCase().trim();

            if (this.found(expression)) {
                r1 = this.InvertedIndexBSTtree.retrieve().getDocs();
            }
            return r1;
        } else if (expression.contains(" OR ") && expression.contains(" AND ")) {
            String[] AND_ORs = expression.split(" OR ");
            boolean[] operation1 = ANDOperationArray(AND_ORs[0]);

            for (int i = 1; i < AND_ORs.length; i++) {
                boolean[] operation2 = ANDOperationArray(AND_ORs[i]);

                operation1 = applyOr(operation1, operation2);
            }
            return operation1;
        } else if (expression.contains(" AND ")) {
            return ANDOperationArray(expression);
        }

        return OROperationArray(expression);
    }

    public boolean[] ANDOperationArray(String expression) {
        String[] ANDs = expression.split(" AND ");
        boolean[] operation1 = new boolean[50];

        if (this.found(ANDs[0].toLowerCase().trim())) {
            operation1 = this.InvertedIndexBSTtree.retrieve().getDocs();
        }

        for (int i = 1; i < ANDs.length; i++) {
            boolean[] operation2 = new boolean[50];
            if (this.found(ANDs[i].toLowerCase().trim())) {
                operation2 = this.InvertedIndexBSTtree.retrieve().getDocs();
            }

            operation1 = applyAND(operation1, operation2);
        }
        return operation1;
    }

    public boolean[] OROperationArray(String expression) {
        String[] ORs = expression.split(" OR ");
        boolean[] operation1 = new boolean[50];

        if (this.found(ORs[0].toLowerCase().trim())) {
            operation1 = this.InvertedIndexBSTtree.retrieve().getDocs();
        }

        for (int i = 1; i < ORs.length; i++) {
            boolean[] operation2 = new boolean[50];
            if (this.found(ORs[i].toLowerCase().trim())) {
                operation2 = this.InvertedIndexBSTtree.retrieve().getDocs();
            }

            operation1 = applyOr(operation1, operation2);

        }
        return operation1;
    }

    public boolean[] applyAND(boolean[] array1, boolean[] array2) {
        boolean[] result = new boolean[50];
        for (int i = 0; i < 50; i++) {
            result[i] = array1[i] && array2[i];
        }
        return result;
    }

    public boolean[] applyOr(boolean[] array1, boolean[] array2) {
        boolean[] result = new boolean[50];
        for (int i = 0; i < 50; i++) {
            result[i] = array1[i] || array2[i];
        }
        return result;
    }

}
