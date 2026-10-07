/** One node of the linked list: a single task. */
public class Task {
    int id;
    String title;
    int priority;   // 1 = highest, 5 = lowest
    Task next;      // link to the next node

    public Task(int id, String title, int priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Priority: %d | %s", id, priority, title);
    }
}