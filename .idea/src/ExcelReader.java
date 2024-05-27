import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<Employee> readEmployees(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Пропускаем заголовок
            String name = row.getCell(0).getStringCellValue();
            double taskHours = row.getCell(1).getNumericCellValue();
            employees.add(new Employee(name, (int) taskHours));
        }

        workbook.close();
        fileInputStream.close();
        return employees;
    }
}
