package AutoGherkinGenerator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Section3_Step2_UpdatedFeatureFile {
	
	public static void updatedFeatureFileCreation() throws FilloException, IOException {
		
		String TC="",header="",value="";
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();	
	
		Fillo fillo1 = new Fillo();		
        Connection connection1 = fillo1.getConnection("src/DataSheet/UniqueGherkinStepsRepo.xlsx"); 
				
		Fillo fillo = new Fillo();		
		Connection connection = fillo.getConnection("src/DataSheet/ScenarioMatrixwithKeyword.xlsx"); 
		String strQuery = "Select * from Shares where Update_Feature='Yes'";
		Recordset recordset = connection.executeQuery(strQuery);
		
		while(recordset.next()) {
			TC=recordset.getField("TestConditionID");
			System.out.println(TC);
			
			String strQuery2 = "Select * from Shares where Update_Feature='Yes' and TestConditionID='"+TC+"'";
			Recordset recordset2 = connection.executeQuery(strQuery2);
			while (recordset2.next()) {
				ArrayList<String> fields = recordset2.getFieldNames();
				Iterator<String> dataIterator = fields.iterator();	
				while (dataIterator.hasNext()) {
					header = dataIterator.next();
					value = recordset2.getField(header);
					if (value.isEmpty()) {
						map.remove(header, value);
					}	
					map.put(header, value);
					map.remove("TestConditionID");
					map.remove("Secnario_Tag");
					map.remove("Feature_tag");
					map.remove("Secnario_Data");
					map.remove("Feature_Data");
					map.remove("Feature_Name");
					map.remove("Scenario_Name");
					map.remove("ExpectedScenario");
					map.remove("Update_Feature");
						
	           if (value.startsWith("Keyword")) {
					
					String strQuery1 = "Select * from Steps where Keywords='"+value+"'";
					Recordset recordset1 = connection1.executeQuery(strQuery1);
					while(recordset1.next()) {
					String UniqueSteps=recordset1.getField("UniqueSteps");
					map.replace(header, value, UniqueSteps);
					}
				}
				}
			}
			String MapValues=map.values().toString();	
			String Val[]=MapValues.split(", ");
			String Examples=recordset2.getField("TestConditionID");
		    String ScenarioTag=recordset2.getField("Secnario_Tag");
			String FeatureTag=recordset2.getField("Feature_tag");
			String ScenarioData=recordset2.getField("Secnario_Data");
			String FeatureData=recordset2.getField("Feature_Data");
						
		String fileName = 
				"src/Features/UpdatedScenario"+TC+".feature";    
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));  
		writer.write(FeatureTag);
		writer.write("\n");
		writer.write("Feature: "+FeatureData);
		writer.write("\n");
		writer.write(ScenarioTag);
		writer.write("\n");
		writer.write("Scenario Outline: "+ScenarioData);		
		writer.write("\n");
		
		for(int j=0;j<Val.length;j++) {
		String Steps=Val[j].toString().replace("[", "").replace("]","");
	    writer.write(Steps);
		}
		
		writer.write("\n");
		writer.write("Examples:");
		writer.write("\n");
		writer.write("|TestConditionID|");
		writer.write("\n");
		writer.write("|"+Examples+"|");	
		writer.close();
		System.out.println("Updating feature file");
		}
	}
			
}
	


