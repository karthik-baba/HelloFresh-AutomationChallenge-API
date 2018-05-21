package com.hellofresh.api.RegressionSuite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hellofresh.api.testdatapojo.CountryTestData;
import com.hellofresh.utils.ExcelUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class CountryTests {
	public static final String baseURI="http://services.groupkt.com/";
	private RequestSpecification spec;

	@BeforeTest
	public void init()
	{
		this.spec = new RequestSpecBuilder()
				.setBaseUri(baseURI)
				.build();
	}


	@Test
	public 	void getAllCountriesTest()
	{
		given().
			spec(this.spec).
		when().
			get("country/get/all").
		then().
			contentType(ContentType.JSON).
			body("RestResponse.result.alpha2_code",hasItems("US","DE","GB")).
			//log().all().
			statusCode(200);
	}
	
	@Test
	public void getIndividualCountries()
	{
			
		//Test Data From Excel
		List<CountryTestData> excelObjLst=null;
		try 
		{
			excelObjLst=(List<CountryTestData>)ExcelUtility.fn_GetExcelData("IndividualCountries", CountryTestData.class);
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(CountryTestData countryTestDataObj:excelObjLst)
		{
			given().
				spec(this.spec).
				pathParam("COUNTRY_ISO2CODE", countryTestDataObj.getIso2Code()).
			when().
				get("country/get/iso2code/{COUNTRY_ISO2CODE}").
			then().
				contentType(ContentType.JSON).
				//log().all().
				body("RestResponse.result.name",equalTo(countryTestDataObj.getCountryName())).
				body("RestResponse.result.alpha2_code",equalTo(countryTestDataObj.getAlpha2Code())).
				body("RestResponse.result.alpha3_code",equalTo(countryTestDataObj.getAlpha3Code())).
				statusCode(200);
		}		
		
	}
	
	@Test
	public void getInvalidCountries()
	{
		given().
			spec(this.spec).
			pathParam("COUNTRY_ISO2CODE", "XXXX").
		when().
			get("country/get/iso2code/{COUNTRY_ISO2CODE}").
		then().
			contentType(ContentType.JSON).
			//log().all().
			body("RestResponse.messages[0]",equalTo("No matching country found for requested code [XXXX].")).
			statusCode(200);
	}
	
	@Test
	public void addNewCountry()
	{
		given().
			spec(this.spec).
			body("{name: \"Test Country\",alpha2_code: \"TC\",alpha3_code: \"TCY\"}").
			contentType(ContentType.JSON).
		when().
			request().
			post("country").
		then().
			log().all().
			statusCode(400);
	}

}
