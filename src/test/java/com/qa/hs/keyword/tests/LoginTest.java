package com.qa.hs.keyword.tests;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeywordEngine;

public class LoginTest {
	public KeywordEngine keywordEngine;
//	String 
	
	@Test
	public void loginTest(){
		keywordEngine=new KeywordEngine();
		keywordEngine.startExecution("login");
	}
	@Test
	public void abc(){
		
	}

}
