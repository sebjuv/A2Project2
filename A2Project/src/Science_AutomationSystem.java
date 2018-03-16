// class Science_AutomationSystem - Shell created by MenuMaker on Wed Jan 17 13:35:30 GMT 2018 by 16Szuddas

import java.awt.*;
import java.util.Arrays;
import park.*;
import javax.swing.*;
import java.util.Scanner;

public class Science_AutomationSystem {

   //  CLASS VARIABLES
   PC_Style science_AutomationStyle = new PC_Style();//  holds the style for the user interface
 public static int itemnumber; 
 public static boolean checkchemicals;
 
String ChemicalFile = "datafiles/Chemical.dat";
String EquipmentFile = "datafiles/Equipment.dat";

HashFile ChemicalHash = new HashFile(ChemicalFile, 100, new Chemical()); // this needs to be seen to and resolved before further progress can be made!!
HashFile EquipmentHash = new HashFile(EquipmentFile, 100, new Equipment());
 

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
  
	   
	  
	   
  
	public void addItem() {
		// TODO Auto-generated method stub
		 PC_Table EquiptmentTable = new PC_Table("Equiptment", 100, "Product Code, Description, Quantity, Cupboard, Room", "Confirm" );
		   PC_Table ChemicalsTable = new PC_Table("Chemicals", 100, "Reference Code, Reactant, Quantity, Cupboard, Warning", "Confirm" );
			
		
	}
	 
	   
   //  end of method


   public void editItem() {
	   
	   
	   }
	   
   //  end of method


   public void requestItem() {
	   PC_Dialog RequestItem = new PC_Dialog ("Item Quantity", "Date for Use, Period for Use", "Confirm , Cancel");
	   RequestItem.choice();
	   boolean Confirm = false;
	   
	   
	   
	   
	   
	   
	   
   }//  end of method


   public void searchItem() {
	   PC_Dialog Search = new PC_Dialog ("Search Item","Item Number, Item Name, Date Purchased+, Stored as Chemical?* ", "Search, Cancel");
	   Search.choice();
	   Search.setSize(100, 100);
	   Search.setCenter(true);
	   
	   
	   
	   
	   
   }//  end of method


   public void itemHazardReport() {
	   
	   String printreport = new String("printreport");
	  
	  
	   printhazard paper = new printhazard();
	  
	   /*
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

