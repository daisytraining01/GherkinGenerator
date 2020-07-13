package AutoGherkinGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Section3_Step1_AssignKeyword {
	
	public static String inputfile="src/DataSheet/ScenarioMatrix.xlsx";
    public static String outputfile="src/DataSheet/ScenarioMatrixwithKeyword.xlsx";
    
		
	public static void fileCopy() throws IOException {	 	
     FileInputStream input=new FileInputStream(inputfile);
	 FileOutputStream output=new FileOutputStream(outputfile);
		
		 byte[] buffer = new byte[1024];
      int length;         
      while ((length = input.read(buffer)) > 0) {
     	 output.write(buffer, 0, length);
      } 
      input.close();
      output.close();
		    	}
	
	public static void replaceWithSteps() throws FilloException {
		
		LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
		String header="",value="",strQuery2="",strQuery1="",Query="",steps="",keyword="";
				
		String strQuery="Select * from Shares";				
	    Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(outputfile);	
		Recordset recordset = connection.executeQuery(strQuery);
		int countofrecords=recordset.getCount();
		
		for (int MainIter = 1; MainIter<=10; MainIter++) {	
		int count=0;
		strQuery1="Select * from Shares where TestConditionID='TC_0"+MainIter+"'";
		Recordset recordset1 = connection.executeQuery(strQuery1);
		
		while (recordset1.next()) {		
		String expectedScenario=recordset1.getField("ExpectedScenario");
	    String[] scenario=expectedScenario.split("\n");
	    		
		ArrayList<String> headers = recordset1.getFieldNames();		
		headers.remove("TestConditionID");
		headers.remove("Secnario_Tag");
		headers.remove("Feature_tag");
		headers.remove("Secnario_Data");
		headers.remove("Feature_Data");
		headers.remove("Feature_Name");
		headers.remove("Scenario_Name");
		headers.remove("ExpectedScenario");
		headers.remove("Update_Feature");	
		Iterator<String> dataIterator = headers.iterator();
		
		while (dataIterator.hasNext()) {
			 header = dataIterator.next();
			 value = recordset1.getField(header);
			 map1.put(header, value);		
			 for(int i=0;i<scenario.length;i++) {
				 if(!value.isEmpty()) {					 
	        		strQuery2="Update Shares Set "+header+"='"+scenario[count]+"' where TestConditionID='TC_0"+MainIter+"'";
				    connection.executeUpdate(strQuery2);
					count=count+1;
				    break;
							
				 }else {
					 break;
				 }
			 }					   
			}
		}
		}
		connection.close();			
		}	
	
	public static void assignKeyword() throws FilloException {
		
		String header="",value="",uniqueSteps="",keywords="";
		String file="src/DataSheet/UniqueGherkinStepsRepo.xlsx";
		
		String strQuery="Select * from Shares";				
	    Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(outputfile);	
		Recordset recordset = connection.executeQuery(strQuery);
		int countofrecords=recordset.getCount();
		
		for (int MainIter = 1; MainIter<=10 ; MainIter++) {	
		String strQuery1="Select * from Shares where TestConditionID='TC_0"+MainIter+"'";
		recordset = connection.executeQuery(strQuery1);
		
		while (recordset.next()) {		
		ArrayList<String> headers = recordset.getFieldNames();		
		headers.remove("TestConditionID");	
		headers.remove("Secnario_Tag");
		headers.remove("Feature_tag");
		headers.remove("Secnario_Data");
		headers.remove("Feature_Data");
		headers.remove("Feature_Name");
		headers.remove("Scenario_Name");
		headers.remove("ExpectedScenario");
		headers.remove("Update_Feature");
		
		Iterator<String> dataIterator = headers.iterator();
		while (dataIterator.hasNext()) {
			 header = dataIterator.next();
			 value = recordset.getField(header);
		
		String strQuery2="Select * from Steps";
		Fillo fillo2=new Fillo();
		Connection connection2=fillo2.getConnection(file);	
		Recordset recordset2 = connection2.executeQuery(strQuery2);
		int keywordCount=recordset2.getCount();
	
		for(int count=1;count<=keywordCount;count++) {
			String strQuery3="Select * from Steps where Keywords='Keyword"+count+"'";
			recordset2=connection2.executeQuery(strQuery3);
				while(recordset2.next()) {		
				uniqueSteps=recordset2.getField("UniqueSteps");
				keywords=recordset2.getField("Keywords");
				String[] Steps=uniqueSteps.split("\n");
				
				for(int i=0;i<Steps.length;i++) {					
				if(value.toString().equals(Steps[i])){
            	String strQuery4="Update Shares Set "+header+"='"+keywords+"' where TestConditionID='TC_0"+MainIter+"'";
				connection.executeUpdate(strQuery4);
				break;
				}else {
					break;
				}			
				}
				}				
			}
		}
	}
	}
		System.out.println("*****Scenario Matrix with Keyword is generated****");
	}
	}