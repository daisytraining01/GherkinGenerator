package AutoGherkinGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Section2_Step2_UniqueGherkinSteps {

	public static void removeDuplicates() throws FileNotFoundException, IOException {
		
			Set<String> set=new LinkedHashSet<String>();
			String srcfile="src/DataSheet/GherkinStepsRepo.xlsx";
			String descfile="src/DataSheet/UniqueGherkinStepsRepo.xlsx";
			
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(srcfile))); 
		    XSSFSheet sheet = workbook.getSheetAt(0); 
	  
		    FileOutputStream out = new FileOutputStream(new File(descfile));
		    XSSFWorkbook workbook1 = new XSSFWorkbook(); 
		    XSSFSheet sheet1 = workbook1.createSheet("Steps");
		    
			 for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				 Row currentrow=sheet.getRow(i);
				 Cell currentcell=currentrow.getCell(0);			
				 set.add(currentcell.getStringCellValue());
			 }		
			 
			   int rownum = 1,cellnum = 1;
			   for (String s : set) {		
			    	Row row1 = sheet1.createRow(rownum++);		    	 
			    	  Cell cell1 = row1.createCell(cellnum);
			    	 cell1.setCellValue(s);		    	
				}

			    workbook1.write(out);
			    out.close();
			    System.out.println("****Unique Gherkin Steps Generated****");
	}
	
	public static void assignKeyword() throws IOException {
		
    String file="src/DataSheet/UniqueGherkinStepsRepo.xlsx";
		
		Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(file))); 
	    Sheet sheet = workbook.getSheetAt(0); 		   
	   	    
	    for(int i=1;i<=sheet.getPhysicalNumberOfRows();i++) {
			Row currentrow=sheet.getRow(i);
			Cell nextcell = currentrow.createCell(0);	
			 nextcell.setCellValue("Keyword"+i);		 
	   
	    }
	    FileOutputStream fileOut = new FileOutputStream(file);  
	    workbook.write(fileOut);
	    fileOut.close();
	    System.out.println("****Keywords Assigned****");
		    
	}
	
	public static void assignHeader() throws IOException {
		
	    String file="src/DataSheet/UniqueGherkinStepsRepo.xlsx";
	    XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(file))); 
	    XSSFSheet sheet = workbook.getSheetAt(0); 
      
	   	Row row=sheet.createRow(0);
	   	Cell cell1=row.createCell(0);
	   	cell1.setCellValue("Keywords");

	   	Cell cell2=row.createCell(1);
	   	cell2.setCellValue("UniqueSteps");
	   	
	    FileOutputStream fileOut = new FileOutputStream(new File(file));  
	    workbook.write(fileOut);
	    fileOut.close();
	    System.out.println("****Headers Added****");
	}
}
	


