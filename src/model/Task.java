package model;

public class Task {

    private int id;
    private String name;
    private String dueDate;

    public Task(int id,String name,String dueDate) {

        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
    }

    public Task(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return name;
    }
}