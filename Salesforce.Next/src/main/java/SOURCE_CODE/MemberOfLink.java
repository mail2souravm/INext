package SOURCE_CODE;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberOfLink {

	int count = 0;
	String Link;
	ThreadLocal<WebDriver> myWD;
	
	String xpath;
	List<WebElement> we;

	MemberOfLink(String LN) {
		Link = LN;
		myWD = SFDCAutomationFW.myWD;

	}

	/**
	 * @author Sourav
	 * @return boolean
	 * @throws Exception
	 * @Description Clicks on the supplied hyperlink with Exact match
	 */
	public boolean Click() throws Exception {
		try {
			if (myWD.get().findElement(By.linkText(Link)).isDisplayed()) {
				myWD.get().findElement(By.linkText(Link)).click();
				return true;
				
			} else {
				
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


}
