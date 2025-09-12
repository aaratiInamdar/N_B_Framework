package com.Ninza.hrm.api.EmployeeTest;

import org.testng.annotations.Test;
import com.Ninza.hrm.BaseClass.BaseAPIClass;
import com.Ninza.hrm.EndPoints.IEndPoint;
import com.Ninza.hrm.PojoClassUtility.EmployeePojoClass;
import com.Ninza.hrm.PojoClassUtility.ProjectPojo;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.util.Random;

public class EmployeeTest extends BaseAPIClass{

	String projectName;
	String username;	
	@Test
	public void addEmployeTest() throws IOException
	{
		
//		create object for pojo class 

		 projectName="Airtel"+ju.getRandomNumber(300);
		 username="Rushi" +ju.getRandomNumber(300);
//		API Request 1==> add project inside the server 
		ProjectPojo pObj=new ProjectPojo(projectName, "Created", "Aarati", 0);
		
		 given()
		.spec(specRequestObj)
//		.auth().oauth2(bearerToken)
		.body(pObj)
		
		.when()
		.post(IEndPoint.addProject)
		
		.then()
		.spec(specResponseObj)
		.log().all();
				
//		API2 ==>Add emp to same Project	
		EmployeePojoClass empPojo=new EmployeePojoClass( "Architect", " 24/04/1983", "abc@gmail.com",username , 18,
				 "1234567890",  projectName,  "Role_Employee", username);
		
		 given()
			.spec(specRequestObj)
			.body(empPojo)
			
		.when()
			.post(IEndPoint.addEmp)
			
		.then()
		.assertThat()
							.spec(specResponseObj)
						   .assertThat().statusCode(201)
					     .log().all();
		 
	}
			@Test
			public void addEmployeeWithoutMailTest() throws IOException
			{
				
				String BaseUri = fu.getDataFromProperties("BASEURI");
//				create object for pojo class 
				Random random=new Random();
				int randomNum = random.nextInt(100);
				 projectName="Airtel"+randomNum;
				 username="Rushi" +randomNum;
//				API Request 1==> add project inside the server 
				ProjectPojo pObj=new ProjectPojo(projectName, "Created", "Aarati", 0);
				
				 given()
				.spec(specRequestObj)
				.body(pObj)
				
				.when()
				.post(IEndPoint.addProject)
				
				.then()
				.spec(specResponseObj)
				.log().all();
									
//				API2 ==>Add emp to same Project				
				EmployeePojoClass empPojo=new EmployeePojoClass( "Architect", " 24/04/1983","",username , 18,
						 "1234567890",  projectName,  "Role_Employee", username);
				
				 given()
					.spec(specRequestObj)					
					.body(empPojo)
					
				.when()
					.post(IEndPoint.addEmp)
					
				.then()
				.spec(specResponseObj)
					.assertThat().statusCode(500)
				     .log().all();
			}			
}
