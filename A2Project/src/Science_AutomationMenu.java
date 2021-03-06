// class Science_AutomationMenu - Shell created by MenuMaker on Wed Jan 17 13:35:30 GMT 2018 by 16Szuddas
/*
 * 
Add quantity check to ensure that negative numbers are not inputed for quantity in the add equipment and add chemical dialog boxes.

The splash screen is to be improved by including a photograph. 

When searching an item in the under the Item Search button, the user is prompted for the details of the item, such as a name, an ID number, or its location. If the user closes the box used to input these details, the software crashes, and a new instance has to be run. This will be fixed by adding a button which directs the user to the Item menu, where the user can go to the main menu.               

The pH button currently does not sort the chemicals by their pH level. The system would be improved if the chemicals were in order of pH, rather than mixed.

Item Hazard report will have all of the items which contain a hazard. The user will be able to print all of the details of all of the items which are hazardous.

Admin be able to edit all the files they can view.

 */

import java.awt.*;
import park.*;
import javax.swing.*;

public class Science_AutomationMenu {

   //  CLASS VARIABLES
   Science_AutomationSystem science_AutomationSystem = new Science_AutomationSystem(); //  the system object
   PC_Menu mainMenuMenu = new PC_Menu("Main Menu", "Item, Admin, Quit");
   PC_Menu itemMenu = new PC_Menu("Item", "Add Item, Edit Item, Request Item, Search Item, Main Menu");
   PC_Menu adminMenu = new PC_Menu("Admin", "Item Hazard Report, pH Level, Available Stock, Main Menu");
   PC_Menu addItemMenu = new PC_Menu ("Add Item","Chemical, Equipment,Item Menu");
   
   //  CLASS METHODS
   Science_AutomationMenu() {  //  constructor
	   
      mainMenuMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
      itemMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
      adminMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
      addItemMenu.setStyle(science_AutomationSystem.science_AutomationStyle);
    
   }

   private void mainmenuRun(){   
	   Splash startSplash = new Splash (2,"images/splash.jpg");
	   
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
      while (startSplash.getWorking());
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

