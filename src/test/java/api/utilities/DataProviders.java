package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;




public class DataProviders {
	
	
	@DataProvider(name="PetData")
	public static String[][] getPetData() throws IOException //data provider method should always be static.
	{
		String petDataSheetPath = "F:\\WorkSpace\\RestAssuredPetStoreAutomation-main\\resources\\PetData.xlsx";
		XLUtility xlutil = new XLUtility(petDataSheetPath);
		
		int rownum = xlutil.getRowCount("petDetails"); //This method gives the count form 1 to last the row number but in actual code row indexing starts from 0.
		int colcount = xlutil.getCellCount("petDetails", 0); //This method gives the count form 1 to last the column number but in actual code column indexing starts from 0.
		
		String petData [][] = new String[rownum][colcount];
		for(int i=1; i<=rownum; i++) //This will fetch data from second row.
		{
			for(int j=0; j<colcount; j++) //This will fetch data from second column.
			{
				petData[i-1][j]= xlutil.getCellData("petDetails", i, j);//indexing in array to store data starts from 0.
			}
		}
		return petData;
	}
	
	
	
	@DataProvider(name="UpdatedPetData")
	public static String[][] getUpdatedPetData() throws IOException //data provider method should always be static.
	{
		String petDataSheetPath = "F:\\WorkSpace\\RestAssuredPetStoreAutomation-main\\resources\\PetData.xlsx";
		XLUtility xlutil = new XLUtility(petDataSheetPath);
		
		int rownum = xlutil.getRowCount("updatedPetDetails"); //This method gives the count form 1 to last the row number but in actual code row indexing starts from 0.
		int colcount = xlutil.getCellCount("updatedPetDetails", 0); //This method gives the count form 1 to last the column number but in actual code column indexing starts from 0.
		
		String petData [][] = new String[rownum][colcount];
		for(int i=1; i<=rownum; i++) //This will fetch data from second row.
		{
			for(int j=1; j<colcount; j++) //This will fetch data from second column.
			{
				petData[i-1][j]= xlutil.getCellData("updatedPetDetails", i, j);//indexing in array to store data starts from 0.
			}
		}
		return petData;
	}

}
