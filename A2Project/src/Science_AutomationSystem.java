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
String datePurchased = "Date Purchased";
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
	   // strings defining objects
	  
	   
	   
	  // JCheckBox searchBy = new JCheckBox ("Search Item By:");
	   Object[] searchOptions = {itemName,itemNumber, datePurchased}; // objects for the search table
	   Object[] determineTable = {chemicalOption, equipmentOption}; // objects for choice between chemical and equipment tables
	   
	   int searchBy = JOptionPane.showOptionDialog(null, "What would you like to search by?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, searchOptions, searchOptions[0]);
	   
	if (searchBy != -1);
	{
		int choice = JOptionPane.showOptionDialog(null, "Which database do you want to seach?", "Search Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, determineTable, determineTable[0]); 
}
	 
		// add a listener to the buttons which return dialog boxes selected. The dialog boxes must ask for the appropriate datatype. The code will then output the table and the criteria.  
	   
	   
	   
	   /*
	   PC_Dialog Search = new PC_Dialog ("Search Item","Item Number, Item Name, Date Purchased+, Chemical?*", "Search, Cancel");
	   Search.choice();
	   */
	   
	   
	   // add a choice box 
	   
	   
	   /*
	   chemicalIDnumber = Search.getFieldInt(1);
	   chemical = (Chemical) chemicalFile.retrieve(chemicalIDnumber);
	   if(chemical != null) {
		   chemical.edit();
		   chemicalFile.store(chemical);
		   
	   }
	   
	   
	   
	   Search.choice();
	   Search.setSize(100, 100);
	   Search.setCenter(true);
	   
	   */
	   
	   
	   
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

