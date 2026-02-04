package ru.netology.todos;

public class Todos {
    private Task[] tasks = new Task[0];

    public void add(Task task) {
        Task[] tmp = new Task[tasks.length + 1];
        for (int i = 0; i < tasks.length; i++) {
            tmp[i] = tasks[i];
        }
        tmp[tasks.length] = task;
        tasks = tmp;
    }

    public Task[] findAll() {
        return tasks;
    }

    public Task[] search(String query) {
        Task[] result = new Task[0];
        for (Task task : tasks) {
            if (task.matches(query)) {
                result = addToArray(result, task);
            }
        }
        return result;
    }

    private Task[] addToArray(Task[] current, Task task) {
        Task[] tmp = new Task[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = task;
        return tmp;
    }
}
