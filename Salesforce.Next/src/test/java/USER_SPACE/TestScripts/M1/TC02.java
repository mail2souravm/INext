package USER_SPACE.TestScripts.M1;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.SFDCAutomationFW;

public class TC02 {
	
	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	

	@Parameters("browser")
	@Test
	public void MyTestScenario(String browser) throws Exception 
	{
		brow.set(browser);
		
		System.out.println("from TC02 --> Browser:"+brow.get());
		sfdc.get().OpenURL(null, "https://login.salesforce.com", brow.get());
		sfdc.get().LoginToSFDC("matt.young@cognizant.com","Welcome1");
		sfdc.get().Link("Leads").Click();
		sfdc.get().Button("New").Click();
		sfdc.get().Button("Save").Click();
		sfdc.get().Field("Last Name").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("Company").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("First Name").Type(sfdc.get().GetCurrentDateTimeStamp());
		sfdc.get().Field("Company").Type(sfdc.get().GetCurrentDateTimeStamp());
		sfdc.get().Button("Save").Click();
		Thread.sleep(2000L);
		sfdc.get().LogOff();
	}
	
}


