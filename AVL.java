package ds;

public class AVL<K extends Comparable<K>, T> {

    class AVLNode<K extends Comparable<K>, T> {

        public K key;
        public T data;
        AVLNode<K, T> parent; // pointer to  parent
        AVLNode<K, T> left; // pointer to left child
        AVLNode<K, T> right; // pointer to right child
        int bf; // balance the node

        public AVLNode() {
            this.key = null;
            this.data = null;
            this.parent = this.left = this.right = null;
            this.bf = 0;
        }

        public AVLNode(K key, T data) {
            this.key = key;
            this.data = data;
            this.parent = this.left = this.right = null;
            this.bf = 0;
        }

        public AVLNode(K key, T data, AVLNode<K, T> p, AVLNode<K, T> l, AVLNode<K, T> r) {
            this.key = key;
            this.data = data;
            left = l;
            right = r;
            parent = p;
            bf = 0;
        }

        public AVLNode<K, T> getLeft() {
            return left;
        }

        public AVLNode<K, T> getRight() {
            return right;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return "AVL Node{" + "key=" + key + ", data =" + data + '}';
        }
    } //end of class AVLNODE

    private AVLNode<K, T> root;
    private AVLNode<K, T> curr;
    private int count;

//these are the main methods for avl class
    public AVL() {
        root = curr = null;
        count = 0;
    }

    public boolean empty() {
        return root == null;
    }

    public int size() {
        return count;
    }

// Removes all elements in the map
    public void clear() {
        root = curr = null;
        count = 0;
    }

    // Return the key and data of the current element
    public T retrieve() {
        T data = null;
        if (curr != null) {
            data = curr.data;
        }
        return data;
    }

// Update the data of current element.
    public void update(T e) {
        if (curr != null) {
            curr.data = e;
        }
    }

    //searches for the key in the AVL, returns the data or null (if not found).
    private T searchTreeHelper(AVLNode<K, T> node, K key) {

        if (node == null) {
            return null;
        } else if (node.key.compareTo(key) == 0) {
            curr = node;
            return node.data;
        } else if (node.key.compareTo(key) > 0) {
            return searchTreeHelper(node.left, key);
        } else {
            return searchTreeHelper(node.right, key);
        }
    }

    // update  balance factor of node
    private void updateBalance(AVLNode<K, T> node) {
        if (node.bf < -1 || node.bf > 1) {
            rebalance(node);
            return;
        }

        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.bf -= 1;
            }

            if (node == node.parent.right) {
                node.parent.bf += 1;
            }

            if (node.parent.bf != 0) {
                updateBalance(node.parent);
            }
        }
    }

    // rebalance the tree 
    void rebalance(AVLNode<K, T> node) {
        if (node.bf > 0) {
            if (node.right.bf < 0) {
                rightRotate(node.right);
                leftRotate(node);
            } else {
                leftRotate(node);
            }
        } else if (node.bf < 0) {
            if (node.left.bf > 0) {
                leftRotate(node.left);
                rightRotate(node);
            } else {
                rightRotate(node);
            }
        }
    }

    //returns if the node with key k is found or not
    public boolean find(K key) {
        T data = searchTreeHelper(this.root, key);
        if (data != null) {
            return true;
        }
        return false;
    }

    // rotate left at given node
    void leftRotate(AVLNode<K, T> x) {
        AVLNode<K, T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;

        // update  balance factor
        x.bf = x.bf - 1 - Math.max(0, y.bf);
        y.bf = y.bf - 1 + Math.min(0, x.bf);
    }

    // rotate right at given node
    void rightRotate(AVLNode<K, T> x) {
        AVLNode<K, T> y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;

        // update the balance factor
        x.bf = x.bf + 1 - Math.min(0, y.bf);
        y.bf = y.bf + 1 + Math.max(0, x.bf);
    }

    public boolean insert(K key, T data) {

        AVLNode<K, T> node = new AVLNode<K, T>(key, data);
        AVLNode<K, T> p = null;
        AVLNode<K, T> currentNode = this.root;

        while (currentNode != null) {
            p = currentNode;
            if (node.key.compareTo(currentNode.key) == 0) {
                return false;
            } else if (node.key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        // p  parent of current
        node.parent = p;
        if (p == null) {
            root = node;
            curr = node;
        } else if (node.key.compareTo(p.key) < 0) {
            p.left = node;
        } else {
            p.right = node;
        }
        count++;

        //  re-balance the node 
        updateBalance(node);
        return true;
    }

    public void Traverse() {
        if (root != null) {
            traverseTree(root);
        }
    }

    //print in order 
    private void traverseTree(AVLNode<K, T> node) {
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

    private void traverseTreeT(AVLNode<K, T> node) {
        if (node == null) {
            return;
        }
        traverseTreeT(node.left);
        if (node.getData() instanceof AVL) {
            System.out.println("Node key ==== " + node.key);
            ((AVL<String, WordRank>) node.getData()).Traverse();
        } else {
            System.out.println(node.data);
        }
        traverseTreeT(node.right);
    }

    public String PrintAVLnodeData() {
    if (root != null) {
        StringBuilder output = new StringBuilder();
        PrintAVLnode(root, output);
        return output.toString(); // Return the accumulated data
    }
    return ""; // Return an empty string if the tree is empty
}

private void PrintAVLnode(AVLNode<K, T> node, StringBuilder output) {
    if (node == null) {
        return;
    }
    // Process left child
    PrintAVLnode(node.left, output);
    // Process current node
    output.append("The Token: ").append(node.key);
    if (node.getData() instanceof TermBoolean) {
        output.append(",   Documents: ");
        boolean[] docs = ((TermBoolean) node.data).getDocs();
        for (int i = 0; i < 50; i++) {
            if (docs[i]) {
                output.append(" ").append(i).append(" ");
            }
        }
        output.append("\n");
    }
    // Process right child
    PrintAVLnode(node.right, output);
}

}
