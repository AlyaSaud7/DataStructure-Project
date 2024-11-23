package ds;

public class BST<K extends Comparable<K>, T> {

    class BSTNode<K extends Comparable<K>, T> {

        public K key;
        public T data;
        public BSTNode<K, T> left, right;

        /**
         * Creates a new instance of BSTNode
         */
        public BSTNode(K Newk, T value) {
            key = Newk;
            data = value;
            left = right = null;
        }

        public BSTNode(K Newk, T value, BSTNode<K, T> leftt, BSTNode<K, T> rightt) {
            key = Newk;
            data = value;
            left = leftt;
            right = rightt;
        }
    }

    BSTNode<K, T> root;
    BSTNode<K, T> current;
    int count;

    public BST() {
        root = current = null;
        count = 0;
    }

    // Returns the number of elements in the map.
    public int size() {
        return count;
    }

    // Return true if the tree is empty. Must be O(1).
    public boolean empty() {
        return root == null;
    }

    // Removes all elements 
    public void clear() {
        root = current = null;
        count = 0;
    }

    // Return the key and data of the current element
    public T retrieve() {
        T data = null;
        if (current != null) {
            data = current.data;
        }
        return data;
    }

    // Update the data
    public void update(T e) {
        if (current != null) {
            current.data = e;
        }
    }

    public boolean find(K key) {
        BSTNode<K, T> p = root;

        if (empty()) {
            return false;
        }

        while (p != null) {
            if (p.key.compareTo(key) == 0) {
                current = p;
                return true;
            } else if (key.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }

    public boolean insert(K key, T data) {

        if (empty()) {
            BSTNode<K, T> NewNode = new BSTNode<K, T>(key, data);
            current = root = NewNode;
            count++;
            return true;
        }

        BSTNode<K, T> par = null;
        BSTNode<K, T> child = root;

        while (child != null) {
            if (child.key.compareTo(key) == 0) {
                return false;
            } else if (key.compareTo(child.key) < 0) {
                par = child;
                child = child.left;
            } else {
                par = child;
                child = child.right;
            }
        }

        if (key.compareTo(par.key) < 0) {
            par.left = new BSTNode<K, T>(key, data);
            current = par.left;
        } else {
            par.right = new BSTNode<K, T>(key, data);
            current = par.right;
        }
        count++;
        return true;
    }

    private BSTNode<K, T> find_min(BSTNode<K, T> p) {
        if (p == null) {
            return null;
        }

        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public void Traverse() {
        if (root != null) {
            traverseTree(root);
        }
    }

    private void traverseTree(BSTNode<K, T> node) {
        if (node == null) {
            return;
        }
        traverseTree(node.left);
        System.out.println(node.data);
        traverseTree(node.right);

    }

    public void TraverseT() {
        if (root != null) {
            traverseTreeT(root);
        }
    }

    private void traverseTreeT(BSTNode<K, T> node) {
        if (node == null) {
            return;
        }
        traverseTreeT(node.left);
        if (node.data instanceof BST) {
            System.out.println("Node key ==== " + node.key);
            ((BST<String, WordRank>) node.data).Traverse();
        } else {
            System.out.println(node.data);
        }

        traverseTreeT(node.right);

    }

    public String PrintBSTnodeData() {
        if (root != null) {
            StringBuilder tokens = new StringBuilder();
            PrintDataBSTnode(root, tokens);
            return tokens.toString(); // Return the accumulated string
        }
        return ""; // If root is null, return an empty string
    }

    private void PrintDataBSTnode(BSTNode<K, T> node, StringBuilder tokens) {
        if (node == null) {
            return;
        }
        // Process left child
        PrintDataBSTnode(node.left, tokens);
        // Process current node
        tokens.append("The Token: ").append(node.key);
        if (node.data instanceof TermBoolean) {
            tokens.append(",   Documents: ").append("(");
            boolean[] docs = ((TermBoolean) node.data).getDocs();
            for (int i = 0; i < 50; i++) {
                if (docs[i]) {
                    tokens.append(" ").append(i).append(" ");
                }
            }
            tokens.append(")\n");
            
        }

        // Process right child
        PrintDataBSTnode(node.right, tokens);
    }

}
