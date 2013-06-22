/**
 * Incomplete ABCD TEST TEST
 */

public class AVLTree<Key extends Comparable<Key>, Value> implements Iterable<Value> {

    private Node root;
    private int size;

    private class Node {
        private Key key;
        private Value value;
        private int height = -1;
        private Node leftNode, rightNode;
    }

    public AVLTree() {
        root = new Node();
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void insert(Key key, Value value) {

        // If there
        if (isEmpty()) {
            root.value = value;
            root.leftNode = null;
            root.rightNode = null;
            root.height = 0;
            size = 1;
            return;
        }

        Node currentNode = root;

        while (currentNode != null) {

            int comp = key.compareTo(root.key);

            if (comp < 0) currentNode = currentNode.leftNode;
            else if (comp > 0) currentNode = currentNode.rightNode;
            else {
                currentNode.value = value;
                return;
            }
        }

        currentNode.key = key;
        currentNode.value = value;

        size++;

        updateHeights(currentNode);
        balanceTree();
    }

    private void updateHeights(Node node) {

        Node currentNode = node;

        while (currentNode != root) {
            if
        }
    }


}
