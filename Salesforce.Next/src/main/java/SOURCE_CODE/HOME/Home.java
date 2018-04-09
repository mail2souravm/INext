package SOURCE_CODE.HOME;

import SOURCE_CODE.SFDCAutomationFW;

public class Home {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		System.out.println("Killing all driver and browser process");
		
		
		SFDCAutomationFW._killProcess("chromedriver.exe");
		SFDCAutomationFW._killProcess("chrome.exe");
		
		SFDCAutomationFW._killProcess("geckodriver.exe");
		SFDCAutomationFW._killProcess("firefox.exe");
		
		SFDCAutomationFW._killProcess("IEDriverServer.exe");
		SFDCAutomationFW._killProcess("iexplore.exe");
		
		//System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir").replace("\\", "/") + "/src/test/resources/chromedriver.exe");
		
	}

}
