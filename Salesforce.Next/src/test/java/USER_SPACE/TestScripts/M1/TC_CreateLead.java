package USER_SPACE.TestScripts.M1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.DB;
import SOURCE_CODE.SFDCAutomationFW;
import USER_SPACE.ObjectRepository.LeadScreen;
import USER_SPACE.Prerequisite.DataSetup;

public class TC_CreateLead {

	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<String> grid = new ThreadLocal<String>();
	public static final ThreadLocal<String> platform = new ThreadLocal<String>();
	public static final ThreadLocal<String> udid = new ThreadLocal<String>();
	public static final ThreadLocal<String> devicename = new ThreadLocal<String>();
	public static final ThreadLocal<String> version = new ThreadLocal<String>();
	public static final ThreadLocal<String> appium_url = new ThreadLocal<String>();
	
	
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	@Parameters({"isgrid","browser","platform","udid","devicename","version","appium_host_url"})
	@Test
	public void MyTestScenario(String isgrid, String browser, String platform, String udid, String devicename,String version, String appium_host_url) throws Exception 
	{
				
		try {
			
			
		this.grid.set(isgrid);
		this.brow.set(browser);
		this.platform.set(platform);
		this.udid.set(udid);
		this.devicename.set(devicename);
		this.version.set(version);
		this.appium_url.set(appium_host_url);
		
		//***************** Test Case Body ***********************
		
		long threadId = Thread.currentThread().getId();
		System.out.println("This task is performed by:"+threadId);
		Thread.sleep(2000L);
		System.out.println("from TC_CreateLead --> Browser:"+brow.get());
		sfdc.get().OpenURL(null, "https://login.salesforce.com",grid.get(),brow.get(),this.platform.get(), this.udid.get(),this.devicename.get(),this.version.get(),this.appium_url.get());
		sfdc.get().LoginToSFDC("mail2souravm@gmail.com","Welcome400");
		sfdc.get().SelectApplication("Sales");
		sfdc.get().Link("Leads").Click();
		LeadScreen.NewButton().Click();  //use of object repository
		LeadScreen.SaveButton().Click();
		Thread.sleep(1000L);
		LeadScreen.LastNameField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
		LeadScreen.CompanyField().VerifyFieldErrorMsgOnEditPage("You must enter a value");
		Thread.sleep(2000L);
		LeadScreen.LeadStatusField().SelectPL("Working - Contacted");
		LeadScreen.CampaignField().SelectFromLookup("OBJECT_REPO");
		LeadScreen.FirstNameField().Type(sfdc.get().GetCurrentDateTimeStamp());
		LeadScreen.LastNameField().Type(sfdc.get().GetCurrentDateTimeStamp());
		LeadScreen.CompanyField().Type(sfdc.get().GetCurrentDateTimeStamp());
		//Thread.sleep(5000L);
		LeadScreen.SaveButton().Click();
		Thread.sleep(5000L);
		
		DB.Connect(DataSetup.TC_CreateLead);
		DB.UpdateXLCell("TC_CreateLead",LeadScreen.NameField().GetViewOnlyValue(), "LeadName", "SN", "SN01"+SFDCAutomationFW.getParallelID());
		DB.UpdateXLCell("TC_CreateLead",SFDCAutomationFW.GetCurrentURL(), "SFDCURL", "SN", "SN01"+SFDCAutomationFW.getParallelID());
		
		
		
		//LeadScreen.SaveButton().Click();
		
		//sfdc.get().Button("New").Click();
		//sfdc.get().Button("Save").Click();
		//sfdc.get().Field("Last Name").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		//sfdc.get().Field("Company").VerifyFieldErrorMsgOnEditPage("You must enter a value");
		//Thread.sleep(1000L);
		//sfdc.get().Field("First Name").Type(sfdc.get().GetCurrentDateTimeStamp());
		//sfdc.get().Field("Company").Type(sfdc.get().GetCurrentDateTimeStamp());
		//sfdc.get().Field("Last Name").Type(sfdc.get().GetCurrentDateTimeStamp());
		Thread.sleep(1000L);
		//sfdc.get().Button("Save").Click();
		//Thread.sleep(1000L);
		sfdc.get().LogOff();
		
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}
