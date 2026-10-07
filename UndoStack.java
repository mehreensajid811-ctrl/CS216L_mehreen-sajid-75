/** Stack implemented with linked nodes (LIFO). Holds Actions for Undo. */
public class UndoStack {

    private static class Node {
        Action data;
        Node next;
        Node(Action data) { this.data = data; }
    }

    private Node top = null;
    private int size = 0;

    /** Push: add action on top. O(1) */
    public void push(Action a) {
        Node n = new Node(a);
        n.next = top;
        top = n;
        size++;
    }

    /** Pop: remove and return top action, or null if empty. O(1) */
    public Action pop() {
        if (isEmpty()) return null;
        Action a = top.data;
        top = top.next;   // old top node is dropped (garbage collected)
        size--;
        return a;
    }

    /** Peek: look at top action without removing it. O(1) */
    public Action peek() {
        return isEmpty() ? null : top.data;
    }

    public boolean isEmpty() { return top == null; }

    public int size() { return size; }
}