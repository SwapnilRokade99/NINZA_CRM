package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class AddProductPage {
	
		WebDriver driver;
		public AddProductPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
		@FindBy(name = "productName") private WebElement ProductNameTF;
		//@FindBys({@FindBy(xpath = "productName"),@FindBy(name = "productName")}) private WebElement ProductNameTF;
		@FindBy(name = "productCategory") private WebElement productCategoryDD;
		@FindBy(name = "quantity") private WebElement QuantityTF;
		@FindBy(name = "price") private WebElement PricePerUnitTF;
		@FindBy(name = "vendorId") private WebElement VendorIdDD;
		@FindBy(xpath = "//button[text()='Add']") private WebElement AddBtn;
		
		
		public WebElement getProductNameTF() {
			return ProductNameTF;
		}
		public WebElement getProductCategoryDD() {
			return productCategoryDD;
		}
		public WebElement getQuantityTF() {
			return QuantityTF;
		}
		public WebElement getPricePerUnitTF() {
			return PricePerUnitTF;
		}
		public WebElement getVendorIdDD() {
			return VendorIdDD;
		}
		public WebElement getAddBtn() {
			return AddBtn;
		}

}
