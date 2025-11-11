package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//create java respresentation object of physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\Swapnil Rokade\\Downloads\\commondata.properties");
		
		//create object for properties
		Properties prop=new Properties();
				
		//load the data in prop
		prop.load(fis);
		
		//read the data
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("un");
		String PWD = prop.getProperty("pwd");
		
		//printing the data
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);
		
		
		

	}

}
