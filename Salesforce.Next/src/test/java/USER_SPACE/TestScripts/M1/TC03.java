package USER_SPACE.TestScripts.M1;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.SFDCAutomationFW;

public class TC03 {
	
	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	

	@Parameters("browser")
	@Test
	public void MyTestScenario(String browser) throws Exception 
	{
		brow.set(browser);
		
		System.out.println("from TC03 --> Browser:"+brow.get());
		sfdc.get().OpenURL(null, "https://login.salesforce.com", brow.get());
		sfdc.get().LoginToSFDC("matt.young@cognizant.com","Welcome1");
		
	}
	
}

