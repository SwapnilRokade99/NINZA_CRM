package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	WebDriver driver;
	public CreateContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath = "//span[text()='Create Contact']") private WebElement CreateContactBtn;
	@FindBy(name = "organizationName") private WebElement OrgTF;
	
}