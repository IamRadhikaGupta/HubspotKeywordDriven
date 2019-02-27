package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.Base.Base;

public class KeywordEngine {
	public WebDriver driver;
	public Properties prop;
	public Workbook book;
	public Sheet sheet;
	
	public Base base;
	WebElement e;
	
	
	public final String SCENARIO_SHEET_PATH="C:\\Users\\radhika.gupta\\"
			+ "Documents\\MySeProjects\\KeywordDrivenHubspot\\src\\main\\"
			+ "java\\com\\qa\\hs\\keyword\\scenarios\\HubspotScenarios.xlsx";
	
//	C:\Users\radhika.gupta\Documents\MySeProjects\KeywordDrivenHubspot\src\main\java\com\qa\hs\keyword\scenarios\HubspotScenarios.xlsx
	public void startExecution(String sheetName){
		String locatorName=null;
		String locatorValue=null;
		String action=null;
		String value;
		
		
		FileInputStream file= null;
		try {
			file=new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
		int k=0;//columns
		for(int i =0;i<sheet.getLastRowNum();i++){
		String locatorColValue=sheet.getRow(i+1).getCell(k+1).toString().trim();
		if(!locatorColValue.equalsIgnoreCase("NA")){ 
			//for first row this is true but ! will make it false
		locatorName=locatorColValue.split("=")[0].trim();//id
		locatorValue=locatorColValue.split("=")[1].trim();//username
			
		}
		
    	action=	sheet.getRow(i+1).getCell(k+2).toString().trim();
    	value=sheet.getRow(i+1).getCell(k+3).toString().trim();
        
    	switch(action){
    	
    	case "open bowser":
    		base=new Base();
    	    prop=base.init_properties();
    	    
    		if(value.isEmpty()||value.equals("NA")){
    			 driver= base.init_driver(prop.getProperty("browser"));
    		}
    		else{
    			driver=base.init_driver(value);
    		}
    		break;
    		
       case	"enter url":
    	   if(value.isEmpty()||value.equals("NA")){
  			 driver.get(prop.getProperty("url"));
  		   }
    	   else{
    		   driver.get(value);
    		  }
    	   
    	   break;
    	   
      case "quit":
    	  driver.quit();
    	  break;
    	  
     default:
    	 break;
    	
    	}
    	
  try{    	
	switch(locatorName){
	case "id":
    		 e=driver.findElement(By.id(locatorValue));
    		if(action.equalsIgnoreCase("sendkeys")){
    			e.sendKeys(value);
    		}
    		if(action.equalsIgnoreCase("click")){
    			e.click();
    		}
    	locatorName=null;
    	    break;
    	case "name":
    		 e=driver.findElement(By.name(locatorValue));
    		if(action.equalsIgnoreCase("sendkeys")){
    			e.sendKeys(value);
    		}
    		if(action.equalsIgnoreCase("click")){
    			e.click();
    		}
    		locatorName=null;
            break;
    	case "linkText":
    		e=driver.findElement(By.linkText(locatorValue));
    		if(action.equalsIgnoreCase("sendkeys")){
    			e.sendKeys(value);
    		}
    		if(action.equalsIgnoreCase("click")){
    			e.click();
    		}
    		locatorName=null;
    	    break;
    	default:
    		break;
    	}}
  catch(Exception e){}
    	}
		
		}
		
		
	}


