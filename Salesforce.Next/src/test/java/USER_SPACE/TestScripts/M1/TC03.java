package USER_SPACE.TestScripts.M1;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.SFDCAutomationFW;
import USER_SPACE.ObjectRepository.LeadScreen;

public class TC03 {
	
	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<String> grid = new ThreadLocal<String>();
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	

	@Parameters({ "browser", "isgrid" })
	@Test
	public void MyTestScenario(String browser, String isgrid) throws Exception 
	{
		brow.set(browser);
		grid.set(isgrid);
		System.out.println("from TC03 --> Browser:"+brow.get());
		sfdc.get().OpenURL(null, "https://login.salesforce.com", brow.get(),grid.get());
		sfdc.get().LoginToSFDC("matt.young@cognizant.com","Welcome1");
		sfdc.get().Link("Leads").Click();
		/*
		sfdc.get().Button("New").Click();
		sfdc.get().Button("Save").Click();
		sfdc.get().Field("Last Name").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("Company").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("First Name").Type(sfdc.get().GetCurrentDateTimeStamp());
		sfdc.get().Field("Company").Type(sfdc.get().GetCurrentDateTimeStamp());
		sfdc.get().Field("Last Name").Type(sfdc.get().GetCurrentDateTimeStamp());
		sfdc.get().Button("Save").Click();
		*/
		LeadScreen.NewButton().Click();
		LeadScreen.SaveButton().Click();
		Thread.sleep(1000L);
		LeadScreen.LastNameField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
		LeadScreen.CompanyField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
		
		LeadScreen.FirstNameField().Type(sfdc.get().GetCurrentDateTimeStamp());
		LeadScreen.LastNameField().Type(sfdc.get().GetCurrentDateTimeStamp());
		LeadScreen.CompanyField().Type(sfdc.get().GetCurrentDateTimeStamp());
		Thread.sleep(1000L);
		LeadScreen.SaveButton().Click();
		//Thread.sleep(2000L);
		//sfdc.get().LogOff();
	}
	
}


