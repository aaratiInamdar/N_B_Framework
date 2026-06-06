package com.Ninza.hrm.JsonUtility;

import java.io.IOException;
import java.util.List;
import com.Ninza.hrm.FileUtility.FileUtility;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class JsonUtility {

	FileUtility fu=new FileUtility();
//	 get the json data from based on json complex xpath
	public String getDataOnJsonPath(Response response , String jsonXpath)
	{
		List<Object> list = JsonPath.read(response.asString(), jsonXpath);
		return list.get(0).toString();
		
	}
	
//	 get the XML data from based on XML complex xpath
	
	public String getDataOnXpathPath(Response response , String xmlXpath)
	{
		return response.xmlPath().get(xmlXpath);
	}
	
//	verify the data in jsonbody based jsonpath
	
	public boolean VerifyDataOnJsonPath(Response response , String jsonXpath, String expectedData)
	{
		List<String> list = JsonPath.read(response.asString(), jsonXpath);
		boolean flag=false;
		for (String str : list)
		{
			if(str.equals(expectedData))
			{
				System.out.println(expectedData+ "is available ==PASS");
				flag=true;
			}
		}
		if (flag ==false )
			
		{
			System.out.println(expectedData + "is not available==FAIL");
		}
		return flag ;
	}
	
//	get the access token 
	public String getAccessToken() throws IOException
	{
			Response response = given()
						.formParam("client_id", fu.getDataFromProperties("client_id"))
						.formParam("client_secret", fu.getDataFromProperties("client_secret"))
						.formParam("grant_type", "client_credentials")
			.when()
						.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
			response.then().log().all();
			
//			Capture the data from the response 
			String token = response.jsonPath().get("access_token");
			return token;
	}
	
}
