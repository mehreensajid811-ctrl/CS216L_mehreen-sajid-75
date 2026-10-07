
public class Action {
    public enum Type { ADD, DELETE }

    Type type;
    Task task;      
    int index;      

    public Action(Type type, Task task, int index) {
        this.type = type;
        this.task = task;
        this.index = index;
    }
}