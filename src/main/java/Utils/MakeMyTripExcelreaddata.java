package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MakeMyTripExcelreaddata {

	static String filepath=System.getProperty("user.dir")+"\\Input\\";
	//String sheetName = "Input";
	static Object d;

	public static Object[][] readData(String fileName, String sheetName) throws IOException {
		Object[][] readdata = null;
		File file = new File(filepath+fileName);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int totalNoOfRows = sheet.getPhysicalNumberOfRows();
		int totalNoOfColumns = sheet.getRow(0).getLastCellNum();
		readdata = new Object[totalNoOfRows][totalNoOfColumns];

		for (int rowCount = 0; rowCount < totalNoOfRows; rowCount++) 
		{
			Row rowValue = sheet.getRow(rowCount);
			int usedColumn = sheet.getRow(rowCount).getLastCellNum();
			for (int columnCount = 0; columnCount < usedColumn; columnCount++) 
			{
				Cell data = rowValue.getCell(columnCount);
				readdata[rowCount][columnCount] = readdataassuch(data);
			}
		}
		workbook.close();
		fis.close();
		return readdata;
	}

	public static Object readdataassuch(Cell cellvalue) {
		if (cellvalue.getCellType().equals(CellType.STRING)) 
		{
			d = cellvalue.getStringCellValue();
		} else if (cellvalue.getCellType().equals(CellType.NUMERIC))
		{
			DataFormatter dataFormatter = new DataFormatter();
			d = dataFormatter.formatCellValue(cellvalue);
		}

		return d;
	}
}
