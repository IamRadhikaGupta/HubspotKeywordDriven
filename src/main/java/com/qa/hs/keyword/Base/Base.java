/*engine is execution engine */

package com.qa.hs.keyword.Base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public FileReader reader;
	
	public WebDriver init_driver(String browserName){
		System.setProperty("webdriver.chrome.property", "C:\\JarFiles\\Drivers\\chromedriver.exe");
	    if(prop.getProperty("headless").equals("yes")){
			//headless mode
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--headless--");
			driver=new ChromeDriver(options);
			
		}else{
			driver=new ChromeDriver();
		}
		return driver;
	}
		
	public Properties init_properties(){
		prop=new Properties();
		try {
			reader=new FileReader("C:\\Users\\radhika.gupta\\"
			 		+ "Documents\\MySeProjects\\"
			 		+ "KeywordDrivenHubspot\\src\\main\\java\\com\\qa\\hs"
			 		+ "\\keyword\\config\\config.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		try {
			prop.load(reader);
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		return prop;
		
	}	
		
		
		
	}


