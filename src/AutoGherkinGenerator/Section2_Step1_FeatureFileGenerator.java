package AutoGherkinGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Section2_Step1_FeatureFileGenerator {
	
	public static void featureFileCreation() throws FilloException, IOException {
		
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(
				"src/DataSheet/ScenarioMatrix.xlsx");		
		String strQuery = "Select * from Shares";
		Recordset recordset = connection.executeQuery(strQuery);	
		int countofrecords=recordset.getCount();		
		for (int MainIter = 1; MainIter<=10 ; MainIter++) {	
			
		String strQuery1 = "Select * from Shares where TestConditionID='TC_0" + MainIter +"'" ;
		recordset = connection.executeQuery(strQuery1);	
	    String stepValue = PrefixDataMethod.Records( recordset);
	    String Examples=recordset.getField("TestConditionID");
	    String ScenarioTag=recordset.getField("Secnario_Tag");
		String FeatureTag=recordset.getField("Feature_tag");
		String ScenarioData=recordset.getField("Secnario_Data");
		String FeatureData=recordset.getField("Feature_Data");
		
		String fileName = 
				"src/Features/Scenario"+MainIter+".feature";    
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));  
		writer.write(FeatureTag);
		writer.write("\n");
		writer.write("Feature: "+FeatureData);
		writer.write("\n");
		writer.write(ScenarioTag);
		writer.write("\n");
		writer.write("Scenario Outline: "+ScenarioData);		
		writer.write("\n");
		writer.write(stepValue);
		writer.write("\n");
		writer.write("Examples:");
		writer.write("\n");
		writer.write("|TestConditionID|");
		writer.write("\n");
		writer.write("|"+Examples+"|");
				
			String Query =
			"Update Shares Set ExpectedScenario='"+stepValue+"',Scenario_Name='TC0"+MainIter+"_scenario"+MainIter+"' where TestConditionID='TC_0"+MainIter+"'";
			connection.executeUpdate(Query);		
			writer.close();
			 	} 
		connection.close();
		System.out.println("*****Feature Files are Generated*****");
			}
}


	