package SOURCE_CODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MemberOfField {
	String fieldname;
	ThreadLocal<WebDriver> myWD;
	
	String xpath;
	List<WebElement> allposblefieldelements;
	WebElement getsingleWebelement;
	
	MemberOfField(String FN)
	{
		fieldname = FN;
		myWD = SFDCAutomationFW.myWD;
		
	}

	
	
	/**
	 * @param
	 * @author Sourav Mukherjee
	 * @Description Clicks on the view only value of a field
	 * @return boolean
	 * @throws Exception
	 */
	public boolean ClickONViewOnlyValue() throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
				
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			//Thread.sleep(10000L);
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			getsingleWebelement.click();
			SFDCAutomationFW.AddToXLLogs("Clicked on the value of field ("+fieldname+").", "Pass");
			System.out.println("Clicked on the value of field ("+fieldname+").");
			return true;
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the value of field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the value of field ("+fieldname+").");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the value of field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the value of field ("+fieldname+").");
			e.printStackTrace();
			return false;
		}
	}

/**
	 * @author Cognizant
	 * @Description Clicks on the hyperlink present beside a field in View Details page
	 * @returns true if click is successful else returns false
	 * @throws Exception
	 */
	public boolean ClickONViewOnlyLinkValue() throws Exception
	{
		//xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/div[1]/a[1]";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::a[1]";		
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			//Thread.sleep(10000L);
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			getsingleWebelement.click();
			SFDCAutomationFW.AddToXLLogs("Clicked on the value of field ("+fieldname+").", "Pass");
			System.out.println("Clicked on the value of field ("+fieldname+").");
			return true;
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the value of field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the value of field ("+fieldname+").");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the value of field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the value of field ("+fieldname+").");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @author Cognizant
	 * @Description Clicks on a particular field value in the Edit page
	 * @return
	 * @throws Exception
	 */
	public boolean Click() throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'  or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		try
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			getsingleWebelement.click();
			SFDCAutomationFW.AddToXLLogs("Clicked on the ("+fieldname+") Field.", "Pass");
			System.out.println("Clicked on the ("+fieldname+") Field.");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			SFDCAutomationFW.AddToXLLogs("Unable to click on the field ("+fieldname+")", "Pass");
			System.out.println("Unable to click on the field ("+fieldname+").");
			return false;
		}
	}
	
	
	/**
	 * @param input any string
	 * @author Sourav Mukherjee
	 * @Description Type any value in textbox, textarea and also in textbox having lookup field
	 * @return boolean
	 * @throws Exception
	 */
	public boolean Type(String Value) throws Exception
	{
		//working xpath = "((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";

		//xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		
		xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden'])[1]";
		
		
		
		
		try
		{
					
			
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			//Thread.sleep(10000L);
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
		
			//System.out.println("Inside Type:"+allposblefieldelements.size());
			getsingleWebelement.clear();
			getsingleWebelement.sendKeys(Value);
		
			//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
		/*
		for(WebElement we1:allposblefieldelements)
		{
			
			System.out.println("TagName:"+we1.getTagName()+"|"+"class:"+we1.getAttribute("class")+"|"+"id:"+we1.getAttribute("id")+"|"+"type:"+we1.getAttribute("type")+"|"+"text:"+we1.getText()+"|");

		}
		*/
			SFDCAutomationFW.AddToXLLogs("Entered the value ("+Value+") in the field ("+fieldname+").", "Pass");
			System.out.println("Entered the value ("+Value+") in the field ("+fieldname+").");
			return true;
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to enter the value ("+Value+") in the field ("+fieldname+").", "Fail");
			System.out.println("Unable to enter the value ("+Value+") in the field ("+fieldname+").");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to enter the value ("+Value+") in the field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to enter the value ("+Value+") in the field ("+fieldname+") when xpath is ("+xpath+")");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @author Sourav Mukherjee
	 * @Description Verifies the field level error message in the edit page
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyFieldErrorMsgOnEditPage(String ErrorMessage) throws Exception
	{
		//xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::*[@type !='hidden' and contains(@class,'errorMsg')]";
		xpath = "//*[contains(normalize-space(text()),'"+fieldname+"')][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[contains(@class,'errorMsg')]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
		
			//System.out.println("Inside Type:"+allposblefieldelements.size());
			if (getsingleWebelement.getText().trim().contains(ErrorMessage.trim()))
			{
				System.out.println("Successfully verified the error message ("+ErrorMessage+") on field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully verified the error message ("+ErrorMessage+") on field ("+fieldname+")", "Pass");
				return true;
			}
			else
			{
				System.out.println("Unable to verify the error message on field ("+fieldname+"). Actual Error Message was ("+getsingleWebelement.getText().trim()+") when Expected Error Message is ("+ErrorMessage+")");
				SFDCAutomationFW.AddToXLLogs("Unable to verify the error message on field ("+fieldname+"). Actual Error Message was ("+getsingleWebelement.getText().trim()+") when Expected Error Message is ("+ErrorMessage+")", "Fail");
				return false;
			}
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath, "Fail");
			System.out.println("Unable to find the element when xpath is:"+xpath);
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath, "Fail");
			System.out.println("Unable to find the element when xpath is:"+xpath);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param Value
	 * @return boolean
	 * @throws Exception
	 * @Description Selects pick list field value by visible text
	 */
	public boolean SelectPL(String Value) throws Exception
	{
		//Working xpath = "((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[local-name()='select'])[1]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,30))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			//System.out.println("Inside SelectPL:");
		
			if (getsingleWebelement.getAttribute("type").trim().equals("select-one")||(getsingleWebelement.getTagName().trim().equals("select")))
			{
				Select s = new Select(getsingleWebelement);
				s.selectByVisibleText(Value);
				
				SFDCAutomationFW.AddToXLLogs("Selected the value ("+Value+") from picklist field ("+fieldname+").", "Pass");
				System.out.println("Selected the value ("+Value+") from picklist field ("+fieldname+").");
				return true;
			}
			else
			{
				SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+").", "Fail");
				System.out.println("Unable to find the pick list field ("+fieldname+").");
				return false;
			}
			//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
			
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")");
			return false;
		}
	}
	
	
	public boolean SelectPL_StartsWith(String Value) throws Exception
	{
		//Working xpath = "((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[local-name()='select']";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,30))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			//System.out.println("Inside SelectPL:");
		
			if (getsingleWebelement.getAttribute("type").trim().equals("select-one")||(getsingleWebelement.getTagName().trim().equals("select")))
			{
				Select s = new Select(getsingleWebelement);
				List<WebElement> eachPLValue = s.getAllSelectedOptions();
					
				for(WebElement eo: eachPLValue)
				{
					
					if(eo.getText().trim().startsWith(Value))
					{
						s.selectByVisibleText(eo.getText().trim());
						SFDCAutomationFW.AddToXLLogs("Selected the value ("+eo.getText().trim()+") from picklist field ("+fieldname+").", "Pass");
						System.out.println("Selected the value ("+eo.getText().trim()+") from picklist field ("+fieldname+").");
						return true;
					}
				}
				SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+").", "Fail");
				System.out.println("Unable to find the pick list field ("+fieldname+").");
				return false;
				
			}
			else
			{
				SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+").", "Fail");
				System.out.println("Unable to find the pick list field ("+fieldname+").");
				return false;
			}
			//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
			
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")");
			return false;
		}
	}
	/**
     * @author Sourav Mukherjee
     * @Description Selects the picklist value by index where index starts from zero (0)
     * @param Index
     * @return boolean
     * @throws Exception
     */
     public boolean SelectPLValueByIndex(int Index) throws Exception
     {
            //Working xpath = "((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
            xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[local-name()='select']";
            //xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::select[1]";
            try
            {
            if (SFDCAutomationFW.WaitForElement(xpath,10))
            {
                   getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
                   //System.out.println("Inside SelectPL:");
            
                   if (getsingleWebelement.getAttribute("type").trim().equals("select-one")||(getsingleWebelement.getTagName().trim().equals("select")))
                   {
                         Select s = new Select(getsingleWebelement);
                         //s.selectByVisibleText(Value);
                         s.selectByIndex(Index);
                         
                         SFDCAutomationFW.AddToXLLogs("Selected the value ("+s.getFirstSelectedOption()+") by index from picklist field ("+fieldname+").", "Pass");
                         System.out.println("Selected the value ("+s.getFirstSelectedOption()+") by index from picklist field ("+fieldname+").");
                         return true;
                   }
                   else
                   {
                         SFDCAutomationFW.AddToXLLogs("Unable to find the pick list value for field ("+fieldname+").", "Fail");
                         System.out.println("Unable to find the pick list value for field ("+fieldname+").");
                         return false;
                   }
                   //System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
                   
            }
            else
            {
                   SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
                   System.out.println("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")");
                   return false;
            }
            }
            catch(Exception e)
            {
                   //System.out.println("Inside Catch");
                   SFDCAutomationFW.AddToXLLogs("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
                   System.out.println("Unable to find the pick list field ("+fieldname+") when xpath is ("+xpath+")");
                   return false;
            }
     }
     

	/**
	 * @param Value semicolon separated value for multiple
	 * @return boolean
	 * @throws Exception
	 * @Description Selects multiple values in the multi-select picklist field and adds to the Chosen list.
	 */
	public boolean MultiSelectAdd(String Value) throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][1]/ancestor::select[1]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,30))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			Select s = new Select(getsingleWebelement);
		
			//System.out.println("Inside multiSelect Add:");
		
			List<String> eachPLValue = Arrays.asList(Value.split(";"));
			for(String str: eachPLValue)
			{
				s.selectByVisibleText(str);
				System.out.println("Multiselect pick list values are:"+str.trim());
				getsingleWebelement = myWD.get().findElement(By.xpath("((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='img'][1]"));		
				getsingleWebelement.click();
			}
		
		
		
			//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
			SFDCAutomationFW.AddToXLLogs("Successfully added values ("+Value+") for multi-select pick list field ("+fieldname+").", "Pass");	
			System.out.println("Successfully added values ("+Value+") for multi-select pick list field ("+fieldname+").");
			return true;
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to add values ("+Value+") for multi-select pick list field ("+fieldname+") when xpath is("+xpath+")", "Fail");	
			System.out.println("Unable to add values ("+Value+") for multi-select pick list field ("+fieldname+") when xpath is("+xpath+")");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to add values ("+Value+") for multi-select pick list field ("+fieldname+") when xpath is("+xpath+")", "Fail");	
			System.out.println("Unable to add values ("+Value+") for multi-select pick list field ("+fieldname+") when xpath is("+xpath+")");
			return false;
		}
	}
	
	/**
	 * @param Value
	 * @return boolean
	 * @throws Exception
	 * @Description Removes multiple values from chosen list to Available list of multi-select pick list field. 
	 * 	
	 *  */
	public boolean MultiSelectRemove(String Value) throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][2]/ancestor::select[1]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,30))
		
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			Select s = new Select(getsingleWebelement);
		
			//System.out.println("Inside multiSelect Remove:");
		
			List<String> eachPLValue = Arrays.asList(Value.split(";"));
			for(String str: eachPLValue)
			{
				s.selectByVisibleText(str);
				System.out.println("Multiselect pick list values are:"+str.trim());
			}
		
			getsingleWebelement = myWD.get().findElement(By.xpath("((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='img'][2]"));		
			getsingleWebelement.click();
		
			//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
			SFDCAutomationFW.AddToXLLogs("Successfully removed values ("+Value+") from multi-select pick list field ("+fieldname+")", "Pass");	
			System.out.println("Successfully removed values ("+Value+") from multi-select pick list field ("+fieldname+")");
			return true;
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to remove values ("+Value+") from multi-select pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");	
			System.out.println("Unable to remove values ("+Value+") from multi-select pick list field ("+fieldname+") when xpath is ("+xpath+")");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to remove values ("+Value+") from multi-select pick list field ("+fieldname+") when xpath is ("+xpath+")", "Fail");	
			System.out.println("Unable to remove values ("+Value+") from multi-select pick list field ("+fieldname+") when xpath is ("+xpath+")");
			return false;
		}
	}
	
	/**
	 * @return boolean
	 * @throws Exception
	 * @Description Selects and Adds all the available values of multi-select pick list field from Available List to Chosen List 
	 */
	public boolean MultiSelectAddAll() throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][1]/ancestor::select[1]";
		String allvalues="";
		try
		{
		WebElement getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
		Select s = new Select(getsingleWebelement);
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		myWD.get().findElement(By.xpath(xpath)).sendKeys(selectAll);
		
		//s.selectByVisibleText(s.getOptions().toString());
				
		List<WebElement> options = s.getOptions();
		for(WebElement eachOption: options)
		{
			//Thread.sleep(3000L);
			//s.selectByVisibleText(eachOption.getText().trim());
			//s.selectByValue(eachOption.getText().trim());			
			if (allvalues.equals(""))
			{
				allvalues = eachOption.getText().trim();
			}
			else
			{
				allvalues = allvalues +","+ eachOption.getText().trim();
			}
		}
		
		//getsingleWebelement = myWD.get().findElement(By.xpath("((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='img'][1]"));		
		getsingleWebelement = myWD.get().findElement(By.xpath("((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::img[contains(normalize-space(@title),'Add') and contains(normalize-space(@alt),'Add')][1]"));
		Thread.sleep(3000L);
		getsingleWebelement.click();
		Thread.sleep(3000L);
		System.out.println("Successfully added values ("+allvalues+") in the multi-select pick list field "+fieldname);
		SFDCAutomationFW.AddToXLLogs("Successfully added values ("+allvalues+") in the multi-select pick list field "+fieldname, "Pass");
		return true;
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element while adding all values to multiselect picklist field ("+fieldname+") when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element while adding all values to multiselect picklist field ("+fieldname+") when xpath is:"+xpath, "Fail");
			return false;
		}
	}
	/**
	 * @return boolean
	 * @throws Exception
	 * @Description Selects and Removes all the available values of multi-select pick list field from Chosen List to Available List. 
	 */
	public boolean MultiSelectRemoveAll() throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][2]/ancestor::select[1]";
		String allvalues="";
		try
		{
		WebElement getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
		Select s = new Select(getsingleWebelement);
		
		//myWD.get().findElement(By.xpath(xpath+"/optgroup[normalize-space(@label)='Chosen'][1]")).click();
		//String selectAll = Keys.chord(Keys.CONTROL, "a");
		//myWD.get().findElement(By.xpath(xpath)).sendKeys(selectAll);
		
		List<WebElement> options = s.getOptions();
			
		
		for(WebElement eachOption: options)
		{			
			s.selectByVisibleText(eachOption.getText().trim());
			
			if (allvalues.equals(""))
			{
				allvalues = eachOption.getText().trim();
			}
			else
			{
				allvalues = allvalues +","+ eachOption.getText().trim();
			}
			myWD.get().findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL);
			//r.keyPress(KeyEvent.VK_CONTROL);
		}
		//r.keyRelease(KeyEvent.VK_CONTROL);		
		getsingleWebelement = myWD.get().findElement(By.xpath("((//*[text()='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='img'][2]"));		
		getsingleWebelement.click();
		System.out.println("Successfully removed values ("+allvalues+") in the multi-select pick list field "+fieldname);
		SFDCAutomationFW.AddToXLLogs("Successfully removed values ("+allvalues+") in the multi-select pick list field "+fieldname, "Pass");
		return true;
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element while removing all values to multiselect picklist field ("+fieldname+") when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element while removing all values to multiselect picklist field ("+fieldname+") when xpath is:"+xpath, "Fail");
			return false;
		}
	}
	
	
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit
	 * @Description Verify selected/default value displayed in the pick list field and sends the message to the Log
	 * @param Value
	 * @return boolean
	 * @throws Exception
	 * 
	 */
	public boolean VerifyPLDefaultValue(String Value) throws Exception
	{
		
		//xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[local-name()='select']";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			//System.out.println("Inside SelectPL:");
		
			if (getsingleWebelement.getAttribute("type").trim().equals("select-one")||(getsingleWebelement.getTagName().trim().equals("select")))
			{
				Select s = new Select(getsingleWebelement);
				if (s.getFirstSelectedOption().getText().trim().equals(Value))
				{
					SFDCAutomationFW.AddToXLLogs("Successfully verified the selected value ("+Value+") in the picklist field ("+fieldname+")", "Pass");
					System.out.println("Successfully verified the selected value ("+Value+") in the picklist field ("+fieldname+")");
					return true;
				}        	
				else
				{
					SFDCAutomationFW.AddToXLLogs("The value ("+s.getFirstSelectedOption()+") displayed in the pick list field ("+fieldname+") is not expected. Expected value is: ("+Value+")", "Fail");
					System.out.println("The value ("+s.getFirstSelectedOption()+") displayed in the pick list field ("+fieldname+") is not expected. Expected value is: ("+Value+")");
					return false;
				}
			}
			else
			{
				SFDCAutomationFW.AddToXLLogs("Unable to find the select element while verifying selected picklist value when xpath is ("+xpath+")", "Fail");
				System.out.println("Unable to find the select element while verifying selected picklist value when xpath is ("+xpath+")");
				return false;
			}
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the parent element while verifying selected picklist value when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the parent element while verifying selected picklist value when xpath is ("+xpath+")");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the parent element while verifying selected picklist value when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the parent element while verifying selected picklist value when xpath is ("+xpath+")");
			return false;

		}
		//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
			 
		
	}
	
	
	/**
	 * @author Cognizant
	 * @PageDisplayMode Edit
	 * @Description Read the displayed value in the picklist field when the page is opened in Edit mode and returns the value.
	 * @return Value displayed in the picklist field in Edit mode on success and returns blank value on Failure
	 * @throws Exception
	 * 
	 */
	public String GetPLDefaultValue() throws Exception
	{
		
		//xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[local-name()='select']";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			//System.out.println("Inside SelectPL:");
		
			if (getsingleWebelement.getAttribute("type").trim().equals("select-one")||(getsingleWebelement.getTagName().trim().equals("select")))
			{
				Select s = new Select(getsingleWebelement);
				String Value = s.getFirstSelectedOption().getText().trim();
				SFDCAutomationFW.AddToXLLogs("The value displayed in the field ("+fieldname+") is ("+Value+")", "Pass");
				System.out.println("The value displayed in the field ("+fieldname+") is ("+Value+")");
				return Value;
			}
			else
			{
				SFDCAutomationFW.AddToXLLogs("Unable to find the select element while reading the value in field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
				System.out.println("Unable to find the select element while reading the value in field ("+fieldname+") when xpath is ("+xpath+")");
				return "";
			}
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the parent element while reading the value in field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the parent element while reading the value in field ("+fieldname+") when xpath is ("+xpath+")");
			return "";
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the parent element while reading the value in field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the parent element while reading the value in field ("+fieldname+") when xpath is ("+xpath+")");
			return "";
		}
		//System.out.println("TagName:"+getsingleWebelement.getTagName()+"|"+"class:"+getsingleWebelement.getAttribute("class")+"|"+"id:"+getsingleWebelement.getAttribute("id")+"|"+"type:"+getsingleWebelement.getAttribute("type")+"|"+"text:"+getsingleWebelement.getText()+"|");
			 
		
	}
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit
	 * @Description Verify pick list field values from Edit page and sends the message to the Log
	 * @param Values
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyPLValue(String Values) throws Exception
	{
		List<String> avail = new ArrayList<String>();
		
		//xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='select']";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[local-name()='select']";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,2))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			//System.out.println("Inside SelectPL:");
		
			if (getsingleWebelement.getAttribute("type").trim().equals("select-one")||(getsingleWebelement.getTagName().trim().equals("select")))
			{
				Select s = new Select(getsingleWebelement);
				List<WebElement> AllValues_elements = s.getOptions();	
				for(WebElement eachElement: AllValues_elements)
				{
					avail.add(eachElement.getText().trim());
					
				}
			}
			else
			{
				SFDCAutomationFW.AddToXLLogs("Unable to find the select element while verifying picklist values when xpath is ("+xpath+")", "Fail");
				System.out.println("Unable to find the select element while verifying picklist values when xpath is ("+xpath+")");
				return false;
			}
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the parent element while verifying selected picklist value when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to find the parent element while verifying selected picklist value when xpath is ("+xpath+")");
			return false;
		}
		List<String> ExpectedValues = Arrays.asList(Values.split(";"));
		String failedPLValue="";
		
		for(String exp:ExpectedValues)
		{
			if (!avail.contains(exp))
			{
				if (failedPLValue!="")
				{
					failedPLValue = failedPLValue + ";" + exp;
				}
				else
				{
					failedPLValue = exp;
				}
			}
		}
		
		if(failedPLValue=="")
		{
			System.out.println("Successfully verified the Available List of Values("+Values+") from PickList field ("+fieldname+")");
			SFDCAutomationFW.AddToXLLogs("Successfully verified the Available List of Values("+Values+") from picklist field ("+fieldname+")", "Pass");
			return true;
		}
		else
		{
			System.out.println("Could not find pick list values ("+failedPLValue+") in the available list of field("+fieldname+").");
			SFDCAutomationFW.AddToXLLogs("Could not find pick list values ("+failedPLValue+") in the available list of field("+fieldname+").", "Fail");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to verify picklist values when xpath is ("+xpath+")", "Fail");
			System.out.println("Unable to verify picklist values when xpath is ("+xpath+")");
			return false;

		}
			 
		
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit
	 * @Description Verify the values displayed in Available list of a Multi-Select pick list field in Edit page
	 * @param Values
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyMPLAvailable(String Values) throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][1]/ancestor::select[1]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,1))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			Select s = new Select(getsingleWebelement);
			List<WebElement> options = s.getOptions();

			List<String> avail = new ArrayList<String>();
		
			for(WebElement eachOption: options)
			{
				avail.add(eachOption.getText().trim());
				//System.out.println("eachOption: "+eachOption.getText().trim());
			}
		
		
			List<String> AllavailValues = Arrays.asList(Values.split(";"));
			String failedPLValue="";
			for(String exp:AllavailValues)
			{
				if (!avail.contains(exp))
				{
					if (failedPLValue!="")
					{
						failedPLValue = failedPLValue + ";" + exp;
					}
					else
					{
						failedPLValue = exp;
					}
				}
			}
			if(failedPLValue=="")
			{
				System.out.println("Successfully verified the Available List of Values from Multiselect picklist field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully verified the Available List of Values from Multiselect picklist field ("+fieldname+")", "Pass");
				return true;
			}
			else
			{
				System.out.println("Could not find multi select pick list values ("+failedPLValue+") in the available list of field("+fieldname+").");
				SFDCAutomationFW.AddToXLLogs("Could not find multi select pick list values ("+failedPLValue+") in the available list of field("+fieldname+").", "Fail");
				return false;
			}
		
		}
		else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is ("+xpath+").", "Fail");
			System.out.println("Unable to find the element after waiting for specified time when xpath is ("+xpath+").");
			return false;
		}
		}
		catch(Exception e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath, "Fail");
			System.out.println("Unable to find the element when xpath is:"+xpath);
			return false;
			
		}
}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit
	 * @Description Verify the values displayed in Chosen list of a Multi-Select pick list field in Edit page
	 * @param Values
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyMPLChosen(String Values) throws Exception
	{
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor::td[1])/following-sibling::td[1]/descendant-or-self::*[local-name()='optgroup'][2]/ancestor::select[1]";
		try
		{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			Select s = new Select(getsingleWebelement);
			List<WebElement> options = s.getOptions();

			List<String> avail = new ArrayList<String>();
		
			for(WebElement eachOption: options)
			{
				avail.add(eachOption.getText().trim());
				//System.out.println("eachOption: "+eachOption.getText().trim());
			}
		
		
			List<String> AllavailValues = Arrays.asList(Values.split(";"));
			String failedPLValue="";
			for(String exp:AllavailValues)
			{
				if (!avail.contains(exp))
				{
					if (failedPLValue!="")
					{
						failedPLValue = failedPLValue + ";" + exp;
					}
					else
					{
						failedPLValue = exp;
					}
				}
			}
			if(failedPLValue=="")
			{
				System.out.println("Successfully verified the Chosen List of Values from Multiselect picklist field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully verified the Chosen List of Values from Multiselect picklist field ("+fieldname+")", "Pass");
				return true;
			}
			else
			{
				System.out.println("Could not find multi select pick list values ("+failedPLValue+") in the Chosen list of field("+fieldname+").");
				SFDCAutomationFW.AddToXLLogs("Could not find multi select pick list values ("+failedPLValue+") in the Chosen list of field("+fieldname+").", "Fail");
				return false;
			}
		}
		else
		{
			System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
			return false;
			
		}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element while verifying Chosen List of multiselect pick list field "+fieldname);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element while verifying Chosen List of multiselect pick list field "+fieldname, "Fail");
			return false;
		}
	}
	
	
	
	/**
	 * @author Sourav
	 * @PageDisplayMode View Only
	 * @Description Verify the value displayed in a field in view only page and send the message to logs
	 * @param Value
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyViewOnlyValueEquals(String Value) throws Exception
	{

		
		//xpath="//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		xpath="//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
		try
		{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{
			if(myWD.get().findElement(By.xpath(xpath)).getText().trim().equals(Value))
			{
				System.out.println("The value("+Value+") has been verified successfully for the field("+fieldname +")");
				SFDCAutomationFW.AddToXLLogs("The value("+Value+") has been verified successfully for the field("+fieldname +")", "Pass");
				return true;
			}
			else
			{
				System.out.println("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim());
				SFDCAutomationFW.AddToXLLogs("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim(), "Fail");
				return false;
			}
		}
		else
		{
			System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
			return false;
		}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return false;
		
		}
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode View Only
	 * @Description Read the value of a field from view details page
	 * @return Returns the displayed value of a Field in success, returns blank string in case failure
	 * @throws Exception
	 */
	public String GetViewOnlyValue() throws Exception
	{
		
		String Value="";
		xpath="//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
		try
		{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{
			Value = myWD.get().findElement(By.xpath(xpath)).getText().trim();
			System.out.println("The value of field ("+fieldname +") is ("+Value+")");
			SFDCAutomationFW.AddToXLLogs("The value of field ("+fieldname +") is ("+Value+")", "Pass");
			return Value;
			
		}
		else
		{
			System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
			return Value;
		}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return "";
		
		}
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode View Only
	 * @Description Verify if the field value contains supplied substring 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyViewOnlyValueContains(String Value) throws Exception
	{
		xpath="//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
		//System.out.println("XXXXXXXXXXXXXXXXXXXXXXX: "+myWD.get().findElement(By.xpath(xpath)).getText());
		try
		{
			if(SFDCAutomationFW.WaitForElement(xpath,10))
			{
				if(myWD.get().findElement(By.xpath(xpath)).getText().trim().contains(Value))
				{
					System.out.println("The value("+Value+") has been verified successfully for the field("+fieldname +")");
					SFDCAutomationFW.AddToXLLogs("The value("+Value+") has been verified successfully for the field("+fieldname +")", "Pass");
					return true;
				}
				else
				{
					System.out.println("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim());
					SFDCAutomationFW.AddToXLLogs("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim(),"Fail");
					return false;
				}
			}
			else
			{
				System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
				SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return false;
		
		}	
	}
	
	/**
	 * @author Cognizant
	 * @param Value
	 * @return true if verified successfully else return false
	 * @Description Verifies that the field value does not contain the supplied string 
	 * @throws Exception
	 */
	public boolean VerifyViewOnlyValueDoesNotContain(String Value) throws Exception
	{
		xpath="//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
		try
		{
			if(SFDCAutomationFW.WaitForElement(xpath,10))
			{
				if(myWD.get().findElement(By.xpath(xpath)).getText().trim().contains(Value))
				{
					System.out.println("The value ("+Value+") contains in the field ("+fieldname +") when not expected");
					SFDCAutomationFW.AddToXLLogs("The value ("+Value+") contains in the field ("+fieldname +") when not expected", "Fail");
					return false;
				}
				else
				{
					System.out.println("Successfully verified that the value ("+Value+") does not contain in the field ("+fieldname +")");
					SFDCAutomationFW.AddToXLLogs("Successfully verified that the value ("+Value+") does not contain in the field ("+fieldname +")","Pass");
					return true;
				}
			}
			else
			{
				System.out.println("Unable to verify the value ("+Value+") of field ("+fieldname+") when xpath is:"+xpath);
				SFDCAutomationFW.AddToXLLogs("Unable to verify the value ("+Value+") of field ("+fieldname+") when xpath is:"+xpath,"Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to verify the value ("+Value+") of field ("+fieldname+") when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to verify the value ("+Value+") of field ("+fieldname+") when xpath is:"+xpath,"Fail");
			return false;
	
		}	
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode View Only
	 * @Description Verify if the field value starts with supplied substring 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyViewOnlyValueStartsWith(String Value) throws Exception
	{
		xpath="//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
		try
		{
			if(SFDCAutomationFW.WaitForElement(xpath,10))
			{
				if(myWD.get().findElement(By.xpath(xpath)).getText().trim().startsWith(Value))
				{
					System.out.println("The value("+Value+") has been verified successfully for the field("+fieldname +")");
					SFDCAutomationFW.AddToXLLogs("The value("+Value+") has been verified successfully for the field("+fieldname +")", "Pass");
					return true;
				}
				else
				{
					System.out.println("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim());
					SFDCAutomationFW.AddToXLLogs("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim(), "Fail");
					return false;
				}
			}
			else
			{
				System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
				SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return false;
		
		}		
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode View Only
	 * @Description Verify if the field value ends with supplied substring 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyViewOnlyValueEndsWith(String Value) throws Exception
	{
		
		xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]";
		try
		{
			if(SFDCAutomationFW.WaitForElement(xpath,10))
			{
				if(myWD.get().findElement(By.xpath(xpath)).getText().trim().endsWith(Value))
				{
					System.out.println("The value("+Value+") has been verified successfully for the field("+fieldname +")");
					SFDCAutomationFW.AddToXLLogs("The value("+Value+") has been verified successfully for the field("+fieldname +")", "Pass");
					return true;
				}
				else
				{
					System.out.println("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim());
					SFDCAutomationFW.AddToXLLogs("Could not find the value("+Value+") for the field("+fieldname +"). Actual Value:"+myWD.get().findElement(By.xpath(xpath)).getText().trim(), "Fail");
					return false;
				}
			}
			else
			{
				System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
				SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return false;
		
		}	
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @Description Verify if the field(text, textarea) value is equal to the supplied string in the displayed edit page
	 * @return boolean
	 * @throws Exception
	 */
	public boolean VerifyEditFieldValue(String Value) throws Exception
	{
		
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		
		//xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try
		{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			System.out.println(getsingleWebelement.getAttribute("value").trim());
			if (getsingleWebelement.getAttribute("value").trim().equals(Value.trim()))
			{
				System.out.println("Successfully verified the value for the field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully verified the value for the field ("+fieldname+")", "Pass");
				return true;
			}
			else
			{
				System.out.println("Could not find the value ("+Value+") for the field ("+fieldname+"). Actual value is ("+getsingleWebelement.getAttribute("value").trim()+")");
				SFDCAutomationFW.AddToXLLogs("Could not find the value ("+Value+") for the field ("+fieldname+"). Actual value is ("+getsingleWebelement.getAttribute("value").trim()+")","Fail");
				return false;
			}
			//System.out.println("Inside Type:"+allposblefieldelements.size());
		}
		else
		{
			System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
			return false;
		}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return false;
		}
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @Description Reads the field (text,textarea) value displayed in the edit page and returns the value  
	 * @return Returns field value on success and returns blank value in case failure
	 * @throws Exception
	 */
	public String GetEditFieldValue() throws Exception
	{
		String Value = "";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='input' or local-name()='textarea') and @type !='hidden']";
		
		//xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1]/following-sibling::*[local-name()='td' or local-name()='th'][1]";
		try
		{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			Value = getsingleWebelement.getAttribute("value").trim();
			
			System.out.println("The value of the field ("+fieldname+") is ("+Value+")");
			SFDCAutomationFW.AddToXLLogs("The value of the field ("+fieldname+") is ("+Value+")", "Pass");
			return Value;
		}
		else
		{
			System.out.println("Unable to read the value of field ("+fieldname+") when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to read the value of field ("+fieldname+") when xpath is: "+xpath, "Fail");
			return Value;
		}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return "";
		}
	}
	/*******************************************************************************************/
	//Following function verifies if the value of a check box in checked/unchecked 			   //
	//in view details page as well as Edit page 											   //
	//																						   //
	/*******************************************************************************************/
	/**
	 * @author Sourav
	 * @PageDisplayMode View Only/Edit Page
	 * @Description Verify if the checkbox field is checked or not checked in Edit as well as View only page 
	 * @return boolean
	 * @throws Exception
	 * @param Checked OR Not Checked
	 */
	public boolean VerifyChkBoxValue(String CheckedORNotChecked) throws Exception
	{
	
		xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='img' or local-name()='input')][1]";
	try
		{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{			
			if (CheckedORNotChecked.equalsIgnoreCase("Checked"))
			{
				if ((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")!=null) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked").equals("true")))
				{
					System.out.println("Successfully verified the value as checked for the checkbox field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Successfully verified the value as checked for the checkbox field ("+fieldname+")", "Pass");
					return true;
				}
				else if((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("Checked")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null))
				{
					System.out.println("Successfully verified the value as checked for the checkbox field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Successfully verified the value as checked for the checkbox field ("+fieldname+")", "Pass");
					return true;
				}
				else
				{
					System.out.println("Could not find the value as checked for the checkbox field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Could not find the value as checked for the checkbox field ("+fieldname+")", "Fail");
					return false;
				}
			}
			if (CheckedORNotChecked.equalsIgnoreCase("Not Checked"))
			{
				if ((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("Not Checked")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null))
				{
					System.out.println("Successfully verified the value as unchecked for the checkbox field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Successfully verified the value as unchecked for the checkbox field ("+fieldname+")", "Pass");
					return true;
				}
				else if((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null))
				{
					System.out.println("Successfully verified the value as checked for the checkbox field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Successfully verified the value as checked for the checkbox field ("+fieldname+")", "Pass");
					return true;
				}
				else
				{
					System.out.println("Could not find the value as unchecked for the checkbox field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Could not find the value as unchecked for the checkbox field ("+fieldname+")", "Fail");
					return false;
				}
				
			}
			else
			{
				System.out.println("The input parameter value for the function VerifyChkBoxValue is not expected: "+CheckedORNotChecked);
				SFDCAutomationFW.AddToXLLogs("The input parameter value for the function VerifyChkBoxValue is not expected: "+CheckedORNotChecked, "Fail");
				return false;
			}
			
		}
		else
		{
			System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
			return false;
		}
	}
	catch(Exception e)
	{
		System.out.println("Unable to find the element when xpath is: "+xpath);
		SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
		return false;
	}
}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @Description Checks the checkbox field in Edit page 
	 * @return boolean
	 * @throws Exception
	 
	 */
	public boolean CheckBoxSelect() throws Exception
	{
	
		xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='img' or local-name()='input')][1]";
	try
	{
		if(SFDCAutomationFW.WaitForElement(xpath,10))
		{			
			
			if ((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("") || myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("Not Checked")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null))
			{
				myWD.get().findElement(By.xpath(xpath)).click();
				System.out.println("Successfully checked the checkbox for the field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully checked the checkbox for the field ("+fieldname+")", "Pass");
				return true;
			}
			else if(((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("Checked")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null)) || ((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")!=null)))
			{
				System.out.println("Successfully checked the checkbox for the field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully checked the checkbox for the field ("+fieldname+")", "Pass");
				return true;
			}
			else
			{
				System.out.println("Unable to check the check box for field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Unable to check the check box for field ("+fieldname+")", "Fail");
				return false;
			}
		}
		else
		{
			System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
			return false;
		}
	}
	catch(Exception e)
	{
		System.out.println("Unable to find the element when xpath is: "+xpath);
		SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
		return false;
	}
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @Description Checks the checkbox field in Edit page in case checkbox element displays before the field label.
	 * @return boolean
	 * @throws Exception
	 */
	public boolean PrecedingCheckBoxSelect() throws Exception
	{
		
		try
		{
			
		if (myWD.get().findElement(By.xpath("//label[contains(normalize-space(text()),'"+fieldname+"')]/preceding-sibling::input[1]")).getAttribute("checked") == null)
		{
			myWD.get().findElement(By.xpath("//label[contains(normalize-space(text()),'"+fieldname+"')]/preceding-sibling::input[1]")).click();
			System.out.println("Successfully checked the checkbox for the field ("+fieldname+")");
			SFDCAutomationFW.AddToXLLogs("Successfully checked the checkbox for the field ("+fieldname+")", "Pass");
			return true;
			
		}
		else
		{
			
			System.out.println("Successfully checked the checkbox for the field ("+fieldname+")");
			SFDCAutomationFW.AddToXLLogs("Successfully checked the checkbox for the field ("+fieldname+")", "Pass");
			return true;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to check the check box for field ("+fieldname+") ");
			SFDCAutomationFW.AddToXLLogs("Unable to check the check box for field ("+fieldname+")", "Fail");
			return false;
		}
	}
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @Description UnChecks the checkbox field in Edit page in case checkbox element displays before the field label.
	 * @return boolean
	 * @throws Exception
	 */
	public boolean PrecedingCheckBoxDeSelect() throws Exception
	{
		try{
			if (myWD.get().findElement(By.xpath("//label[contains(normalize-space(text()),'"+fieldname+"')]/preceding-sibling::input[1]")).getAttribute("checked") != null)
			{
				myWD.get().findElement(By.xpath("//label[contains(normalize-space(text()),'"+fieldname+"')]/preceding-sibling::input[1]")).click();
				System.out.println("Successfully unchecked the checkbox for the field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully unchecked the checkbox for the field ("+fieldname+")", "Pass");
				return true;
				
			}
			else
			{
				System.out.println("Successfully unchecked the checkbox for the field ("+fieldname+")");
				SFDCAutomationFW.AddToXLLogs("Successfully unchecked the checkbox for the field ("+fieldname+")", "Pass");
				return true;
			}
			}
			catch(Exception e)
			{
				System.out.println("Unable to uncheck the check box for field ("+fieldname+") when xpath is:(//label[contains(normalize-space(text()),'"+fieldname+"')]/preceding-sibling::input[1])");
				SFDCAutomationFW.AddToXLLogs("Unable to uncheck the check box for field ("+fieldname+")", "Fail");
				return false;
			}
	}
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @Description UnChecks the checkbox field in Edit page 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean CheckBoxDeSelect() throws Exception
	{
		xpath="//*[text()='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::*[(local-name()='img' or local-name()='input')][1]";
		try
		{	
			if(SFDCAutomationFW.WaitForElement(xpath,10))
			{	
				if(((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("Checked")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null)) || ((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")!=null)))
				{
					myWD.get().findElement(By.xpath(xpath)).click();
					System.out.println("Successfully unchecked the checkbox for the field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Successfully unchecked the checkbox for the field ("+fieldname+")", "Pass");
					return true;
				}
				else if ((myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("") || myWD.get().findElement(By.xpath(xpath)).getAttribute("title").equals("Not Checked")) && (myWD.get().findElement(By.xpath(xpath)).getAttribute("checked")==null))
				{
					System.out.println("Successfully unchecked the checkbox for the field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Successfully unchecked the checkbox for the field ("+fieldname+")", "Pass");
					return true;
				}
				else
				{
					System.out.println("Unable to uncheck the check box for field ("+fieldname+")");
					SFDCAutomationFW.AddToXLLogs("Unable to uncheck the check box for field ("+fieldname+")", "Fail");
					return false;
				}
			}
			else
			{
				System.out.println("Unable to find the element after waiting for specified time when xpath is:"+xpath);
				SFDCAutomationFW.AddToXLLogs("Unable to find the element after waiting for specified time when xpath is:"+xpath, "Fail");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to find the element when xpath is: "+xpath);
			SFDCAutomationFW.AddToXLLogs("Unable to find the element when xpath is:"+xpath,"Fail");
			return false;
		}
	}
	
	//Choose value from lookup
	
	/**
	 * @author Sourav
	 * @PageDisplayMode Edit Page
	 * @param LookUpValue
	 * @Description Selects the value from SFDC OOB lookup field. If the text box field is editable then it types the value in that field then clicks on the lookup icon and clicks on the hyperlink displayed in search lookup. In case the text box field is read only then directly clicks on the lookup icon and searches the expected value in the search lookup window and clicks on hyperlink. It also sets the focus to the parent window 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean SelectFromLookup(String LookUpValue) throws Exception
	{
		//xpath = "((//*[normalize-space(text())='"+fieldname+"'])[1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/input[@type !='hidden'][1]";
		String parentwindowtitle = "";
		xpath = "((//*[normalize-space(text())='"+fieldname+"'])/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/input[1]";
		
		
		try
		{
			//if (myWD.toString().contains("FirefoxDriver"))
			//if (myWD.toString().contains("ChromeDriver"))
			//{

				String mainwindow = myWD.get().getWindowHandle();    
				getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
								
				System.out.println("getsingleWebelement.getAttribute: "+getsingleWebelement.getAttribute("readonly"));
				if (getsingleWebelement.getAttribute("readonly") == null)
				{
					
					getsingleWebelement.clear();
					getsingleWebelement.sendKeys(LookUpValue);
					SFDCAutomationFW.PressTABKeyOnWindowAlert();
					Thread.sleep(6000L);
					xpath = "((//*[normalize-space(text())='"+fieldname+"'])/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/descendant-or-self::img[contains(normalize-space(@alt),'New Window')]";			
					getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
					getsingleWebelement.click();
					Thread.sleep(5000L);
					
					Set<String> s = myWD.get().getWindowHandles(); 
			        Iterator<String> ite = s.iterator(); 
			        while(ite.hasNext()) 
			        { 
			            String popup = ite.next(); 
			            if(!popup.equalsIgnoreCase(mainwindow)){ 
			                myWD.get().switchTo().window(popup); 
			                System.out.println("Window Title is: "+myWD.get().getTitle());
			                break;
			              }
			        }
			        
			        Thread.sleep(3000L);
					//SFDCAutomationFW.SelectWindow("Search");
					myWD.get().switchTo().defaultContent();
					System.out.println("Currectly focused window is:"+myWD.get().getTitle());
					myWD.get().switchTo().frame(myWD.get().findElement(By.xpath("//frame[contains(normalize-space(@title),'Result')]")));
					System.out.println("Switching to frame works for Search Result");
					Thread.sleep(3000L);
					myWD.get().findElement(By.xpath("//a[normalize-space(text())='"+LookUpValue+"'][1]")).click();
					SFDCAutomationFW.AddToXLLogs("Successfully picked the value ("+LookUpValue+") from lookup field ("+fieldname+")", "Pass");
					System.out.println("Successfully picked the value ("+LookUpValue+") from lookup field ("+fieldname+")");
					//SFDCAutomationFW.SelectWindow(mainwindow);
					myWD.get().switchTo().window(mainwindow); 
					myWD.get().switchTo().defaultContent();
					return true;
							        
				}
				
				//Below code works if the field is read only
				//else if (getsingleWebelement.getAttribute("readonly").equalsIgnoreCase("true") || (getsingleWebelement.getAttribute("readonly").equalsIgnoreCase("readonly"))) 
				else
				{
					mainwindow = myWD.get().getWindowHandle();    
					xpath = "((//*[normalize-space(text())='"+fieldname+"'])/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::span[contains(normalize-space(@class),'lookup')]/descendant-or-self::img[contains(normalize-space(@alt),'New Window')]";			
					getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
					getsingleWebelement.click();
					Thread.sleep(2000L);
					//SFDCAutomationFW.SelectWindow("Search");
					Set<String> s = myWD.get().getWindowHandles(); 
			        Iterator<String> ite = s.iterator(); 
			        while(ite.hasNext()) 
			        { 
			            String popup = ite.next(); 
			            if(!popup.equalsIgnoreCase(mainwindow)){ 
			                myWD.get().switchTo().window(popup); 
			                System.out.println("Window Title is: "+myWD.get().getTitle());
			                break;
			              }
			        }
					myWD.get().switchTo().frame(myWD.get().findElement(By.xpath("//frame[contains((@title),'Search')]")));
					System.out.println("Switching to Frame Works properly for Search box.");
					myWD.get().findElement(By.xpath("//input[contains(normalize-space(@placeholder),'Search') and normalize-space(@type)='text']")).clear();
					myWD.get().findElement(By.xpath("//input[contains(normalize-space(@placeholder),'Search') and normalize-space(@type)='text']")).sendKeys(LookUpValue);
					Thread.sleep(1000L);
					myWD.get().findElement(By.xpath("//input[contains(normalize-space(@placeholder),'Search') and normalize-space(@type)='text']")).sendKeys(Keys.ENTER);
				
					myWD.get().switchTo().defaultContent();
					System.out.println("Currectly focused window is:"+myWD.get().getTitle());
					myWD.get().switchTo().frame(myWD.get().findElement(By.xpath("//frame[contains(normalize-space(@title),'Result')]")));
					System.out.println("Switching to frame works for Search Result");
					Thread.sleep(3000L);
					if (myWD.get().findElement(By.linkText(LookUpValue)).isDisplayed())
					{
						myWD.get().findElement(By.linkText(LookUpValue)).click();
						SFDCAutomationFW.AddToXLLogs("Successfully picked the value ("+LookUpValue+") from lookup field ("+fieldname+")", "Pass");
						System.out.println("Successfully picked the value ("+LookUpValue+") from lookup field ("+fieldname+")");
						//SFDCAutomationFW.SelectWindow(mainwindow);
						myWD.get().switchTo().window(mainwindow); 
						myWD.get().switchTo().defaultContent();
						return true;
					}
					else
					{
						
						SFDCAutomationFW.AddToXLLogs("Unable to find any such record ("+LookUpValue+") from the lookup field ("+fieldname+")", "Fail");
						System.out.println("Unable to find any such record ("+LookUpValue+") from the lookup field ("+fieldname+")");
						SFDCAutomationFW.CloseWindow("Search");
						myWD.get().switchTo().window(mainwindow); 
						myWD.get().switchTo().defaultContent();
						return false;
					}
					
					
				}
				
		}
		catch(Exception e)
		{
			
			SFDCAutomationFW.AddToXLLogs("Unable to pick the supplied value("+LookUpValue+") from lookup field ("+fieldname+") when xpath is ("+xpath+")", "Fail");
			System.out.println("Exception: Unable to pick the supplied value("+LookUpValue+") from lookup field ("+fieldname+") when xpath is ("+xpath+")");
			e.printStackTrace();
			SFDCAutomationFW.CloseWindow("Search");
			SFDCAutomationFW.SelectWindow(parentwindowtitle);
			myWD.get().switchTo().defaultContent();
			return false;
		}
		
	}
	
	
	
	
	
	/**
	 * @author Sourav
	 * @Description Waits for specified time for the field to be displayed in the UI
	 * @param waitingTimeinSec
	 * @return boolean
	 * @throws Exception
	 */
	public boolean WaitForElement(long waitingTimeinSec) throws Exception
	{
		xpath = "//*[normalize-space(text())='"+fieldname+"']";
		 try {
			 
			 if (myWD.toString().contains("InternetExplorerDriver"))
     	 	 {
     	 			SFDCAutomationFW.WebDriverWaitForElement(xpath,waitingTimeinSec);
     	 			return true;
     	 	 }
			 else
			 {
             myWD.get().manage().timeouts().implicitlyWait(waitingTimeinSec,TimeUnit.SECONDS);
             List<WebElement> myDynamicElement = myWD.get().findElements(By.xpath(xpath));
             if (myDynamicElement.size() > 0)
             {
            	 SFDCAutomationFW.AddToXLLogs("The field ("+fieldname+") was available in the application.", "Pass");
            	 return true;
             }
             else
             {
            	 SFDCAutomationFW.AddToXLLogs("Could not find the field ("+fieldname+") in the application.", "Fail");
            	 return false;
             }
			 }
             //System.out.println("The value of dynamic webelement is:"+myDynamicElement.isDisplayed());
             //return myDynamicElement.isDisplayed();
             }
         catch(NoSuchElementException e)
             {
             System.out.println("Could not find the element after waiting for specified time.");
             SFDCAutomationFW.AddToXLLogs("Could not find the button ("+fieldname+") in the application.", "Fail");
             return false;
             //return false;
             }
	}
	/**
	 * @author Sourav
	 * @param LinkPartialText
	 * @return boolean
	 * @Description Clicks on partial link displayed anywhere in the UI
	 * @throws Exception
	 */
	public boolean LinkClick(String LinkPartialText) throws Exception
	{
		//xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::a[contains(normalize-space(text()),'"+LinkPartialText.trim()+"')][1]";
		  xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::a[contains(normalize-space(text()),'"+LinkPartialText.trim()+"')][1]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			SFDCAutomationFW.HighLight(getsingleWebelement);
			getsingleWebelement.click();
			SFDCAutomationFW.AddToXLLogs("Clicked on the link ("+LinkPartialText+") for the field ("+fieldname+").", "Pass");
			System.out.println("Clicked on the link ("+LinkPartialText+") for the field ("+fieldname+").");
			return true;
		}
	    else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the link ("+LinkPartialText+") for the field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the link ("+LinkPartialText+") for the field ("+fieldname+").");
			return false;
		}
		}
		catch(WebDriverException e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the link ("+LinkPartialText+") for the field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the link ("+LinkPartialText+") for the field ("+fieldname+") when xpath is:"+xpath);
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * @return
	 * @throws Exception
	 * @Description Clicks on the first link present against the field on Edit page
	 */
	public boolean LinkClick_EditPage() throws Exception
	{
		//xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th'][1])/following-sibling::*[local-name()='td' or local-name()='th'][1]/descendant-or-self::a[contains(normalize-space(text()),'"+LinkPartialText.trim()+"')][1]";
		  xpath = "(//*[normalize-space(text())='"+fieldname+"'][1]/ancestor-or-self::*[local-name()='td' or local-name()='th' or local-name()='div'][1])/following-sibling::*[local-name()='td' or local-name()='th' or local-name()='div'][1]/descendant-or-self::a[1][1]";
		try
		{
		if (SFDCAutomationFW.WaitForElement(xpath,10))
		{
			getsingleWebelement = myWD.get().findElement(By.xpath(xpath));
			SFDCAutomationFW.HighLight(getsingleWebelement);
			getsingleWebelement.click();
			SFDCAutomationFW.AddToXLLogs("Clicked on the link against the field ("+fieldname+").", "Pass");
			System.out.println("Clicked on the link against the field ("+fieldname+").");
			return true;
		}
	    else
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the link against the field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the link against the field ("+fieldname+").");
			return false;
		}
		}
		catch(WebDriverException e)
		{
			SFDCAutomationFW.AddToXLLogs("Unable to click on the link against the field ("+fieldname+").", "Fail");
			System.out.println("Unable to click on the link against the field ("+fieldname+") when xpath is:"+xpath);
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @author Cognizant
	 * @param YesORNo
	 * @Description Verify if a field label is present in the View Details page
	 * @return
	 * @throws Exception
	 */
	public boolean IsDisplayed(String YesORNo) throws Exception
    {
           xpath = "//*[normalize-space(text())='"+fieldname+"']";
           
           //System.out.println();
           
           if (YesORNo.equalsIgnoreCase("Yes"))
           {
                  try
                  {
                  if((myWD.get().findElement(By.xpath(xpath)).isDisplayed()))
                  {
                        System.out.println("Successfully verified the existence of the field ("+fieldname+").");
                        SFDCAutomationFW.AddToXLLogs("Successfully verified the existence of the field ("+fieldname+").", "Pass");
                        return true;  
                  }
                  else
                  {
                        System.out.println("Could not find the field ("+fieldname+") in the application.");
                        SFDCAutomationFW.AddToXLLogs("Could not find the field ("+fieldname+") in the application.", "Fail");
                        return false;
                  }
                  }catch(Exception e)
                  {
                        System.out.println("Could not find the field ("+fieldname+") in the application when xpath is:"+xpath);
                        SFDCAutomationFW.AddToXLLogs("Could not find the field ("+fieldname+") in the application when xpath is:"+xpath, "Fail");
                        e.printStackTrace();
                        return false;
                  }
           }
           else if (YesORNo.equalsIgnoreCase("No"))
           {
                  try
                  {
                  if((myWD.get().findElement(By.xpath(xpath)).isDisplayed()))
                  {
                        System.out.println("The field ("+fieldname+") is present in the UI when it is not expected to be.");
                        SFDCAutomationFW.AddToXLLogs("The field ("+fieldname+") is present in the UI when it is not expected to be.", "Fail");
                        return false;
                  }
                  else
                  {
                        System.out.println("Successfully verified that the field ("+fieldname+") is not present in the UI.");
                        SFDCAutomationFW.AddToXLLogs("Successfully verified that the field ("+fieldname+") is not present in the UI.", "Pass");
                        return true;
                  }
                  }catch(Exception e2)
                  {
                        System.out.println("Successfully verified that the field ("+fieldname+") is not present in the UI.");
                        SFDCAutomationFW.AddToXLLogs("Successfully verified that the field ("+fieldname+") is not present in the UI.", "Pass");
                        return true;
                  }
           }
           else
           {
                  System.out.println("The input parameter supplied in IsDisplayed() function is not correct. It should be either Yes or No");
                  SFDCAutomationFW.AddToXLLogs("The input parameter supplied in IsDisplayed() function is not correct. It should be either Yes or No", "Fail");
                  return false;
           }
    }

}
