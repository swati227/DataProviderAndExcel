import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DataProviderAndExcel {

    DataFormatter dataFormatter = new DataFormatter();

    @Test(dataProvider = "ExcelDriveTest")
    public void testCase(String greeting, String communication, String id){
        System.out.println(greeting+ ", " +communication+ ", "+id);
    }

    @DataProvider(name = "ExcelDriveTest")
    public Object[][] getData() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("/Users/nikhilpatil/Downloads/ExcelWithDataProvider.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowsCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();
        Object data[][] = new Object[rowsCount - 1][columnCount];

        for (int i = 0; i < rowsCount - 1; i++) {
            row = sheet.getRow(i+1);
            for (int j = 0; j < columnCount; j++) {

                XSSFCell cell = row.getCell(j);
//                cell value ultimately gets converted to string:
                data[i][j] = dataFormatter.formatCellValue(cell);
            }
        }

        return data;

    }
}
