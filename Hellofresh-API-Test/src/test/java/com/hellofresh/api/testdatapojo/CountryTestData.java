package com.hellofresh.api.testdatapojo;

import com.hellofresh.utils.ExcelBean;
import com.hellofresh.utils.ExcelColumnHeader;
@ExcelBean
public class CountryTestData {
	@ExcelColumnHeader(columnHeader="iso2Code", dataType="String")
	private String iso2Code;
	
	@ExcelColumnHeader(columnHeader="name", dataType="String")
	private String countryName;
	
	@ExcelColumnHeader(columnHeader="alpha2Code", dataType="String")
	private String alpha2Code;
	
	@ExcelColumnHeader(columnHeader="alpha3Code", dataType="String")
	private String alpha3Code;

	public String getIso2Code() {
		return iso2Code;
	}

	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public String getAlpha3Code() {
		return alpha3Code;
	}

	public void setAlpha3Code(String alpha3Code) {
		this.alpha3Code = alpha3Code;
	}
	
	
	

}
