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
String itemLocation = "Item Location/Cupboard Number";
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
	
	PC_Table editTable = new PC_Table("Search Table", 0, " Item Name, ID ,Item Quantity# ,Hazard Warning#, Item Hazard, Item Location/Cupboard Number, Room","OK");
		editTable.setSize(1500, 1000);
		
		
	
	Chemical chemical = new Chemical (science_AutomationStyle);
	 
	Equipment equipment = new Equipment (science_AutomationStyle);
	
	Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	   
	int choice = JOptionPane.showOptionDialog(null, "Which database would you like to edit?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]); 
	
	
	
	
	
	if ( choice == -1  ) {
		JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
		
	}
	if ( choice == 0 ) {
		
		String userInput = JOptionPane.showInputDialog(null, "Input an ID number or a Reactant Name to narrow down your search:", "Search By ID Number or Reactant Name", 1);
		System.out.println(userInput);
		
		if (userInput == null) {
			JOptionPane.showMessageDialog(null, "Please select a search criteria", "Error Message", 1, null);
			
			
		}else
		
		{
		
		int row = 0;
		   chemicalFile.resetSerialAccess();// prepare the file for serial access
		   while (chemicalFile.moreSerialRecords()) {//loop for each Chemical in the file
		   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
		   if ((chemical.reference_Code.contains(userInput))|(chemical.reactant_Name.contains(userInput))) { //if the Inputed values match
		   editTable.addRow();//add a new row to the table
		   //put the fields of chemical into the table
		   editTable.setValueAt(chemical.reactant_Name, row, 0);
		   editTable.setValueAt(chemical.reference_Code, row, 1);
		   editTable.setValueAt(chemical.quantity, row, 2);
		   editTable.setValueAt(chemical.hazard_Type, row, 4);
		   editTable.setValueAt(chemical.location, row,5);
		   
		   row++; // add one to row
		   }
		   }
		   editTable.choice();//display the table
		   editTable.setSize(1920, 1080);
		   String chemicalIDNumber = editTable.getValueAt(editTable.getSelectedRow() , 1);
		   chemical = (Chemical) chemicalFile.retrieve(chemicalIDNumber);
		   if (chemical != null){
			   chemical.edit();
			   chemicalFile.store(chemical);
		   }else
			   JOptionPane.showMessageDialog(null, "Error - Unable to find Item");
		   if (row > 0){
			   
		   }else JOptionPane.showMessageDialog(null, "Error - No matching item");
		   }
		}
		
	
	if ( choice == 1 ) {
		String userInput = JOptionPane.showInputDialog(null, "Input an ID number or a Equipment Name to narrow down your search:", "Search Equipment ID or Equipment Location", 1);
		System.out.println(userInput);
		
		if (userInput == null) {
			JOptionPane.showMessageDialog(null, "Please select a search criteria", "Error Message", 1, null);
			
			
		}else{
		int row = 0;
		   equipmentFile.resetSerialAccess();// prepare the file for serial access
		   while (equipmentFile.moreSerialRecords()) {//loop for each Equipment in the file
		   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
		   if ((equipment.cupboard_Number.contains(userInput) | equipment.room.contains(userInput))) { //if the surname matches
		   editTable.addRow();//add a new row to the table
		   //put the fields of chemical into the table
		   editTable.setValueAt(equipment.item_Name, row, 0);
		   editTable.setValueAt(equipment.product_Code, row, 1);
		   editTable.setValueAt(equipment.quantity, row, 2);
		   editTable.setValueAt(equipment.cupboard_Number, row,5);
		   editTable.setValueAt(equipment.room, row, 6);
		   
		   
		   }
		   }
		   editTable.choice();
		   editTable.setSize(1920,1080);
		   String equipmentIDNumber = editTable.getValueAt(editTable.getSelectedRow() , 1);
		   equipment = (Equipment) equipmentFile.retrieve(equipmentIDNumber);
		   if (equipment != null){
			   equipment.edit();
			   equipmentFile.store(equipment);
		   }else
			   JOptionPane.showMessageDialog(null, "Error - Unable to find Item");
		   if (row > 0){
			   
		   }else JOptionPane.showMessageDialog(null, "Error - No matching item");
		   }
	}
	  
 }
	   
//  end of method

public void requestItem() { 
	
	PC_Table requestTable = new PC_Table("Search Table", 0, " Item Name, ID ,Item Quantity# ,Hazard Warning#, Item Hazard, Item Location/Cupboard Number, Room","OK");
	requestTable.setSize(1500, 1000);
		
		
	
	Chemical chemical = new Chemical (science_AutomationStyle);
	 
	Equipment equipment = new Equipment (science_AutomationStyle);
	
	Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	   
	int choice = JOptionPane.showOptionDialog(null, "Which database are you making an order from?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]); 
	
	
	
	
	
	if ( choice == -1  ) {
		JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
		
	}
	if ( choice == 0 ) {
		
		String userInput = JOptionPane.showInputDialog(null, "Input an ID number or a Reactant Name to narrow down your search:", "Search By ID Number or Reactant Name", 1);
		System.out.println(userInput);
		
		if (userInput == null) {
			JOptionPane.showMessageDialog(null, "Please select a search criteria", "Error Message", 1, null);
			
			
		}else
		
		{
		
		int row = 0;
		   chemicalFile.resetSerialAccess();// prepare the file for serial access
		   while (chemicalFile.moreSerialRecords()) {//loop for each Chemical in the file
		   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
		   if ((chemical.reference_Code.contains(userInput))|(chemical.reactant_Name.contains(userInput))) { //if the Inputed values match
			   requestTable.addRow();//add a new row to the table
		   //put the fields of chemical into the table
			   requestTable.setValueAt(chemical.reactant_Name, row, 0);
			   requestTable.setValueAt(chemical.reference_Code, row, 1);
			   requestTable.setValueAt(chemical.quantity, row, 2);
			   requestTable.setValueAt(chemical.hazard_Type, row, 4);
			   requestTable.setValueAt(chemical.location, row,5);
		   
		   row++; // add one to row
		   }
		   }
		   requestTable.choice();//display the table
		   int chemicalQuantity = requestTable.getIntValueAt(requestTable.getSelectedRow(), 2);
		   chemical = (Chemical) chemicalFile.retrieve(chemicalQuantity);
		   
		   if (chemical != null){
			   
		   PC_Dialog requestChemical = new PC_Dialog("Order", "Chemical ID, Chemical Name, Quantity Required, Period Needed", "Confirm Order, Cancel");
		   requestChemical.setField(0, 101);
		   chemicalFile.store(chemical);
		   
		   }else
			   JOptionPane.showMessageDialog(null, "Error - Unable to find Item");
		   if (row > 0){
			   
		   }else JOptionPane.showMessageDialog(null, "Error - No matching item");
		   
		   /*
		   requestTable.setSize(1920, 1080);
		   String chemicalIDNumber = requestTable.getValueAt(requestTable.getSelectedRow() , 1);
		   chemical = (Chemical) chemicalFile.retrieve(chemicalIDNumber);
		   if (chemical != null){
			   chemical.edit();
			   chemicalFile.store(chemical);
		   }else
			   JOptionPane.showMessageDialog(null, "Error - Unable to find Item");
		   if (row > 0){
			   
		   }else JOptionPane.showMessageDialog(null, "Error - No matching item");
		   */		   
		   
		}
		}
		
	
	if ( choice == 1 ) {
		String userInput = JOptionPane.showInputDialog(null, "Input an ID number or a Equipment Name to narrow down your search:", "Search Equipment ID or Equipment Location", 1);
		System.out.println(userInput);
		
		if (userInput == null) {
			JOptionPane.showMessageDialog(null, "Please select a search criteria", "Error Message", 1, null);
			
			
		}else{
		int row = 0;
		   equipmentFile.resetSerialAccess();// prepare the file for serial access
		   while (equipmentFile.moreSerialRecords()) {//loop for each Equipment in the file
		   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
		   if ((equipment.cupboard_Number.contains(userInput) | equipment.room.contains(userInput))) { //if the surname matches
			   requestTable.addRow();//add a new row to the table
		   //put the fields of chemical into the table
			   requestTable.setValueAt(equipment.item_Name, row, 0);
			   requestTable.setValueAt(equipment.product_Code, row, 1);
			   requestTable.setValueAt(equipment.quantity, row, 2);
			   requestTable.setValueAt(equipment.cupboard_Number, row,5);
			   requestTable.setValueAt(equipment.room, row, 6);
		   
		   
		   }
		   }
		  
		   requestTable.choice();
		   
		   
	}
	  
 } 
	   
	   
	  	   
	  
	//  itemQuantity = itemQuantity - itemRequiredQuantity; // the operation used to take away the requested item from the actual item quantity
	  
	  
 }

// end of method 
   
public void searchItem() {
	   
	  
	 PC_Table searchTable = new PC_Table("Search Table", 0, " Item Name, ID ,Item Quantity# ,Hazard Warning#, Item Hazard, Item Location/Cupboard Number, Room","OK");
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
	   
	   if ( choice == -1  ) {
			JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
			
		} else if (searchBy == -1 ) {
			JOptionPane.showMessageDialog(null, "Error - select search criteria", "Error Message", 1, null);
		}
	   if ((searchBy == 0 ) & (choice == 0 )) {
			String chemicalSearchName = JOptionPane.showInputDialog(null, "Chemical Name", "Search By Name", 1);
			
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each Chemical in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   if (chemical.reactant_Name.contains(chemicalSearchName)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(chemical.reactant_Name, row, 0);
			   searchTable.setValueAt(chemical.reference_Code, row, 1);
			   searchTable.setValueAt(chemical.quantity, row, 2);
			   searchTable.setValueAt(chemical.hazard_Type, row, 4);
			   searchTable.setValueAt(chemical.location, row,5);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			   searchTable.editCellAt(1, 2);
			
		}
	   if ((searchBy == 1 ) & (choice == 0 )) {
			String chemicalID = JOptionPane.showInputDialog(null, "Chemical ID", "Search By ID Number", 1);
			
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each Chemical in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   if (chemical.reference_Code.contains(chemicalID)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(chemical.reactant_Name, row, 0);
			   searchTable.setValueAt(chemical.reference_Code, row, 1);
			   searchTable.setValueAt(chemical.quantity, row, 2);
			   searchTable.setValueAt(chemical.hazard_Type, row, 4);
			   searchTable.setValueAt(chemical.location, row,5);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	   if ((searchBy == 2 ) & (choice == 0 )) {
			String chemicalLocation = JOptionPane.showInputDialog(null, "Chemical Location", "Search By Location Name", 1);
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each Chemical in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   if (chemical.location.contains(chemicalLocation)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(chemical.reactant_Name, row, 0);
			   searchTable.setValueAt(chemical.reference_Code, row, 1);
			   searchTable.setValueAt(chemical.quantity, row, 2);
			   searchTable.setValueAt(chemical.hazard_Type, row, 4);
			   searchTable.setValueAt(chemical.location, row,6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			
		}
	   if ((searchBy == 0 ) & (choice == 1 )) {
			String equipmentSearchName = JOptionPane.showInputDialog(null, "Equipment Name", "Search By Name", 1);
			
			int row = 0;
			   equipmentFile.resetSerialAccess();// prepare the file for serial access
			   while (equipmentFile.moreSerialRecords()) {//loop for each quotation in the file
				   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
			   if (equipment.item_Name.contains(equipmentSearchName)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(equipment.item_Name, row, 0);
			   searchTable.setValueAt(equipment.product_Code, row, 1);
			   searchTable.setValueAt(equipment.quantity, row, 2);
			   searchTable.setValueAt(equipment.cupboard_Number, row,5);
			   searchTable.setValueAt(equipment.room, row, 6);
			   
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			
		}
	   
	   if ((searchBy == 1 ) & (choice == 1 )) {
			String equipmentID = JOptionPane.showInputDialog(null, "Equipment ID", "Search By ID Number", 1);
			
			int row = 0;
			   equipmentFile.resetSerialAccess();// prepare the file for serial access
			   while (equipmentFile.moreSerialRecords()) {//loop for each Equipment in the file
				   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
			   if (equipment.product_Code.contains(equipmentID)) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of Equipment into the table
			   searchTable.setValueAt(equipment.item_Name, row, 0);
			   searchTable.setValueAt(equipment.product_Code, row, 1);
			   searchTable.setValueAt(equipment.quantity, row, 2);
			   searchTable.setValueAt(equipment.cupboard_Number, row,5);
			   searchTable.setValueAt(equipment.room, row, 6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	   if ((searchBy == 2 ) & (choice == 1 )) {
			String equipmentLocation = JOptionPane.showInputDialog(null, "Equipment Location", "Search By Location Name", 1);
			
			int row = 0;
			   equipmentFile.resetSerialAccess();// prepare the file for serial access
			   while (equipmentFile.moreSerialRecords()) {//loop for each Equipment in the file
			   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
			   if ((equipment.cupboard_Number.contains(equipmentLocation) | equipment.room.contains(equipmentLocation))) { //if the surname matches
			   searchTable.addRow();//add a new row to the table
			   //put the fields of chemical into the table
			   searchTable.setValueAt(equipment.item_Name, row, 0);
			   searchTable.setValueAt(equipment.product_Code, row, 1);
			   searchTable.setValueAt(equipment.quantity, row, 2);
			   searchTable.setValueAt(equipment.cupboard_Number, row,5);
			   searchTable.setValueAt(equipment.room, row, 6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	

	   
   }

// end of method

public void itemHazardReport() {
	   
	Chemical chemical = new Chemical (science_AutomationStyle);
		
	PC_Table chemicalStock = new PC_Table ("All Chemicals", 0, "Item Name, ID, Item Quantity#, Item Location/Cupboard Number, Item Hazard", "OK, Print");
	
	PC_Paper hazardReport = new PC_Paper();
	hazardReport.setOrientation(PC_Paper.PORTRAIT_ORIENTATION);
	hazardReport.setOrientation(PC_Paper.ALIGN_CENTER);
	hazardReport.setSize(900, 900);
	
			
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each chemical in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   
			chemicalStock.addRow();//add a new row to the table //put the fields of chemical into the table
			chemicalStock.setValueAt(chemical.reactant_Name, row,0);
			chemicalStock.setValueAt(chemical.reference_Code, row,1);
			chemicalStock.setValueAt(chemical.quantity, row, 2);
			chemicalStock.setValueAt(chemical.location, row,3);
			chemicalStock.setValueAt(chemical.hazard_Type, row, 4);
			   
			   row++; // add one to row
			   
			   }
			  
			   int editButton = chemicalStock.choice();//display the table
			   
			   
			   if(editButton == 2){
				   String chemicalIDNumber = chemicalStock.getValueAt(chemicalStock.getSelectedRow() , 1);
				   chemical = (Chemical) chemicalFile.retrieve(chemicalIDNumber);
				   if (chemical != null){
					   
				   
			   
				   hazardReport.drawString("Chemical Name: "+chemical.reactant_Name,30, 30);
				   hazardReport.drawString("Reference Code: "+chemical.reference_Code, 30, 50);
				   hazardReport.drawString("Quantity: "+chemical.quantity, 30, 70);
				   hazardReport.drawString("Location: "+chemical.location, 30, 90);
				   hazardReport.drawString("Hazard Type: "+chemical.hazard_Type, 30, 110);
				   hazardReport.drawString("pH level: "+chemical.pH_level, 30, 130);
				   hazardReport.display();
					
			   }
			 }
		}

// end of method 
   
public void phLevel() {
	Chemical chemical = new Chemical(science_AutomationStyle);
	
	PC_Table pHLevel = new PC_Table("pH Level", 0, "pH Level#, Hazard Type, Item Name, Item ID, Item Quantity#, Item Location/Cupboard Number ","OK");
	
	
	int row = 0;
	   chemicalFile.resetSerialAccess();// prepare the file for serial access
	   while (chemicalFile.moreSerialRecords()) {//loop for each chemical in the file
	   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
	   if (chemical.pH_level > 0){
	   pHLevel.addRow();//add a new row to the table
	   //put the fields of chemical into the table
	   pHLevel.setValueAt(chemical.pH_level, row, 0);
	   pHLevel.setValueAt(chemical.hazard_Type, row, 1);
	   pHLevel.setValueAt(chemical.reactant_Name, row, 2);
	   pHLevel.setValueAt(chemical.reference_Code, row, 3);
	   pHLevel.setValueAt(chemical.quantity, row, 4);
	   pHLevel.setValueAt(chemical.location, row,5);
	   
	   
	   row++; // add one to row
	   }
	   }
	   pHLevel.sortNumeric(0, true);
	   pHLevel.choice();//display the table
	
	
   }

//  end of method

public void availableStock() {
	   
	Chemical chemical = new Chemical (science_AutomationStyle);
	Equipment equipment = new Equipment (science_AutomationStyle);
	
	PC_Table equipmentStock = new PC_Table ("All Stock", 0, "Item Name, ID ,Item Quantity# , Item Location/Cupboard Number, Item Hazard","OK, Edit");
	
	PC_Table chemicalStock = new PC_Table ("All Stock", 0, "Item Name, ID, Item Quantity#, Item Location/Cupboard Number, Item Hazard", "OK,Edit");
	
	
	Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	
	int tableChoice = JOptionPane.showOptionDialog(null,"Which database would you like to view?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]);
	
	   
	
if ( tableChoice == -1  ) {
				JOptionPane.showMessageDialog(null, "Error - please select a database to view", "Error Message", 1, null);
				
			}
if (tableChoice == 0){
			
			int row = 0;
			   chemicalFile.resetSerialAccess();// prepare the file for serial access
			   while (chemicalFile.moreSerialRecords()) {//loop for each chemical in the file
			   chemical = (Chemical) chemicalFile.getNextSerialValue(); // read chemical
			   
			chemicalStock.addRow();//add a new row to the table //put the fields of chemical into the table
			chemicalStock.setValueAt(chemical.reactant_Name, row,0);
			chemicalStock.setValueAt(chemical.reference_Code, row,1);
			chemicalStock.setValueAt(chemical.quantity, row, 2);
			chemicalStock.setValueAt(chemical.location, row,3);
			chemicalStock.setValueAt(chemical.hazard_Type, row, 4);
			   
			   row++; // add one to row
			   
			   }
			  
			   int editButton = chemicalStock.choice();//display the table
			   
			   if(editButton == 2){
			   
			   chemicalStock.setSize(1920, 1080);
			   String chemicalIDNumber = chemicalStock.getValueAt(chemicalStock.getSelectedRow() , 1);
			   chemical = (Chemical) chemicalFile.retrieve(chemicalIDNumber);
			   if (chemical != null){
				   chemical.edit();
				   chemicalFile.store(chemical);
			   }else
				   JOptionPane.showMessageDialog(null, "Error - Unable to find Item");
			   if (row > 0){
				   
			   }else JOptionPane.showMessageDialog(null, "Error - No matching item");
			   
			   }
	    else {
	    	
	    }}
	   

if (tableChoice == 1){
		   System.out.println(tableChoice);
		  
			
		   int row = 0;
		   equipmentFile.resetSerialAccess();// prepare the file for serial access
		   while (equipmentFile.moreSerialRecords()) {//loop for each chemical in the file
		   equipment = (Equipment) equipmentFile.getNextSerialValue(); // read chemical
		   
		   equipmentStock.addRow();//add a new row to the table
		   //put the fields of chemical into the table
		   equipmentStock.setValueAt(equipment.item_Name, row,0);
		   equipmentStock.setValueAt(equipment.product_Code, row,1);
		   equipmentStock.setValueAt(equipment.quantity, row, 2);
		   equipmentStock.setValueAt(equipment.cupboard_Number, row,3);
		   equipmentStock.setValueAt(equipment.hazard_Type, row, 4);
		   
		   row++; // add one to row			   }
		   }
		   
		   int editButton = equipmentStock.choice();//display the table
		   
		   if(editButton == 2){
		   
		   equipmentStock.setSize(1920, 1080);
		   String equipmentIDNumber = equipmentStock.getValueAt(equipmentStock.getSelectedRow() , 1);
		   equipment = (Equipment) equipmentFile.retrieve(equipmentIDNumber);
		   if (equipment != null){
			   equipment.edit();
			   equipmentFile.store(equipment);
		   }else
			   JOptionPane.showMessageDialog(null, "Error - Unable to find Item");
		   if (row > 0){
			   
		   }else JOptionPane.showMessageDialog(null, "Error - No matching item");
		   
		   }
    else
   {
   
   }
}}

//  end of method

//*sets the style to be used by menus windows etc*/	
	
void setStyle(PC_Style style){
        //menu styles
        style.setBackgroundColour(null);
        style.setMenuBackgroundColour(null);
        style.setMenuBackgroundPicture("");
        style.setMenuFooterBackgroundColour(null);
        style.setMenuFooterFont(null);
        style.setMenuFooterForegroundColour(null);
        style.setMenuCenter(true);
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

