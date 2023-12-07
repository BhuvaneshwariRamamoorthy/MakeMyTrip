package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class EcelFileRead {
	
	String filepath = System.getProperty("user.dir")+"\\Input\\";
	String filename ="SampleData.xls";
	String sheetName = "input";
	
	public void readData() throws IOException
	{
		// REad the file
		
		File F = new File(filepath+filename);
		FileInputStream FS = new FileInputStream(F);
		//XSSFWorkbook excelbook = new XSSFWorkbook(FS);
		HSSFWorkbook excelbook = new HSSFWorkbook(FS);
		
		Sheet wbsheet = excelbook.getSheet(sheetName);
		// or 
		//Sheet wbsheet = excelbook.getSheetAt(0);
		int totalrows = wbsheet.getPhysicalNumberOfRows();
		//or 
		//int totalrows = wbsheet.getLastRowNum();
		
		for(int i=0;i<totalrows;i++) {
			Row eachRow = wbsheet.getRow(i);
			int totalColumns = eachRow.getLastCellNum();
			for (int j=0;j<totalColumns;j++)
			{
				Cell eachCell = eachRow.getCell(j);
				//System.out.print(eachCell.getStringCellValue());
				System.out.print(GetData(eachCell));
				System.out.print("\t");
			}
			System.out.println("");
		}
		excelbook.close();
	}
	
	public Object GetData(Cell cellvalue)
	{
		switch(cellvalue.getCellType())
		{
		case STRING:
			return cellvalue.getStringCellValue();
		case NUMERIC:
			DataFormatter dataFormatter = new DataFormatter();
			return dataFormatter.formatCellValue(cellvalue);
	
		default:
			return null;
			
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EcelFileRead E = new EcelFileRead();
		E.readData();
	}

}
