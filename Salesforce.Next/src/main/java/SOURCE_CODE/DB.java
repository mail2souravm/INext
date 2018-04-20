package SOURCE_CODE;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.serial.SerialBlob;

//import org.apache.poi.hslf.util.SystemTimeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DB {
	
	//public static Connection con;
	public static Workbook wb;
	public static String workbook_path = null;
	public synchronized static Workbook Connect(String ExcelFileName) throws Exception{
		
		try{
			//String myCurrentDir = System.getProperty("user.dir");
			//System.out.println("myCurrentDir:"+myCurrentDir);
			
			File file = new File(ExcelFileName);
			String absolutePath = file.getAbsolutePath();
			//System.out.println("absolutePath:"+absolutePath);
			workbook_path = ExcelFileName;
			//System.out.println("path of ExcelFileName:"+absolutePath);
			if(absolutePath.endsWith(".xlsx"))
			{
				wb = new XSSFWorkbook(new FileInputStream(absolutePath));
			}
			else if(absolutePath.endsWith(".xls"))
			{
				wb = new HSSFWorkbook(new FileInputStream(absolutePath));
			}
			else
			{
				System.out.println("Not a valid excel file.");
			}
			return wb;
		}
		catch(Exception e)
		{
			System.out.println("Unable to make excel connection inside DB.Connect");
			e.printStackTrace();
			return null;
		}		
	}
	
	
	
	public static String ReadXLData(String SheetName,String ColumnNameTobeRead,String KeyColumnName,String KeyColumnValue) throws Exception
	{
			
			try{
				//System.out.println("SheetName:"+SheetName);
				//System.out.println("ColumnNameTobeRead:"+ColumnNameTobeRead);
				//System.out.println("KeyColumnName:"+KeyColumnName);
				//System.out.println("KeyColumnValue:"+KeyColumnValue);
				
				String value = "";
				Sheet sh = wb.getSheet(SheetName);
				int cnt_row = sh.getLastRowNum() + 1;
				int cnt_col = sh.getRow(0).getLastCellNum();
				
				int ind_kc = 0;
				int ind_kv = 0;
				int ind_col_toget = 0;
				
				//System.out.println("Number of columns:"+cnt_col);
				//System.out.println("Number of row:"+cnt_row);
				for(int i=0;i<cnt_col;i++)
				{
					Cell cell = sh.getRow(0).getCell(i);
					
					if(_cellToString(cell).trim().equals(KeyColumnName))
					{
						ind_kc = i;
						
					}
					if(_cellToString(cell).trim().equals(ColumnNameTobeRead))
					{
						ind_col_toget = i;
						
					}
					
				}
				for(int i=1;i<cnt_row;i++)
				{
					Cell cell = sh.getRow(i).getCell(ind_kc);
					if(_cellToString(cell).trim().equals(KeyColumnValue))
					{
						//ind_col_toget = i;
						Cell cell1 = sh.getRow(i).getCell(ind_col_toget);
						value = _cellToString(cell1).trim();
						break;
					}
				}
				//System.out.println("value:"+value);
				return value;
			}catch(Exception e)
			{
				System.out.println("Unable to read the value from excel.");
				e.printStackTrace();
				return null;
			}
	}
	public static ArrayList<String> getAllExcelColumns(String workbook_path,String worksheet)
	{
		try{
			String value = "";
			Workbook wb = null;
			ArrayList<String> al_api_sheet_columns = new ArrayList<String>();
			FileInputStream fis = new FileInputStream(workbook_path);
			//System.out.println("workbook_path:"+workbook_path);
			//System.out.println("worksheet:"+worksheet);
			if(workbook_path.endsWith(".xlsx"))
			{
				wb = new XSSFWorkbook(fis);
			}
			else if(workbook_path.endsWith(".xls"))
			{
				wb = new HSSFWorkbook(fis);
			}
			else
			{
				System.out.println("Not a valid excel file.");
			}
				
			Sheet sh = wb.getSheet(worksheet);
			int col_cnt = sh.getRow(0).getLastCellNum();
			
			Row row = sh.getRow(0);
			for(int i=0;i<col_cnt;i++)
			{
				Cell cell = row.getCell(i);
				
				value = cell.getStringCellValue();
				al_api_sheet_columns.add(value.trim());
				//System.out.println("column name:"+value);
			}

		return al_api_sheet_columns;	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void updateXLCell(String workbook_path,String worksheet,String keycolumn, String keyvalue, String targetcolumn, String value)
    {
		try
		{
			Workbook wb = null;
			
			FileInputStream fsIP= new FileInputStream(new File(workbook_path)); //Read the spreadsheet that needs to be updated
			if(workbook_path.endsWith(".xlsx"))
			{
				wb = new XSSFWorkbook(fsIP); //Access the workbook
			}
			else if(workbook_path.endsWith(".xls"))
			{
				wb = new HSSFWorkbook(fsIP);
			}
			else
			{
				System.out.println("Not a valid excel file.");
			}
			
		      
		    Sheet wsheet = wb.getSheet(worksheet); //Access the worksheet, so that we can update / modify it.
		     
		    int col_cnt = wsheet.getRow(0).getLastCellNum();
		    int row_cnt = wsheet.getLastRowNum();
		    
		    int indx_key_col=0, indx_key_val=0, indx_tar_col = 0;
		    
		    //System.out.println("last row num: "+wsheet.getLastRowNum());
		    //System.out.println("last column num: "+wsheet.getRow(0).getLastCellNum());
		    // 0 to 26 col
		    Cell cell = null; 
		    for(int i=0;i<col_cnt;i++)
		    {
		    	 cell = wsheet.getRow(0).getCell(i);
		    	 if (cell.getStringCellValue().equals(keycolumn))
		    	 {
		    		 indx_key_col = i; 
		    	 }
		    	 if (cell.getStringCellValue().equals(targetcolumn))
		    	 {
		    		 indx_tar_col = i; 
		    	 }
		    }
		    //System.out.println("indx_key_col:"+indx_key_col);
		   // System.out.println("indx_tar_col:"+indx_tar_col);
		    for(int i=1;i<=row_cnt;i++)
		    {
		    	 cell = wsheet.getRow(i).getCell(0);
		    	 //System.out.println("cell.getStringCellValue():"+cell.getStringCellValue());
		    	 if (cell.getStringCellValue().equals(keyvalue))
		    	 {
		    		 indx_key_val = i; 
		    		 break;
		    	 }
		    }
		    
		    //System.out.println("indx_key_val:"+indx_key_val);
		   // HSSFRow row = wsheet.getRow(indx_key_val);
		    Cell cell1;
		    cell1 = wsheet.getRow(indx_key_val).getCell(indx_tar_col);
		    //System.out.println(indx_key_val +" "+indx_tar_col+"value: "+value);
		    //cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		    cell1.setCellValue(value); 
		    
		    fsIP.close(); //Close the InputStream
		     
		    FileOutputStream output_file =new FileOutputStream(new File(workbook_path));  //Open FileOutputStream to write updates
		      
		    wb.write(output_file); //write changes
		      
		    output_file.close();  //close the stream    
		    		
		    	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
   	public static String[][] GetAllExcelValues(String SheetName) throws Exception
	{			
		try {
			String value = "";
			Sheet sh = wb.getSheet(SheetName);
			int cnt_row = sh.getLastRowNum() + 1;
			int cnt_col = sh.getRow(0).getLastCellNum();
			
			int ind_kc = 0;
			int ind_kv = 0;
			int ind_col_toget = 0;
			
			//System.out.println("Number of columns:"+cnt_col);
			//System.out.println("Number of row:"+cnt_row);
			String XL_DATA[][] = new String[cnt_row][cnt_col];
			
			for(int i=0;i<cnt_row;i++)
			{
				
				
				for(int j=0;j<cnt_col;j++)
				{
					Row r = sh.getRow(i);
					Cell c = r.getCell(j);
					
					//System.out.println("i:"+i+" j:"+j);
					//System.out.println("c.getStringCellValue().trim();:"+_cellToString(c));
					XL_DATA[i][j] = _cellToString(c);
				}	
				
			}
			
			return XL_DATA;
			
		}catch(Exception e)
		{
			System.out.println("Unable to read the data from excel.");
			e.printStackTrace();
			return null;
		}
		
	}    
	
   	//Possibly this was created for Script less, please check again and convert accordingly to POI from JDBC reoval 
   	public static String ReadXLData_FirstMatch_OneRecord_SeperatedBySemiColon(String SheetName,String KeyColumnName,String KeyColumnValue) throws Exception
	{
			Connection con=null;
	
	    	 int index=1;
	    	 try{
			 Statement st = con.createStatement();
			 ResultSet rs= st.executeQuery("select * from ["+SheetName+"$] where "+KeyColumnName+"='"+KeyColumnValue+"'");
			 while(index>0){
				 rs.next();
				 index--;
			 }
			 
			
			 ResultSetMetaData rsmd = rs.getMetaData();
			 //System.out.println("columns: "+rsmd.getColumnCount());  
			 String Value = "";
			 for(int i=1;i<=rsmd.getColumnCount();i++)
			 {
				 Value = Value + rs.getString(i)+";";
			 }
			 //rs.close();rs=null;
			 //st.close(); st=null;
			 //con.close();con=null;
			 //System.out.println(Value);
			 return Value==null?"":Value;
	    	}
	    	catch(Exception e)
	    	{
	    		//e.printStackTrace();
	    		System.out.println("There is no such data in excel");
	    		return null;
	    	}
			 
	}
   	/*
	public static ArrayList<String> GetAllExcelColumns(String SheetName) throws Exception
	{			
		/*
		try{
			ArrayList<String> al_xl_column_name = new ArrayList<String>();
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("select * from ["+SheetName+"$]");
			ResultSetMetaData rsmd = rs.getMetaData();
			
			System.out.println("columnsnames: "+rsmd.getColumnLabel(1));  
			 for(int i=1;i<=rsmd.getColumnCount();i++)
			 {
				 al_xl_column_name.add(rsmd.getColumnLabel(i).toString());
			 }
			 
			 for(int i=0;i<al_xl_column_name.size();i++)
			 {
				 System.out.println("Column Names are:"+al_xl_column_name.get(i));
			 }
			 return al_xl_column_name;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}  	
		
		return null;
		
	}    */


   	public static void removeSheets(String sheetName)
   	{
   		try{
   			for(int i=wb.getNumberOfSheets()-1;i>=0;i--){
   	            Sheet tmpSheet = wb.getSheetAt(i);
   	            if(tmpSheet.getSheetName().equals(sheetName)){
   	                wb.removeSheetAt(i);
   	            }
   	        }	   			
   			
   			
   			FileOutputStream output = new FileOutputStream(workbook_path);
   			wb.write(output);
   			output.close();
   		}
        catch(Exception e)
   		{
        	e.printStackTrace();
   		}
   	}
   	
	public static void copySheet(String sheetName)
   	{
   		try{
   			for(int i=wb.getNumberOfSheets()-1;i>=0;i--){
   	            Sheet tmpSheet = wb.getSheetAt(i);
   	            if(tmpSheet.getSheetName().equals(sheetName)){
   	                Sheet newsheet = wb.cloneSheet(i);
   	                //break;
   	                
   	            }
   	        }	   			
   			
   			wb.setSheetName(wb.getSheetIndex("Exe_Log_Tmp (2)"), "Exe_Log");
   			FileOutputStream output = new FileOutputStream(workbook_path);
   			wb.write(output);
   			output.close();
   		}
        catch(Exception e)
   		{
        	e.printStackTrace();
   		}
   	}
   	
   	
	public static void UpdateXLCell(String SheetName, String NewValue, String ColumnNameTobeUpdated,String KeyColumnName,String KeyColumnValue) throws Exception
	{
		try
		{
			Workbook wb = null;
			 
			
			
			
			FileInputStream fsIP= new FileInputStream(new File(workbook_path)); //Read the spreadsheet that needs to be updated
			if(workbook_path.endsWith(".xlsx"))
			{
				wb = new XSSFWorkbook(fsIP);
			}
			else if(workbook_path.endsWith(".xls"))
			{
				wb = new HSSFWorkbook(fsIP);
			}
			else
			{
				System.out.println("Not a valid excel file.");
			}
			
		      
		    Sheet wsheet = wb.getSheet(SheetName); //Access the worksheet, so that we can update / modify it.
		     
		    int col_cnt = wsheet.getRow(0).getLastCellNum();
		    int row_cnt = wsheet.getLastRowNum();
		    
		    int indx_key_col=0, indx_key_val=0, indx_tar_col = 0;
		    
		    //System.out.println("last row num: "+wsheet.getLastRowNum());
		   //System.out.println("last column num: "+wsheet.getRow(0).getLastCellNum());
		    // 0 to 26 col
		    Cell cell = null; 
		    for(int i=0;i<col_cnt;i++)
		    {
		    	 cell = wsheet.getRow(0).getCell(i);
		    	 if (cell.getStringCellValue().equals(KeyColumnName))
		    	 {
		    		 indx_key_col = i; 
		    	 }
		    	 if (cell.getStringCellValue().equals(ColumnNameTobeUpdated))
		    	 {
		    		 indx_tar_col = i; 
		    	 }
		    }
		   // System.out.println("indx_key_col:"+indx_key_col);
		   // System.out.println("indx_tar_col:"+indx_tar_col);
		    for(int i=1;i<=row_cnt;i++)
		    {
		    	 cell = wsheet.getRow(i).getCell(0);
		    	// System.out.println("cell.getStringCellValue():"+cell.getStringCellValue());
		    	 if (cell.getStringCellValue().equals(KeyColumnValue))
		    	 {
		    		 indx_key_val = i; 
		    		 break;
		    	 }
		    }
		    
		    //System.out.println("indx_key_val:"+indx_key_val);
		   // HSSFRow row = wsheet.getRow(indx_key_val);
		    Cell cell1;
		    cell1 = wsheet.getRow(indx_key_val).getCell(indx_tar_col);
		    //System.out.println(indx_key_val +" "+indx_tar_col+"value: "+NewValue);
		    //cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		    cell1.setCellValue(NewValue); 
		    
		    fsIP.close(); //Close the InputStream
		     
		    FileOutputStream output_file =new FileOutputStream(new File(workbook_path));  //Open FileOutputStream to write updates
		      
		    wb.write(output_file); //write changes
		      
		    output_file.close();  //close the stream    
		  // System.out.println("End of UpdateXLCell");	
		    	
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	
	
	
	/*
	public static void UpdateXLCellWithLargeString(String SheetName, String NewValue, String ColumnNameTobeUpdated,String KeyColumnName,String KeyColumnValue) throws Exception
	{
		try 
		{
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
		if (KeyColumnValue.length()>0 && ColumnNameTobeUpdated.length()>0)
		{
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
		PreparedStatement ps = con.prepareStatement("UPDATE ["+SheetName+"$] SET "+ColumnNameTobeUpdated+"=? WHERE "+KeyColumnName+"=?");
		
		//ps.setString(1, NewValue);
		InputStream is = new ByteArrayInputStream(NewValue.getBytes());
		final byte[] utf8Bytes = NewValue.getBytes("UTF-8");
		//System.out.println("Total byte length: "+utf8Bytes.length); // prints "11"
		ps.setAsciiStream(1, is,utf8Bytes.length);
		
		//ps.setAsciiStream(parameterIndex, x, length);
		ps.setString(2, KeyColumnValue);
        //ps.setNString(1, NewValue);
        //ps.setNString(1, KeyColumnValue);
		ps.executeUpdate();
	    
		//ps.close();ps=null;
	    //con.close();con=null;
		}
		else 
		{
			System.out.println("The value of param KeyColumnValue or ColumnNameTobeUpdated is blank");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
	*/
	public static void UpdateXLCellWithApend(String SheetName, String NewValue, String ColumnNameTobeUpdated,String KeyColumnName,String KeyColumnValue) throws Exception
	{
		
		try 
		{
			Workbook wb = null;
			//reading existing value from cell
			String existing_value = ReadXLData(SheetName, ColumnNameTobeUpdated, KeyColumnName, KeyColumnValue);
			if (!existing_value.equals(""))
			{
				NewValue = existing_value + "," + NewValue;	
			}
			
			FileInputStream fsIP= new FileInputStream(new File(workbook_path)); //Read the spreadsheet that needs to be updated
			if(workbook_path.endsWith(".xlsx"))
			{
				 wb = new XSSFWorkbook(fsIP);
			}
			else if(workbook_path.endsWith(".xls"))
			{
				 wb = new HSSFWorkbook(fsIP);
			}
			else
			{
				System.out.println("Not a valid excel file.");
			}
			
			
		    Sheet wsheet = wb.getSheet(SheetName); //Access the worksheet, so that we can update / modify it.
		     
		    int col_cnt = wsheet.getRow(0).getLastCellNum();
		    int row_cnt = wsheet.getLastRowNum();
		    
		    int indx_key_col=0, indx_key_val=0, indx_tar_col = 0;
		    
		    //System.out.println("last row num: "+wsheet.getLastRowNum());
		    //System.out.println("last column num: "+wsheet.getRow(0).getLastCellNum());
		    
		    Cell cell = null; 
		    
		    for(int i=0;i<col_cnt;i++)
		    {
		    	 cell = wsheet.getRow(0).getCell(i);
		    	 if (cell.getStringCellValue().equals(KeyColumnName))
		    	 {
		    		 indx_key_col = i; 
		    	 }
		    	 if (cell.getStringCellValue().equals(ColumnNameTobeUpdated))
		    	 {
		    		 indx_tar_col = i; 
		    	 }
		    }
		    
		    //System.out.println("indx_key_col:"+indx_key_col);
		    //System.out.println("indx_tar_col:"+indx_tar_col);
		    
		    for(int i=1;i<=row_cnt;i++)
		    {
		    	 cell = wsheet.getRow(i).getCell(0);
		    	 //System.out.println("cell.getStringCellValue():"+cell.getStringCellValue());
		    	 if (cell.getStringCellValue().equals(KeyColumnValue))
		    	 {
		    		 indx_key_val = i; 
		    		 break;
		    	 }
		    }
		    
		    //System.out.println("indx_key_val:"+indx_key_val);
		   
		    Cell cell1;
		    cell1 = wsheet.getRow(indx_key_val).getCell(indx_tar_col);
		   
		    cell1.setCellValue(NewValue); 
		    
		    fsIP.close(); //Close the InputStream
		     
		    FileOutputStream output_file =new FileOutputStream(new File(workbook_path));  //Open FileOutputStream to write updates
		      
		    wb.write(output_file); //write changes
		      
		    output_file.close();  //close the stream   	    
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}  
	/*
	public static void InsertXLRowInTestLogs(Connection c,String SheetName,String Steps,String TestCaseName,String Details,String Results) throws Exception
	{
		//Connection c = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + "SM.xls" +";readOnly=false");
		try 
		{
		PreparedStatement ps = c.prepareStatement("insert into ["+SheetName+"$] values(?,?,?,?,?)");
		
		ps.setString(1,Steps);
		ps.setString(2, TestCaseName);
		ps.setString(3, Details);
		ps.setString(4, Results);
		ps.setString(5, "");
		
		ps.executeUpdate();
	    
		ps.close();
		c.close();
		}catch(Exception e) 
		{
			e.printStackTrace();
		} 
	}     
	*/
	public static void InsertXLRowInSummaryTestLogs(Workbook wb,String SheetName,String TestCaseName,String Description,String Status,String StartofExecution,String EndofExecution,String Duration,String Browser,String Profiles) throws Exception
	{
		try{
			
			//FileInputStream fsIP= new FileInputStream(new File(workbook_path)); //Read the spreadsheet that needs to be updated
		    
			//XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
		      
		    Sheet wsheet = wb.getSheet(SheetName); //Access the worksheet, so that we can update / modify it.
		     
		    int col_cnt = wsheet.getRow(0).getLastCellNum();
		    int row_cnt = wsheet.getLastRowNum()+1;
		    
		    int indx_key_col=0, indx_key_val=0, indx_tar_col = 0;
		    
		    //System.out.println("Total Number of column: "+col_cnt);
		   // System.out.println("Total Number of row: "+row_cnt);
		    
		    Row row = wsheet.createRow(row_cnt);
		    Cell cell0 = row.createCell(0);
		    Cell cell1 = row.createCell(1);
		    Cell cell2 = row.createCell(2);
		    Cell cell3 = row.createCell(3);
		    Cell cell4 = row.createCell(4);
		    Cell cell5 = row.createCell(5);
		    Cell cell6 = row.createCell(6);
		    Cell cell7 = row.createCell(7);
		    
		    cell0.setCellValue(TestCaseName);
		    cell1.setCellValue(Description);
		    cell2.setCellValue(Status);
		    cell3.setCellValue(StartofExecution);
		    cell4.setCellValue(EndofExecution);
		    cell5.setCellValue(Duration);
		    cell6.setCellValue(Browser);
		    cell7.setCellValue(Profiles);
		    
		  		     
		    FileOutputStream output_file = new FileOutputStream(new File(workbook_path));  //Open FileOutputStream to write updates
		      
		    wb.write(output_file); //write changes
		      
		    output_file.close();  //close the stream
		    wb = null;
		    
		}catch(Exception e) 
		{
			e.printStackTrace();
			wb = null;
		} 
	}  
	
	
	
	public static void Insert_A_Row_IN_Excel(String SheetName,int NoOFColumn,String EntireRow_SemicolonSeparated) throws Exception
	{
		try 
		{
			
			   	Sheet wsheet = wb.getSheet(SheetName); //Access the worksheet, so that we can update / modify it.
			     
			    int col_cnt = wsheet.getRow(0).getLastCellNum();
			    int row_cnt = wsheet.getLastRowNum()+1;
			    
			    int indx_key_col=0, indx_key_val=0, indx_tar_col = 0;
			    
			    //System.out.println("Total Number of column: "+col_cnt);
			    //System.out.println("Total Number of row: "+row_cnt);
			    
			    Row row = wsheet.createRow(row_cnt);
			    
			    Cell[] cell = new XSSFCell[NoOFColumn];
			    
			    //Getting each value to arraylist
				List<String> eachcolumnvalue = Arrays.asList(EntireRow_SemicolonSeparated.split(";"));
				int index_colval=1;
				for(int index=0;index<NoOFColumn;index++)
				{
					cell[index] = row.createCell(index) ;
					cell[index].setCellValue(eachcolumnvalue.get(index));
					index_colval++;
				}
			    
			    FileOutputStream output_file = new FileOutputStream(new File(workbook_path));  //Open FileOutputStream to write updates
			      
			    wb.write(output_file); //write changes
			      
			    output_file.close();  //close the stream
			   
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
	
	public static String _cellToString(Cell cell) throws Exception{
    	// This function will convert an object of type excel cell to a string value
    		try {
			int type = cell.getCellType();
    		Object result;
    		switch (type) {
    			case Cell.CELL_TYPE_NUMERIC: //0
    				result = cell.getNumericCellValue();
    				break;
    			case Cell.CELL_TYPE_STRING: //1
    				result = cell.getStringCellValue();
    				break;
    			case Cell.CELL_TYPE_FORMULA: //2
    				throw new RuntimeException("We can't evaluate formulas in Java");
    			case Cell.CELL_TYPE_BLANK: //3
    				result = "-";
    				break;
    			case Cell.CELL_TYPE_BOOLEAN: //4
    				result = cell.getBooleanCellValue();
    				break;
    			case Cell.CELL_TYPE_ERROR: //5
    				throw new RuntimeException ("This cell has an error");
    			default:
    				throw new RuntimeException("We don't support this cell type: " + type);
          }
          return result.toString();
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    			return "";
    		}
    		
        }
}