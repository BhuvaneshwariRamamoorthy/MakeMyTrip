package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;



public class TextfileWrite {
	
	//String filepath = "C:\\Users\\sathishkumar\\eclipse-workspace\\SeleniumWeekDay8AMTambaram\\Output\\";
	String filepath = System.getProperty("user.dir")+"\\Output\\";
	String ipfilepath = System.getProperty("user.dir")+"\\Input\\";
	
	String inputFile = "sample.txt";
	String filename = "outputfile.txt";
	String filename1 = "outputfile1.txt";
	
	String textToWrite= "Hi this is sathish with new data";
	public void writedata() throws IOException
	{
		File F = new File(filepath+filename);
		FileOutputStream FS = new FileOutputStream(F);
		FS.write(textToWrite.getBytes());
		FS.close();
		System.out.println("done");
	}

	
	public void writedatawithFileWriter() throws IOException
	{
		File F = new File(filepath+filename1);
		FileWriter FS = new FileWriter(F);
		FS.write(textToWrite);
		FS.close();
		System.out.println("done");
	}

	public void copyFile() throws IOException
	{
		File sourceFile = new File(ipfilepath+inputFile);
		File destinationFile = new File(filepath+filename1);
		FileUtils.copyFile(sourceFile, destinationFile);
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TextfileWrite T = new TextfileWrite();
		T.copyFile();
	}

}
