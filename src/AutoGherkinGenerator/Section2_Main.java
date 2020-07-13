package AutoGherkinGenerator;

import java.io.IOException;

import com.codoid.products.exception.FilloException;

public class Section2_Main {

	public static void main(String[] args) throws FilloException, IOException {
		
		Section2_Step1_FeatureFileGenerator.featureFileCreation();
		Section2_Step2_UniqueGherkinSteps.removeDuplicates();
		Section2_Step2_UniqueGherkinSteps.assignKeyword();
		Section2_Step2_UniqueGherkinSteps.assignHeader();
		}

}
