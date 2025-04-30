import java.util.*;

//Problem 4: Task Prioritization Engine (PriorityQueue, Comparator)
//Create a Task class with name, priority, deadline.
//        •	Use PriorityQueue  to order by closest deadline, then highest priority.
//•	Poll and print tasks as they should be executed.

class Task {
    private String name;
    private int priority;       // Higher number = higher priority
    private Date deadline;

    public Task(String name, int priority, Date deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public Date getDeadline() { return deadline; }

    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + ", deadline=" + deadline + "}";
    }
}

public class q4 {
    public static void main(String[] args) {
        // Comparator: 1. Closest deadline 2. Highest priority
        Comparator<Task> taskComparator = (t1, t2) -> {
            int deadlineComparison = t1.getDeadline().compareTo(t2.getDeadline());
            if (deadlineComparison != 0) {
                return deadlineComparison;  // Earlier deadline comes first
            } else {
                return Integer.compare(t2.getPriority(), t1.getPriority());  // Higher priority first
            }
        };

        // PriorityQueue with custom comparator
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(taskComparator);

        // Add some tasks
        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 5);
        taskQueue.add(new Task("Finish report", 3, cal.getTime()));

        cal.set(2025, Calendar.MAY, 2);
        taskQueue.add(new Task("Team meeting", 1, cal.getTime()));

        cal.set(2025, Calendar.MAY, 2);
        taskQueue.add(new Task("Code review", 4, cal.getTime()));

        cal.set(2025, Calendar.MAY, 4);
        taskQueue.add(new Task("Client call", 2, cal.getTime()));

        // Poll and print tasks in execution order
        System.out.println("Tasks in execution order:");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }
}
