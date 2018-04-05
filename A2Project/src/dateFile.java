import java.util.*;
import java.io.*;
import java.lang.*;



public class dateFile {

	private Formatter x;
	
	
	public void openFile(){
		try {
			x = new Formatter("datafiles/DateFile.txt");
			
		}
		catch (Exception e){
			System.out.println("error");
		}
	}
	
	public void addRecords(){
		x.format("PC_Date", "18/02/1998"); // format the record that will be taken to correspond to the date set in the request item dialog box. 
								  // ensure that the argument( second bit) is the value of the date that has been inputted.
									
	}
	
	public void closeFile(){
		x.close();
	}

}
