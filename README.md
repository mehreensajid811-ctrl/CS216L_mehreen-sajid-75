# Task Manager in Java (CS216L Project 1)

**Student:** Asma Muneer | **Roll No:** 6
**Course:** CS 216L, Data Structures Lab (Weeks 1-3)
**Repository:** 

---

## About the Project

This is a menu-driven Task Manager that runs in the console. All tasks are kept in a **singly linked list**, and every change the user makes (add, delete, sort) is recorded on a **stack**, which makes it possible to undo the most recent action.

## Data Structures and Algorithms

| Component | Purpose |
|---|---|
| Singly Linked List (`TaskLinkedList`) | Holds all tasks; supports add, delete, insert and display |
| Stack (`UndoStack`, built from linked nodes) | Remembers actions so they can be undone |
| Linear Search | Finds tasks whose title contains a keyword |
| Bubble Sort | Orders tasks by priority (1 = highest, 5 = lowest) |

## Menu Options

1. Add a task
2. Delete a task
3. View all tasks
4. Search for a task
5. Sort tasks by priority
6. Undo the last action
7. Show the last action (stack peek)
8. Exit

## Input Validation

- The menu choice must be a number from 1 to 8.
- Priority must be a number from 1 to 5.
- Task ID must be a valid positive number.
- A task title cannot be left empty.

When a node is deleted, its `next` reference is set to `null`, so no stray links remain in memory.

## Time Complexity

| Operation | Complexity | Reason |
|---|---|---|
| Add task (at end) | O(n) | Must traverse to the last node |
| Delete task (by ID) | O(n) | Find the node, then unlink it |
| Search (linear) | O(n) | Every node may need to be checked |
| Sort (bubble) | O(n^2) | Repeated passes with comparisons and swaps |
| Push (stack) | O(1) | Added at the top |
| Pop (stack) | O(1) | Removed from the top |

## How to Run

Open a terminal in the project folder and run:

```
javac *.java
java Main
```

## Project Files

`Main.java`, `Task.java`, `TaskLinkedList.java`, `UndoStack.java`, `Action.java`

## Screenshots

<img width="852" height="661" alt="Task Manager menu" src="https://github.com/user-attachments/assets/a0a8f90b-5083-4d90-81c6-e7d8cdcaa39b" />
<img width="967" height="682" alt="Adding and viewing tasks" src="https://github.com/user-attachments/assets/ace34e27-83d9-4997-b031-82b56a7f73c1" />
<img width="744" height="650" alt="Search and sort" src="https://github.com/user-attachments/assets/07708100-2c1e-492d-b724-3089f0311bdd" />
<img width="600" height="644" alt="Undo and last action" src="https://github.com/user-attachments/assets/c892ee25-a551-4d8e-a511-51283173eb43" />
