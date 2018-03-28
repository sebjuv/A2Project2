// class Science_AutomationSystem - Shell created by MenuMaker on Wed Jan 17 13:35:30 GMT 2018 by 16Szuddas

import java.awt.*;
import java.util.Arrays;
import park.*;
import javax.swing.*;
import java.util.Scanner;

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
String itemLocation = "Item Location";
String chemicalOption = "Chemical";
String equipmentOption = "Equipment";
String cancel = "Cancel";


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
	   
	   PC_Table editTable = new PC_Table("Edit Table", 0, "Item Quantity, Item Name, ID, Item Hazard, Item Location ","OK");
	   
	  boolean chemicalChoice;
	  boolean equipmentChoice;
	  
	  
	   PC_Dialog editItem = new PC_Dialog ("Edit Item", "Chemical*, Equipment*", "Confirm , Cancel");
	   editItem.setSize(300, 300);
	   editItem.choice();
	   
	  chemicalChoice = editItem.getFieldBoolean(1);
	  equipmentChoice = editItem.getFieldBoolean(2);
	  
	  if (chemicalChoice == true & equipmentChoice == false){
		  // output the chemical table and edit the item
		  
		  
	  }
	  else if (chemicalChoice == false & equipmentChoice == true){
		  // output the equipment table and edit the item
		  
	  }
	  else if ((chemicalChoice == true & equipmentChoice == true)|(chemicalChoice == false & equipmentChoice == false));
	  // return error as they can't both be ticked or both be not selected
 }


	   
	   
	   
	   
//  end of method


   public void requestItem() {
	   int itemRequiredQuantity;
	   boolean confirm = false;
	   
	   PC_Dialog RequestItem = new PC_Dialog ("Item Quantity", "Quantity Needed, Date for Use+, Period for Use", "Confirm , Cancel");
	   RequestItem.choice();
	   
	  itemRequiredQuantity = RequestItem.getFieldInt(1);
	  periodUse = RequestItem.getFieldInt(3);
	  
	  itemQuantity = itemQuantity - itemRequiredQuantity; // the operation used to take away the requested item from the actual item quantity
	  
	   
	   
   }//  end of method


   public void searchItem() {
	   
	
	   
	 PC_Table searchTable = new PC_Table("Search Table", 0, "Item Quantity, Item Name, ID, Item Hazard, Item Location ","OK");
	// JScrollPane searchTableScroll = new JScrollPane (searchTable);
	 searchTable.setSize(1500, 1000);
	 searchTable.add(new JScrollPane());
	 
	 
	 Chemical chemical = new Chemical (science_AutomationStyle);
	 
	 Equipment equipment = new Equipment (science_AutomationStyle);
	 // chemical and equipment hash tables.
	   
	 // JCheckBox searchBy = new JCheckBox ("Search Item By:");
	   Object[] searchOptions = {itemName,itemNumber, itemLocation}; // objects for the search table
	   Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	   
	   
	   int searchBy = JOptionPane.showOptionDialog(null, "What would you like to search by?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, searchOptions, searchOptions[0]);
	   int choice = JOptionPane.showOptionDialog(null, "Which database do you want to seach?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]); 
		
	   
	   if ((searchBy & choice) != -1){
		
			System.out.println("Search choice: " + searchBy);
			System.out.println("Table Searched: " + choice );
			
		}
	   if ( choice == -1  ) {
			JOptionPane.showMessageDialog(null, "Please select search criteria", "Error Message", 2, null);
			
		} else if (searchBy == -1 ) {
			JOptionPane.showMessageDialog(null, "Please select search criteria", "Error Message", 2, null);
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
			   searchTable.setValueAt(chemical.location, row,4);
			   searchTable.setValueAt(chemical.quantity, row, 3);
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
			   searchTable.setValueAt(chemical.location, row,4);
			   searchTable.setValueAt(chemical.quantity, row, 3);
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
			   searchTable.setValueAt(chemical.location, row,4);
			   searchTable.setValueAt(chemical.quantity, row, 3);
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
			   searchTable.setValueAt(equipment.cupboard_Number, row,4);
			   searchTable.setValueAt(equipment.quantity, row, 3);
			   searchTable.setValueAt(equipment.room, row, 5);
			   searchTable.setValueAt(equipment.hazard_Warning,row,6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
			
		}
	   
	   if ((searchBy == 1 ) & (choice == 1 )) {
			String equipmentID = JOptionPane.showInputDialog(null, "Equipment Number", "Search By Number", 1);
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
			   searchTable.setValueAt(equipment.cupboard_Number, row,4);
			   searchTable.setValueAt(equipment.quantity, row, 3);
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
			   searchTable.setValueAt(equipment.cupboard_Number, row,4);
			   searchTable.setValueAt(equipment.quantity, row, 3);
			   searchTable.setValueAt(equipment.room, row, 5);
			   searchTable.setValueAt(equipment.hazard_Warning,row,6);
			   
			   row++; // add one to row
			   }
			   }
			   searchTable.choice();//display the table
		}
	

	   
   }
//  end of method


   public void itemHazardReport() {
	/*   
	   String printreport = new String("printreport");
	  
	  
	   printhazard paper = new printhazard();
	  
	   
	   paper.setOrientation(printhazard.PORTRAIT_ORIENTATION);
	   display();
	   PC_Paper();
	   
	   */
	   
	   
	   
	   
	   
   }//  end of method
   
   public void phLevel() {
	   
   }//  end of method


   public void availableStock() {
	   
	 
	   
	   
	   
   }//  end of method





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

