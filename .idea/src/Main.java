// P.S. Многопоточность работы заключается в распределении задач сотрудникам

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "employees.xlsx";
        String outputFilePath = "results.xlsx";

        try {
            List<Employee> employees = ExcelReader.readEmployees(inputFilePath);
            TaskManager.manageTasks(employees, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
