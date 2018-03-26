package USER_SPACE.TestScripts.M1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.SFDCAutomationFW;
import USER_SPACE.ObjectRepository.LeadScreen;

public class TC_CreateOpportunity {

	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	

	@Parameters("browser")
	@Test
	public void MyTestScenario(String browser) throws Exception 
	{
		
		brow.set(browser);
		Thread.sleep(2000L);
		System.out.println("from TC_CreateOpportunity --> Browser:"+brow.get());
		sfdc.get().OpenURL(null, "https://login.salesforce.com", brow.get());
		sfdc.get().LoginToSFDC("mail2souravm@gmail.com","Welcome400");
		sfdc.get().Link("Opportunities").Click();
		
		
		
		sfdc.get().Button("New").Click();
		sfdc.get().Button("Save").Click();
		sfdc.get().Field("Opportunity Name").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("Close Date").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("Stage").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		sfdc.get().Field("Multi_Opp_Test").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		
		sfdc.get().Field("Opportunity Name").Type("OPP_"+sfdc.get().GetCurrentDateTimeStamp());
		
		sfdc.get().Field("Close Date").Type("6/10/2018");
		//sfdc.get().SelectFromDateLookup("2018", "June", "10");
		sfdc.get().Field("Stage").SelectPL("Prospecting");
		
		sfdc.get().Field("Multi_Opp_Test").MultiSelectAddAll();
		Thread.sleep(1000L);
		sfdc.get().Button("Save").Click();
		
		sfdc.get().LogOff();
		
		
		
		
		
		
		
	}
	
	
	
}
