package SOURCE_CODE;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MemberOfButton {

	String ButtonName;
	ThreadLocal<WebDriver> myWD;
	SFDCAutomationFW autoFW;
	String xpath;
	
	MemberOfButton(String BN)
	{
		ButtonName = BN;
		
						
		xpath = "(//input[normalize-space(@value)='"+ButtonName+"' and contains(normalize-space(@class),'btn')])[1]";
		
		
		myWD = SFDCAutomationFW.myWD;
	}
	
	
	/**
	 * @Author Sourav Mukherjee
	 * @Description Clicks on Salesforce OOB button
	 * @return boolean
	 * @throws Exception
	 */
	public boolean Click() throws Exception
	{
		try
		{
			//Thread.sleep(2000L);
			if(SFDCAutomationFW.WaitForElement(xpath,30))
			{
				
				SFDCAutomationFW.MouseMove(xpath);
				//System.out.println("just after mousemove");
				if (myWD.toString().contains("InternetExplorerDriver") || myWD.toString().contains("SafariDriver"))
				{
					((JavascriptExecutor) myWD.get()).executeScript("arguments[0].click();", myWD.get().findElement(By.xpath(xpath)));
					System.out.println("Clicked on the button ("+ButtonName+")");
				
					return true;
				}
				else 
				{
					//System.out.println("else condition");
					myWD.get().findElement(By.xpath(xpath)).click();
					
					return true;
				}
			}
			else
			{
				System.out.println("Unable to click on the button ("+ButtonName+")");
				
				return false;
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println("Unable to click on the button ("+ButtonName+").Xpath:"+xpath);
			
			e.printStackTrace();
			return false;
		}
	}
	
}
