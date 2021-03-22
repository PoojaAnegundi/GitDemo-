package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqspec;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqspec==null) {
		//SpecBulilder
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));   //to write : FileoutputStream
		
		reqspec=new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		return reqspec;
		}
		return reqspec;	
	}
	public static String getGlobalValues(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\pooja.anegundi\\eclipse-workspace\\APIFrameworkRahulShetty\\src\\test\\java\\resources\\global.properties");    // To read properties from file : FileInputStream
		prop.load(file);
		return prop.getProperty(key);
	}
	
	public String getJasonPathKaysValue(Response response, String key){
		
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key).toString();
		
	}

}
