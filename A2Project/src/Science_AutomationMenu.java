// class Science_AutomationMenu - Shell created by MenuMaker on Wed Jan 17 13:35:30 GMT 2018 by 16Szuddas


import java.awt.*;
import park.*;
import javax.swing.*;

public class Science_AutomationMenu {

   //  CLASS VARIABLES
   Science_AutomationSystem science_AutomationSystem = new Science_AutomationSystem(); //  the system object
   PC_Menu mainMenuMenu = new PC_Menu("Main Menu", "Item, Admin, Quit");
   PC_Menu itemMenu = new PC_Menu("Item", "Add Item, Edit Item, Request Item, Search Item, Main Menu");
   PC_Menu adminMenu = new PC_Menu("Admin", "Item Hazard Report, pH Level, Available Stock, Main Menu");
   PC_Menu addItemMenu = new PC_Menu ("Add Item","Chemical, Equipment");
   
   //  CLASS METHODS
   Science_AutomationMenu() {  //  constructor
      mainMenuMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
      itemMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
      adminMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
      addItemMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
    
   }

   private void mainmenuRun(){   
	   
      boolean finished = false;
      do {
         switch(mainMenuMenu.choice()){
            case 1 : mainMenuMenu.setVisible(false);
                     itemRun();
                     break;
            case 2 : mainMenuMenu.setVisible(false);
                     adminRun();
                     break;
            case 3 : mainMenuMenu.setVisible(false);
                     science_AutomationSystem.quit();
                     break;
         } //  end of switch
      } while(!finished);  
   } 

   private void itemRun(){
	   
      boolean finished = false;
      do {
         switch(itemMenu.choice()){
            case 1 : itemMenu.setVisible(false);
                     science_AutomationSystem.addItem(); // Make a menu for the chemical and the equipment menus
                     addItemRun();
                     break;
            case 2 : itemMenu.setVisible(false);
                     science_AutomationSystem.editItem();
                     break;
            case 3 : itemMenu.setVisible(false);
                     science_AutomationSystem.requestItem();
                     break;
            case 4 : itemMenu.setVisible(false);
                     science_AutomationSystem.searchItem();
                     break;
            case 5 : itemMenu.setVisible(false);
                     finished=  true;
                     break;
         } //  end of switch
      } while(!finished);
   } 

   private void addItemRun(){
	   
	   boolean finished = false;
	   do{
		   switch(addItemMenu.choice()){
		   
		   case 1 : addItemMenu.setVisible(false);
		   science_AutomationSystem.addChemical();
		   break;
		   
		   case 2 : addItemMenu.setVisible(false);
		   science_AutomationSystem.addEquipment();
		   break;
		   
		   case 3 : addItemMenu.setVisible(false);
		   science_AutomationSystem.quit();
		   break;		   
		   
		   case 4 : addItemMenu.setVisible(false);
		   finished = true;
		   break;
		   
		  }// end of switch
	   } while(!finished);
   }

   private void adminRun(){
      boolean finished = false;
      do {
         switch(adminMenu.choice()){
            case 1 : adminMenu.setVisible(false);
                     science_AutomationSystem.itemHazardReport();
                     break;
            case 2 : adminMenu.setVisible(false);
                     science_AutomationSystem.phLevel();
                     break;
            case 3 : adminMenu.setVisible(false);
                     science_AutomationSystem.availableStock();
                     break;
            case 4 : adminMenu.setVisible(false);
                     finished=  true;
                     break;
         } //  end of switch
      } while(!finished);
   }

   public static void main(String args[]) {
      Science_AutomationMenu science_AutomationMenu = new Science_AutomationMenu(); //  create an instance of the menu
      science_AutomationMenu.mainmenuRun();
   }//  End of main

}//  End of class

