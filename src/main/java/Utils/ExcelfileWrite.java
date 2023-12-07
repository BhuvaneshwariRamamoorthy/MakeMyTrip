package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelfileWrite {
	
	String filepath = System.getProperty("user.dir")+"\\Output\\";
	String filename = "Output25092023.xlsx";
	String[] course = {"python","Java","C#","selenium"};
	
	public void readWrite() throws IOException
	{
		File F = new File(filepath+filename);
		FileOutputStream FS = new FileOutputStream(F);
		XSSFWorkbook writeWorkbook = new XSSFWorkbook();
		Sheet sheet = writeWorkbook.createSheet("outputData");
		
		int sizeofCourse = course.length;
		
		for(int i=0;i<sizeofCourse;i++)
		{
			Row eachRow = sheet.createRow(i);
			Cell eachCell = eachRow.createCell(i); //first column
			
			eachCell.setCellValue(course[i]);
		}
		writeWorkbook.write(FS);
		FS.close();
		System.out.println("Write operation is done");
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExcelfileWrite E = new ExcelfileWrite();
		E.readWrite();
	}

}
