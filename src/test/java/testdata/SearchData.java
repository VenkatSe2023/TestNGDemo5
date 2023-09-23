package testdata;

import org.testng.annotations.DataProvider;

public class SearchData {
	@DataProvider(name="searchdata")
	public Object[][] searchData() {
		
		Object[][] data = new Object[3][1];
		data[0][0]="Selenium";
		data[1][0]="UFT";
		data[2][0]="Appium";
				
		return data;
	}
}
