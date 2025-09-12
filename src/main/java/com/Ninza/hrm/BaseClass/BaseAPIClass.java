package com.Ninza.hrm.BaseClass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.Ninza.hrm.DatabaseUtility.DataBaseUtility;
import com.Ninza.hrm.FileUtility.FileUtility;
import com.Ninza.hrm.JavaUtility.JavaUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static  io.restassured.RestAssured.*;

public class BaseAPIClass {
	
	public static RequestSpecification specRequestObj ;
	public static ResponseSpecification specResponseObj ;
	public JavaUtility ju=new JavaUtility();
	public  FileUtility fu=new FileUtility();
	public DataBaseUtility du=new DataBaseUtility();
//	public String bearerToken="ewsrdtfgyh84512";
	@BeforeSuite
	public void configBS() throws Throwable
	{
		du.getConnection();
		System.out.println("====Connect to db======");
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		
		
//		builder.setAuth(basic("username", "password"));
//		builder.addHeader("", "");
		
		builder.setBaseUri(fu.getDataFromProperties("BASEURI"));
		 specRequestObj = builder.build();
			 
		 ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
		 resBuilder.expectContentType(ContentType.JSON);
		  specResponseObj = resBuilder.build();
	}
	
	@AfterSuite
	public void configAS()
	{
		du.getCloseConnection();
		System.out.println("=close the db connection");
	}
}
