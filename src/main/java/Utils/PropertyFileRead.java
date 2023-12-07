package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileRead {

	static String filename = System.getProperty("user.dir")+"\\EnvironmentDetails\\env.properties";

	/*
	 *
	 */
	public static Properties GetEnvironmentDetail() throws IOException
	{

		File F = new File(filename);
		FileInputStream FI = new FileInputStream(F);
		Properties p = new Properties();
		p.load(FI);
		return p;
	}
}
