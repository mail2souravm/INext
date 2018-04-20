package USER_SPACE.ObjectRepository;

import SOURCE_CODE.MemberOfButton;
import SOURCE_CODE.MemberOfField;
import SOURCE_CODE.SFDCAutomationFW;

public class LeadScreen {

	

static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField LeadOwnerField() throws Exception{ 
	return SFDCAutomationFW.Field("Lead Owner"); 
} 
public static MemberOfField PhoneField() throws Exception{ 
	return SFDCAutomationFW.Field("Phone"); 
} 
public static MemberOfField NameField() throws Exception{ 
	return SFDCAutomationFW.Field("Name"); 
} 
public static MemberOfField MobileField() throws Exception{ 
	return SFDCAutomationFW.Field("Mobile"); 
} 
public static MemberOfField CompanyField() throws Exception{ 
	return SFDCAutomationFW.Field("Company"); 
} 
public static MemberOfField FaxField() throws Exception{ 
	return SFDCAutomationFW.Field("Fax"); 
} 
public static MemberOfField TitleField() throws Exception{ 
	return SFDCAutomationFW.Field("Title"); 
} 
public static MemberOfField EmailField() throws Exception{ 
	return SFDCAutomationFW.Field("Email"); 
} 
public static MemberOfField LeadSourceField() throws Exception{ 
	return SFDCAutomationFW.Field("Lead Source"); 
} 
public static MemberOfField WebsiteField() throws Exception{ 
	return SFDCAutomationFW.Field("Website"); 
} 
public static MemberOfField IndustryField() throws Exception{ 
	return SFDCAutomationFW.Field("Industry"); 
} 
public static MemberOfField LeadStatusField() throws Exception{ 
	return SFDCAutomationFW.Field("Lead Status"); 
} 
public static MemberOfField CampaignField() throws Exception{ 
	return SFDCAutomationFW.Field("Campaign"); 
} 
public static MemberOfField AnnualRevenueField() throws Exception{ 
	return SFDCAutomationFW.Field("Annual Revenue"); 
} 
public static MemberOfField RatingField() throws Exception{ 
	return SFDCAutomationFW.Field("Rating"); 
} 
public static MemberOfField NoofEmployeesField() throws Exception{ 
	return SFDCAutomationFW.Field("No. of Employees"); 
} 
public static MemberOfField AddressField() throws Exception{ 
	return SFDCAutomationFW.Field("Address"); 
} 
public static MemberOfField ProductInterestField() throws Exception{ 
	return SFDCAutomationFW.Field("Product Interest"); 
} 
public static MemberOfField CurrentGeneratorsField() throws Exception{ 
	return SFDCAutomationFW.Field("Current Generator(s)"); 
} 
public static MemberOfField SICCodeField() throws Exception{ 
	return SFDCAutomationFW.Field("SIC Code"); 
} 
public static MemberOfField PrimaryField() throws Exception{ 
	return SFDCAutomationFW.Field("Primary"); 
} 
public static MemberOfField NumberofLocationsField() throws Exception{ 
	return SFDCAutomationFW.Field("Number of Locations"); 
} 
public static MemberOfField CreatedByField() throws Exception{ 
	return SFDCAutomationFW.Field("Created By"); 
} 
public static MemberOfField LastModifiedByField() throws Exception{ 
	return SFDCAutomationFW.Field("Last Modified By"); 
} 
public static MemberOfField DescriptionField() throws Exception{ 
	return SFDCAutomationFW.Field("Description"); 
} 
public static MemberOfField FirstNameField() throws Exception{ 
	return SFDCAutomationFW.Field("First Name"); 
} 
public static MemberOfField LastNameField() throws Exception{ 
	return SFDCAutomationFW.Field("Last Name"); 
} 
public static MemberOfField StateProvinceField() throws Exception{ 
	return SFDCAutomationFW.Field("State/Province"); 
} 
public static MemberOfField CityField() throws Exception{ 
	return SFDCAutomationFW.Field("City"); 
} 
public static MemberOfField CountryField() throws Exception{ 
	return SFDCAutomationFW.Field("Country"); 
} 
public static MemberOfField ZipPostalCodeField() throws Exception{ 
	return SFDCAutomationFW.Field("Zip/Postal Code"); 
} 
 


public static MemberOfButton EditButton() throws Exception{ 
return SFDCAutomationFW.Button("Edit"); 
} 


public static MemberOfButton AddtoCampaignButton() throws Exception{ 
return SFDCAutomationFW.Button("Add to Campaign"); 
} 
public static MemberOfButton FindDuplicatesButton() throws Exception{ 
return SFDCAutomationFW.Button("Find Duplicates"); 
} 
public static MemberOfButton NewTaskButton() throws Exception{ 
return SFDCAutomationFW.Button("New"); 
} 
public static MemberOfButton MailMergeButton() throws Exception{ 
return SFDCAutomationFW.Button("Mail Merge"); 
} 
public static MemberOfButton NewMeetingRequestButton() throws Exception{ 
return SFDCAutomationFW.Button("New Meeting Request"); 
} 
public static MemberOfButton OKButton() throws Exception{ 
return SFDCAutomationFW.Button("OK"); 
} 
public static MemberOfButton ConvertButton() throws Exception{ 
return SFDCAutomationFW.Button("Convert"); 
} 
public static MemberOfButton CancelButton() throws Exception{ 
return SFDCAutomationFW.Button("Cancel"); 
} 
public static MemberOfButton GoButton() throws Exception{ 
return SFDCAutomationFW.Button("Go!"); 
} 
public static MemberOfButton LogaCallButton() throws Exception{ 
return SFDCAutomationFW.Button("Log a Call"); 
} 
public static MemberOfButton CloneButton() throws Exception{ 
return SFDCAutomationFW.Button("Clone"); 
} 
public static MemberOfButton SendanEmailButton() throws Exception{ 
return SFDCAutomationFW.Button("Send an Email"); 
} 
public static MemberOfButton DeleteButton() throws Exception{ 
return SFDCAutomationFW.Button("Delete"); 
} 
public static MemberOfButton NewEventButton() throws Exception{ 
return SFDCAutomationFW.Button("New Event"); 
} 
public static MemberOfButton SaveButton() throws Exception{ 
return SFDCAutomationFW.Button("Save"); 
} 
public static MemberOfButton NewButton() throws Exception{ 
return SFDCAutomationFW.Button("New"); 
} 

}
