import java.util.ArrayList;
import java.util.Scanner;

/** Menu-driven Task Manager: linked list + undo stack + linear search + bubble sort. */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskLinkedList tasks = new TaskLinkedList();
    private static final UndoStack undoStack = new UndoStack();
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Choose option: ", 1, 8);
            switch (choice) {
                case 1: addTask(); break;
                case 2: deleteTask(); break;
                case 3: System.out.println("\n--- All Tasks ---"); tasks.display(); break;
                case 4: searchTask(); break;
                case 5: tasks.bubbleSortByPriority(); System.out.println("Sorted by priority (1 = highest)."); tasks.display(); break;
                case 6: undoLastAction(); break;
                case 7: showLastAction(); break;
                case 8: System.out.println("Goodbye!"); break;
            }
        } while (choice != 8);
    }

    private static void printMenu() {
        System.out.println("\n===== TASK MANAGER =====");
        System.out.println("1. Add task");
        System.out.println("2. Delete task");
        System.out.println("3. View all tasks");
        System.out.println("4. Search task (linear search)");
        System.out.println("5. Sort by priority (bubble sort)");
        System.out.println("6. Undo last action");
        System.out.println("7. Show last action (stack peek)");
        System.out.println("8. Exit");
    }

    private static void addTask() {
        String title = readNonEmpty("Task title: ");
        int priority = readInt("Priority (1 = highest, 5 = lowest): ", 1, 5);
        Task t = new Task(nextId++, title, priority);
        tasks.addLast(t);
        undoStack.push(new Action(Action.Type.ADD, t, tasks.size() - 1));
        System.out.println("Task added with ID " + t.id);
    }

    private static void deleteTask() {
        if (tasks.isEmpty()) { System.out.println("No tasks to delete."); return; }
        tasks.display();
        int id = readInt("Enter task ID to delete: ", 1, Integer.MAX_VALUE);
        int index = tasks.indexOfId(id);
        Task removed = tasks.removeById(id);
        if (removed == null) {
            System.out.println("Task not found.");
        } else {
            undoStack.push(new Action(Action.Type.DELETE, removed, index));
            System.out.println("Deleted: " + removed.title);
        }
    }

    private static void searchTask() {
        String key = readNonEmpty("Search keyword: ");
        ArrayList<Task> found = tasks.searchByTitle(key);
        if (found.isEmpty()) System.out.println("No match found.");
        else for (Task t : found) System.out.println("  " + t);
    }

    private static void undoLastAction() {
        Action a = undoStack.pop();
        if (a == null) { System.out.println("Nothing to undo."); return; }
        if (a.type == Action.Type.ADD) {
            tasks.removeById(a.task.id);
            System.out.println("Undo: removed task '" + a.task.title + "'");
        } else {
            tasks.insertAt(a.index, a.task);
            System.out.println("Undo: restored task '" + a.task.title + "'");
        }
    }

    private static void showLastAction() {
        Action a = undoStack.peek();
        if (a == null) System.out.println("Stack is empty (no actions yet).");
        else System.out.println("Last action: " + a.type + " -> " + a.task.title
                + "   (stack size: " + undoStack.size() + ")");
    }

    // ---------- input validation helpers ----------
    private static int readInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException e) { /* fall through */ }
            System.out.println("Invalid input. Enter a number between " + min + " and " + (max == Integer.MAX_VALUE ? "max" : max) + ".");
        }
    }

    private static String readNonEmpty(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }
}