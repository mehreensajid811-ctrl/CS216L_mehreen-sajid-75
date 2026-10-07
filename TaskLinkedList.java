import java.util.ArrayList;

/** Singly linked list of Tasks with search and sort. */
public class TaskLinkedList {
    private Task head = null;
    private int size = 0;

    public int size() { return size; }
    public boolean isEmpty() { return head == null; }

    /** Insert at the end. O(n) because we walk to the tail. */
    public void addLast(Task t) {
        insertAt(size, t);
    }

    /** Insert at a given index (0..size). O(n) */
    public void insertAt(int index, Task t) {
        if (index < 0 || index > size) index = size;
        t.next = null;
        if (index == 0) {
            t.next = head;
            head = t;
        } else {
            Task prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.next;
            t.next = prev.next;
            prev.next = t;
        }
        size++;
    }

    /** Position of a task id in the list, or -1. */
    public int indexOfId(int id) {
        Task cur = head;
        int i = 0;
        while (cur != null) {
            if (cur.id == id) return i;
            cur = cur.next;
            i++;
        }
        return -1;
    }

    /** Delete node by id and return it (null if not found). O(n) */
    public Task removeById(int id) {
        if (head == null) return null;
        Task removed = null;
        if (head.id == id) {
            removed = head;
            head = head.next;
        } else {
            Task prev = head;
            while (prev.next != null && prev.next.id != id) prev = prev.next;
            if (prev.next == null) return null;
            removed = prev.next;
            prev.next = removed.next;   // unlink the node
        }
        removed.next = null;            // fully detach the removed node
        size--;
        return removed;
    }

    /** LINEAR SEARCH by title keyword (case-insensitive). O(n) */
    public ArrayList<Task> searchByTitle(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Task cur = head; cur != null; cur = cur.next) {
            if (cur.title.toLowerCase().contains(key)) found.add(cur);
        }
        return found;
    }

    /** BUBBLE SORT by priority (1 first). Swaps the data inside nodes. O(n^2) */
    public void bubbleSortByPriority() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            for (Task cur = head; cur.next != null; cur = cur.next) {
                if (cur.priority > cur.next.priority) {
                    int tId = cur.id;
                    String tTitle = cur.title;
                    int tPri = cur.priority;
                    cur.id = cur.next.id;
                    cur.title = cur.next.title;
                    cur.priority = cur.next.priority;
                    cur.next.id = tId;
                    cur.next.title = tTitle;
                    cur.next.priority = tPri;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    /** Print all tasks. O(n) */
    public void display() {
        if (head == null) {
            System.out.println("  (no tasks)");
            return;
        }
        for (Task cur = head; cur != null; cur = cur.next) {
            System.out.println("  " + cur);
        }
    }
}