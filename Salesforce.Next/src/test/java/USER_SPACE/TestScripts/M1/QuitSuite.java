package USER_SPACE.TestScripts.M1;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.SFDCAutomationFW;
import USER_SPACE.ObjectRepository.LeadScreen;

public class QuitSuite {
	
	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<String> grid = new ThreadLocal<String>();
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	
	@AfterSuite
	public void MyQuitTest() throws Exception 
	{
		System.out.println("getting invoked");
		SFDCAutomationFW.Quit();
	}
	
}


