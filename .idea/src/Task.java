public class Task implements Runnable {
    private Employee employee;
    private int taskDuration;

    public Task(Employee employee, int taskDuration) {
        this.employee = employee;
        this.taskDuration = taskDuration;
    }

    @Override
    public void run() {
        int hoursLeft = taskDuration;
        while (hoursLeft > 0) {
            int workHours = Math.min(8, hoursLeft);
            employee.setHoursWorkedToday(workHours);
            employee.setTotalWorkedHours(employee.getTotalWorkedHours() + workHours);
            hoursLeft -= workHours;

            if (hoursLeft > 0) {
                // Перерыв до следующего дня
                employee.setIdleHours(employee.getIdleHours() + (24 - workHours));
            }
        }
    }
}
