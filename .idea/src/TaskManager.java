import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManager {

    public static void manageTasks(List<Employee> employees, String outputFilePath) {
        ExecutorService executor = Executors.newFixedThreadPool(employees.size());

        for (Employee employee : employees) {
            executor.submit(new Task(employee, employee.getTaskHours()));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Ожидаем завершения всех задач
        }

        try {
            writeResultsToExcel(employees, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gatherStatistics(employees);
    }

    private static void writeResultsToExcel(List<Employee> employees, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        int rowCount = 0;
        Row row = sheet.createRow(rowCount++);
        createHeaderRow(row);

        for (Employee employee : employees) {
            row = sheet.createRow(rowCount++);
            writeEmployee(employee, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }

    private static void createHeaderRow(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("Name");

        cell = row.createCell(1);
        cell.setCellValue("Task Hours");

        cell = row.createCell(2);
        cell.setCellValue("Hours Worked Today");

        cell = row.createCell(3);
        cell.setCellValue("Idle Hours");

        cell = row.createCell(4);
        cell.setCellValue("Total Worked Hours");
    }

    private static void writeEmployee(Employee employee, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(employee.getName());

        cell = row.createCell(1);
        cell.setCellValue(employee.getTaskHours());

        cell = row.createCell(2);
        cell.setCellValue(employee.getHoursWorkedToday());

        cell = row.createCell(3);
        cell.setCellValue(employee.getIdleHours());

        cell = row.createCell(4);
        cell.setCellValue(employee.getTotalWorkedHours());
    }

    private static void gatherStatistics(List<Employee> employees) {
        System.out.println("Employee Statistics:");
        for (Employee employee : employees) {
            double efficiency = ((double) employee.getTotalWorkedHours() / (employee.getTotalWorkedHours() + employee.getIdleHours())) * 100;
            System.out.println("Name: " + employee.getName());
            System.out.println("Total Task Hours: " + employee.getTaskHours());
            System.out.println("Total Worked Hours: " + employee.getTotalWorkedHours());
            System.out.println("Total Idle Hours: " + employee.getIdleHours());
            System.out.println("Efficiency: " + String.format("%.2f", efficiency) + "%");
            System.out.println();
        }
    }
}
