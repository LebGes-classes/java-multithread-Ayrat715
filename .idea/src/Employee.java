public class Employee {
    private String name;
    private int taskHours;
    private int hoursWorkedToday;
    private int idleHours;
    private int totalWorkedHours;

    public Employee(String name, int taskHours) {
        this.name = name;
        this.taskHours = taskHours;
        this.hoursWorkedToday = 0;
        this.idleHours = 0;
        this.totalWorkedHours = 0;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public int getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(int taskHours) {
        this.taskHours = taskHours;
    }

    public int getHoursWorkedToday() {
        return hoursWorkedToday;
    }

    public void setHoursWorkedToday(int hoursWorkedToday) {
        this.hoursWorkedToday = hoursWorkedToday;
    }

    public int getIdleHours() {
        return idleHours;
    }

    public void setIdleHours(int idleHours) {
        this.idleHours = idleHours;
    }

    public int getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(int totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }
}
