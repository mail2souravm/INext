package USER_SPACE.ObjectRepository;

import SOURCE_CODE.MemberOfButton;
import SOURCE_CODE.MemberOfField;
import SOURCE_CODE.SFDCAutomationFW;

public class AccountScreen {

	

static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField AccountNameField() throws Exception{ 
	return SFDCAutomationFW.Field("Account Name"); 
} 

}
