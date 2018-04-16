// class Science_AutomationSystem - Shell created by MenuMaker on Wed Jan 17 13:35:30 GMT 2018 by 16Szuddas

import java.awt.*;
import java.util.Arrays;
import park.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

 
public class Science_AutomationSystem {
	
	public class dateFile{
		 

		 dateFile storeDates = new dateFile();
		} 

	
	

   //  CLASS VARIABLES
 PC_Style science_AutomationStyle = new PC_Style();//  holds the style for the user interface
 public static int chemicalIDnumber;
 public static int equipmentIDnumber;
 public static int itemQuantity;
 public static int periodUse;
 public boolean isSelected; 
 
String ChemicalFile = "datafiles/Chemical.dat";
String EquipmentFile = "datafiles/Equipment.dat";
String ItemsFile = "datafiles/Items.dat";
String itemName = "Item Name";
String itemNumber = "Item Number";
String itemLocation = "Item Location";
String chemicalOption = "Chemical";
String equipmentOption = "Equipment";
String cancel = "Cancel";

PC_Date currentDate = new PC_Date();
PC_Date orderDate = new PC_Date();


HashFile itemsFile = new HashFile(ItemsFile, 100, new Items());
HashFile chemicalFile = new HashFile(ChemicalFile, 100, new Chemical()); 
HashFile equipmentFile = new HashFile(EquipmentFile, 100, new Equipment());
 

   //  CLASS METHODS

   Science_AutomationSystem() {
      setStyle(science_AutomationStyle);
      JFrame frame = new JFrame();
      frame.setTitle("Welcome");
      frame.setSize(500, 500);
      
   }



public void quit() {
	
      System.exit(0);
      }
      
private void windowClosing(java.awt.event.WindowEvent evt) {                                   
    	    int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?","EXIT",JOptionPane.YES_NO_OPTION);
    	    if(confirmed == JOptionPane.YES_OPTION)
    	    {
    	        quit();
    	    }
 else {
    	     setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }//  end of method
}
  


public void addChemical (){
	
	Chemical chemical = new Chemical (science_AutomationStyle);
		chemical.input();
		chemicalFile.store(chemical);
}

// end of method

public void addEquipment (){
			Equipment equipment = new Equipment (science_AutomationStyle);
			equipment.input();
			equipmentFile.store(equipment);
}

// end of method

public void addItem() {
		
		
		
	}
//  end of method


public void editItem() {
	
	PC_Table editTable = new PC_Table("Search Table", 0, "Item Quantity, Item Name, ID, Item Hazard, Item Location ","OK");
		editTable.setSize(1500, 1000);
	
	Chemical chemical = new Chemical (science_AutomationStyle);
	 
	Equipment equipment = new Equipment (science_AutomationStyle);
	
	Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	   
	int choice = JOptionPane.showOptionDialog(null, "Which database do you want to search?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]); 
		
	
	
	
	
	if ( choice == -1  ) {
		JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
		
	}
	if ( choice == 0 ) {
		
		String userInput = JOptionPane.showInputDialog(null, "Input an ID number or a Reactant Name:", "Search By ID Number or Reactant Name", 1);
		System.out.println(userInput);
		
		if (userInput == null) {
			JOptionPane.showMessageDialog(null, "Pleaase select a search criteria", "Error Message", 1, null);
			
			
		}else
		
		{
		
		int row = 0;
		   chemicalFile.resetSerialAccess();// prepare the file for serial access
		   while (chemicalFile.moreSerialRecords()) {//loop for each quotation in the file
		   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
		   if ((chemical.reference_Code.contains(userInput))|(chemical.reactant_Name.contains(userInput))) { //if the Inputed values match
		   editTable.addRow();//add a new row to the table
		   //put the fields of chemical into the table
		   editTable.setValueAt(chemical.reactant_Name, row, 1);
		   editTable.setValueAt(chemical.reference_Code, row, 2);
		   editTable.setValueAt(chemical.location, row,4);
		   editTable.setValueAt(chemical.quantity, row, 3);
		   editTable.setValueAt(chemical.hazard_Type, row, 5);
		   
		   row++; // add one to row
		   }
		   }
		   editTable.choice();//display the table
		   editTable.setSize(1920, 1080);
		}
		}
	
	if ( choice == 1 ) {
		String userInput = JOptionPane.showInputDialog(null, "Equipment Location", "Search By Location Name", 1);
		System.out.println(userInput);
		
		if (userInput == null) {
			JOptionPane.showMessageDialog(null, "Pleaase select a search criteria", "Error Message", 1, null);
			
			
		}else{
		int row = 0;
		   equipmentFile.resetSerialAccess();// prepare the file for serial access
		   while (equipmentFile.moreSerialRecords()) {//loop for each quotation in the file
		   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
		   if ((equipment.cupboard_Number.contains(userInput) | equipment.room.contains(userInput))) { //if the surname matches
		   editTable.addRow();//add a new row to the table
		   //put the fields of chemical into the table
		   editTable.setValueAt(equipment.item_Name, row, 1);
		   editTable.setValueAt(equipment.product_Code, row, 2);
		   editTable.setValueAt(equipment.cupboard_Number, row,4);
		   editTable.setValueAt(equipment.quantity, row, 3);
		   editTable.setValueAt(equipment.room, row, 5);
		   editTable.setValueAt(equipment.hazard_Warning,row,6);
		   
		   
		   }
		   }
		   }
	}
	  
 }
	   
//  end of method

public void requestItem() { // ignore dates and just take the item request quantity from the initial quantity.
	
	   int itemRequiredQuantity;
	   boolean confirm = false;
	   
	   // user needs to first select an item:
	   
	   
	   
	   //user then needs to decide how much they are taking out of the given item: 
	   
	   PC_Dialog RequestItem = new PC_Dialog ("Item Quantity", "Quantity Needed, Date for Use+, Period for Use", "Confirm , Cancel");
	   RequestItem.choice();
	   
	   itemRequiredQuantity = RequestItem.getFieldInt(1);
	   orderDate = RequestItem.getFieldDate(2);
	   periodUse = RequestItem.getFieldInt(3);
	   
	   System.out.println(java.time.LocalDate.now()); 
	   System.out.println(orderDate);
	   System.out.println(periodUse);
	   System.out.println(itemRequiredQuantity);
	  
	   
	   
	   
	  
	//  itemQuantity = itemQuantity - itemRequiredQuantity; // the operation used to take away the requested item from the actual item quantity
	  
	  
   }//  end of method

// end of method 
   
public void searchItem() {
	   
	  
	 PC_Table searchTable = new PC_Table("Search Table", 0, "Item Quantity, Item Name, ID, Item Hazard, Item Location ","OK");
	// JScrollPane searchTableScroll = new JScrollPane (searchTable);
	 searchTable.setSize(1500, 1000);
	 searchTable.getScrollPane();
	 
	 
	 
	 Chemical chemical = new Chemical (science_AutomationStyle);
	 
	 Equipment equipment = new Equipment (science_AutomationStyle);
	 // chemical and equipment hash tables.
	   
	 // JCheckBox searchBy = new JCheckBox ("Search Item By:");
	   Object[] searchOptions = {itemName,itemNumber, itemLocation}; // objects for the search table
	   Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	   
	   
	   int searchBy = JOptionPane.showOptionDialog(null,"What would you like to search by?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, searchOptions, searchOptions[0]);
	   int choice = JOptionPane.showOptionDialog(null,"Which database do you want to search?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]); 
	   
	   
	   if ((searchBy & choice) != -1){
		
			System.out.println("Search choice: " + searchBy);
			System.out.println("Table Searched: " + choice );
		
	   }
	   if ( choice == -1  ) {
			JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
			
		} else if (searchBy == -1 ) {
			JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
		}
	   if ((searchBy == 0 ) & (choice == 0 )) {
			String chemicalSearchName = JOptionPane.showInputDialog(null, "Chemical Name", "Search By Name", 1);
			System.out.println(chemicalSearchName);
			
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each quotation in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   if (chemical.reactant_Name.contains(chemicalSearchName)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(chemical.reactant_Name, row, 1);
			   searchTable.setValueAt(chemical.reference_Code, row, 2);
			   searchTable.setValueAt(chemical.quantity, row, 3);
			   searchTable.setValueAt(chemical.location, row,4);
			   searchTable.setValueAt(chemical.hazard_Type, row, 5);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			
		}
	   if ((searchBy == 1 ) & (choice == 0 )) {
			String chemicalID = JOptionPane.showInputDialog(null, "Chemical ID", "Search By ID Number", 1);
			System.out.println(chemicalID);
			
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each quotation in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   if (chemical.reference_Code.contains(chemicalID)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(chemical.reactant_Name, row, 1);
			   searchTable.setValueAt(chemical.reference_Code, row, 2);
			   searchTable.setValueAt(chemical.quantity, row, 3);
			   searchTable.setValueAt(chemical.location, row,4);
			   searchTable.setValueAt(chemical.hazard_Type, row, 5);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	   if ((searchBy == 2 ) & (choice == 0 )) {
			String chemicalLocation = JOptionPane.showInputDialog(null, "Chemical Location", "Search By Location Name", 1);
			System.out.println(chemicalLocation);
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each quotation in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   if (chemical.location.contains(chemicalLocation)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(chemical.reactant_Name, row, 1);
			   searchTable.setValueAt(chemical.reference_Code, row, 2);
			   searchTable.setValueAt(chemical.quantity, row, 3);
			   searchTable.setValueAt(chemical.location, row,4);
			   searchTable.setValueAt(chemical.hazard_Type, row, 5);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			
		}
	   if ((searchBy == 0 ) & (choice == 1 )) {
			String equipmentSearchName = JOptionPane.showInputDialog(null, "Equipment Name", "Search By Name", 1);
			System.out.println(equipmentSearchName);
			
			int row = 0;
			   equipmentFile.resetSerialAccess();// prepare the file for serial access
			   while (equipmentFile.moreSerialRecords()) {//loop for each quotation in the file
				   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
			   if (equipment.item_Name.contains(equipmentSearchName)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(equipment.item_Name, row, 1);
			   searchTable.setValueAt(equipment.product_Code, row, 2);
			   searchTable.setValueAt(equipment.quantity, row, 3);
			   searchTable.setValueAt(equipment.cupboard_Number, row,4);
			   searchTable.setValueAt(equipment.room, row, 5);
			   searchTable.setValueAt(equipment.hazard_Warning,row,6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			
		}
	   
	   if ((searchBy == 1 ) & (choice == 1 )) {
			String equipmentID = JOptionPane.showInputDialog(null, "Equipment ID", "Search By ID Number", 1);
			System.out.println(equipmentID);
			
			int row = 0;
			   equipmentFile.resetSerialAccess();// prepare the file for serial access
			   while (equipmentFile.moreSerialRecords()) {//loop for each quotation in the file
				   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
			   if (equipment.product_Code.contains(equipmentID)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(equipment.item_Name, row, 1);
			   searchTable.setValueAt(equipment.product_Code, row, 2);
			   searchTable.setValueAt(equipment.quantity, row, 3);
			   searchTable.setValueAt(equipment.cupboard_Number, row,4);
			   searchTable.setValueAt(equipment.room, row, 5);
			   searchTable.setValueAt(equipment.hazard_Warning,row,6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	   if ((searchBy == 2 ) & (choice == 1 )) {
			String equipmentLocation = JOptionPane.showInputDialog(null, "Equipment Location", "Search By Location Name", 1);
			System.out.println(equipmentLocation);
			
			int row = 0;
			   equipmentFile.resetSerialAccess();// prepare the file for serial access
			   while (equipmentFile.moreSerialRecords()) {//loop for each quotation in the file
			   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
			   if ((equipment.cupboard_Number.contains(equipmentLocation) | equipment.room.contains(equipmentLocation))) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(equipment.item_Name, row, 1);
			   searchTable.setValueAt(equipment.product_Code, row, 2);
			   searchTable.setValueAt(equipment.quantity, row, 3);
			   searchTable.setValueAt(equipment.cupboard_Number, row,4);
			   searchTable.setValueAt(equipment.room, row, 5);
			   searchTable.setValueAt(equipment.hazard_Warning,row,6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	

	   
   }
// end of method


public void itemHazardReport() {

	/*   
	   String printreport = new String("printreport");
	  
	  
	   printhazard paper = new printhazard();
	  
	   
	   paper.setOrientation(printhazard.PORTRAIT_ORIENTATION);
	   display();
	   PC_Paper();
	   
	   */
	   
	   
	   
	   
	   
   }//  end of method

   // end of method 
   
public void phLevel() {
	   
   }

   //  end of method

public void availableStock() {
	   
	 
	   
	   
	   
   }
   
   //  end of method


	//*sets the style to be used by menus windows etc*/
	
	
void setStyle(PC_Style style){
        //menu styles
        style.setBackgroundColour(null);
        style.setMenuBackgroundColour(null);
        style.setMenuBackgroundPicture("");
        style.setMenuCenter(false);
        style.setMenuFooterBackgroundColour(null);
        style.setMenuFooterFont(null);
        style.setMenuFooterForegroundColour(null);
        style.setMenuFullSize(false);
        style.setMenuInsets(0, 0, 0, 0);
        style.setMenuItemBackgroundColour(null);
        style.setMenuItemFont(null);
        style.setMenuItemForegroundColour(null);
        style.setMenuSpacing(0, 0);
        style.setMenuTitleBackgroundColour(null);
        style.setMenuTitleFont(null);
        style.setMenuTitleForegroundColour(null);
        style.setMenuTitlePicture("");
        
        //table styles
        style.setTableBackgroundColour(null);
        style.setTableButtonBackgroundColour(null);
        style.setTableButtonFont(null);
        style.setTableButtonForegroundColour(null);
        style.setTableCenter(false);
        style.setTableFont(null);
        style.setTableForegroundColour(null);
        style.setTableFullSize(false);
        style.setTableTitleBackgroundColour(null);
        style.setTableTitleFont(null);
        style.setTableTitleForegroundColour(null);
        style.setTableTitlePicture("");
        style.setTextAreaBackgroundColour(null);
        style.setTextAreaFont(null);
        style.setTextAreaForegroundColour(null);
        
        //window style
        style.setWindowBackgroundColour(null);
        style.setWindowBackgroundPicture("");
        style.setWindowButtonBackgroundColour(null);
        style.setWindowButtonFont(null);
        style.setWindowButtonForegroundColour(null);
        style.setWindowCenter(false);
        style.setWindowFullSize(false);
        style.setWindowInsets(10, 10, 10, 10);
        style.setWindowItemEntryBackgroundColour(null);
        style.setWindowItemEntryFont(null);
        style.setWindowItemEntryForegroundColour(null);
        style.setWindowItemLabelBackgroundColour(null);
        style.setWindowItemLabelFont(null);
        style.setWindowItemLabelForegroundColour(null);
        style.setWindowSpacing(10, 10);
        style.setWindowTitleBackgroundColour(null);
        style.setWindowTitleFont(null);
        style.setWindowTitleForegroundColour(null);
        style.setWindowTitlePicture("");
        
    }




}//  End of class

