package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class textFileRead {
	
	String filename = "C:\\Users\\sathishkumar\\eclipse-workspace\\SeleniumWeekDay8AMTambaram\\Input\\sample.txt";
	public void ReadFile() throws IOException
	{
		File F = new File(filename);
		FileInputStream FI = new FileInputStream(F);
		//or 
		//FileInputStream FI = new FileInputStream(filename);
		int i=0;
		while((i=FI.read())!=-1)
		{
			System.out.print((char)i);
		}
	}
	
	public void ReadFileFileREader() throws IOException, InterruptedException
	{
		File F = new File(filename);
		FileReader FI = new FileReader(F);
		//or 
		//FileInputStream FI = new FileInputStream(filename);
		int i=0;
		int temp=0;
		while((i=FI.read())!=-1)
		{
			char eachChar = (char)i;
			if(eachChar=='H' || eachChar=='h')
			{
				temp=temp+1;
			}
			System.out.print((char)i);
			Thread.sleep(500);
		}
		System.out.println("");
		System.out.println("The H is avaiable "+temp+" time");

	}
	
	public void ReadFileusingBufferedREader() throws IOException, InterruptedException
	{
		File F = new File(filename);
		FileReader FI = new FileReader(F);
		BufferedReader BF = new BufferedReader(FI);
		//or 
		//FileInputStream FI = new FileInputStream(filename);
		String i= null;
		int temp=0;
		int LineNumber =0;
		while((i=BF.readLine())!=null)
		{
			LineNumber =LineNumber +1;
			String eachRow = i;
			String[] eachvalues =eachRow.split("\\s");
			for(String eachvalue : eachvalues)
			{
				temp=temp+1;
			if(eachvalue.equals("to"))
			{
				System.out.println("This is avaialbe in Line number : "+LineNumber);
				System.out.println("This is avaialbe in position : "+temp);
				
			}
			
			}
			temp =0;
			//Thread.sleep(500);
		}
		//System.out.println("");
		//System.out.println("The H is avaiable "+temp+" time");

	}


	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		textFileRead t = new textFileRead();
		t.ReadFileusingBufferedREader();
	}

}
