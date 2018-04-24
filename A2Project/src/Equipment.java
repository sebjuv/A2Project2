import java.awt.*;
import javax.swing.*;
import park.*;
import java.io.*;


// class Equipment Created by 11.00 on Tue Apr 24 12:27:15 BST 2018

 public class Equipment  implements Record  {
   public static PC_Style classStyle = new PC_Style();

   Equipment(){
   }

   Equipment(PC_Style style){
      classStyle = style;
   }


//  *********************** Class constants ***********************
   private final int minProduct_Code = 0;  // Minimum value for product_Code
   private final int maxProduct_Code = 20;  // Maximum value for product_Code
   private final int minQuantity = 0;  // Minimum value for quantity
   private final int maxQuantity = 10000;  // Maximum value for quantity

//  ************************* Class fields *************************
   private static String DATA_FILE_NAME = "datafiles/Equipment.dat";// Filename for the data file for this class 

   public String         product_Code = "";     /** Primary Key code for each product*/
   public String         item_Name = "";     /** the full name of the reactant*/
   public int            quantity = 0;     /** How many of the given item there are*/
   public String         cupboard_Number = "";     /** a cupboard location for where the given item is stored*/
   public boolean        hazard_Warning = false;     /** is the item hazardous?*/
   public String         hazard_Type = "None";     /** a place to store the hazard type*/
   public String         room = "Superlab";     /** The room where the item is stored*/
   private boolean        dummy_case           = false;
   private boolean        deleted_case         = false;

// Calculate the storage requirements of the record in bytes
   private final int SIZE_CASE =  434;



//  ********************* Validation Methods *********************

/** This method validates data entry to product_Code*/
   private String validProduct_Code() {
      if (product_Code.length() >= minProduct_Code && product_Code.length() <= maxProduct_Code)
         return "";
      else
         return "Product_Code"+ " is out of range";
   }



/** This method validates data entry to quantity*/
   private String validQuantity() {
      if (quantity >= minQuantity && quantity <= maxQuantity)
         return "";
      else
         return "Quantity"+ " is out of range";
   }



//  *********************** Input Method ***********************

/** This method reads data in from a keyboard using a PC_Dialog*/

   public boolean input()  { 
       boolean finished = false;
      PC_Dialog dialog = new PC_Dialog("Entering Equipment Information", "Product_ Code, Item_ Name, Quantity, Cupboard_ Number, Hazard_ Warning*, Hazard_ Type, Room", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, product_Code);
      dialog.setField(2, item_Name);
      dialog.setField(3, quantity);
      dialog.setField(4, cupboard_Number);
      dialog.setField(5, hazard_Warning);
      dialog.setField(6, hazard_Type);
      dialog.setField(7, room);
      String error = "";
      do{ 
         finished = dialog.choice()==2;
         product_Code        = dialog.getField(1);
         item_Name           = dialog.getField(2);
         quantity            = dialog.getFieldInt(3);
         cupboard_Number     = dialog.getField(4);
         hazard_Warning      = dialog.getFieldBoolean(5);
         hazard_Type         = dialog.getField(6);
         room                = dialog.getField(7);
         error = validProduct_Code()+ validQuantity();
      if(error.length()!=0&& !finished)
         JOptionPane.showMessageDialog(null,error,"Data input error",JOptionPane.ERROR_MESSAGE);
        } while (!finished && error.length()!=0);
      dialog.setVisible(false); 
      deleted_case = false; 
      dummy_case = false;
      return !finished;
 }// End of input method


//  *********************** Output Method ***********************

/** This method displays and object in a PC_Dialog*/

   public int output()  { 
      PC_Dialog dialog = new PC_Dialog("Displaying Equipment Information", "Product_ Code, Item_ Name, Quantity, Cupboard_ Number, Hazard_ Warning*, Hazard_ Type, Room", "OK");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setEditable(2, false);
      dialog.setEditable(3, false);
      dialog.setEditable(4, false);
      dialog.setEditable(5, false);
      dialog.setEditable(6, false);
      dialog.setEditable(7, false);
      dialog.setField(1, product_Code);
      dialog.setField(2, item_Name);
      dialog.setField(3, quantity);
      dialog.setField(4, cupboard_Number);
      dialog.setField(5, hazard_Warning);
      dialog.setField(6, hazard_Type);
      dialog.setField(7, room);
     return dialog.choice();
 }// End of output method


//  *********************** Edit Method ***********************

/** This method allows editing of the object*/

   public int edit()  { 
        int button = 0;
      PC_Dialog dialog = new PC_Dialog("Editing Equipment Information", "Product_ Code, Item_ Name, Quantity, Cupboard_ Number, Hazard_ Warning*, Hazard_ Type, Room", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setField(1, product_Code);
      dialog.setField(2, item_Name);
      dialog.setField(3, quantity);
      dialog.setField(4, cupboard_Number);
      dialog.setField(5, hazard_Warning);
      dialog.setField(6, hazard_Type);
      dialog.setField(7, room);
      String error = "";
      do{ 
         button = dialog.choice();
         if (button !=2){
            product_Code        = dialog.getField(1);
            item_Name           = dialog.getField(2);
            quantity            = dialog.getFieldInt(3);
            cupboard_Number     = dialog.getField(4);
            hazard_Warning      = dialog.getFieldBoolean(5);
            hazard_Type         = dialog.getField(6);
            room                = dialog.getField(7);
         }
         error = validProduct_Code()+ validQuantity();
      if(error.length()!=0 && button!=2)
         JOptionPane.showMessageDialog(null,error,"Data input error",JOptionPane.ERROR_MESSAGE);
        } while (button!=2 && error.length()!=0);
      return button;
 }// End of edit method


//  ***************** Methods required for Record Interface ********************

/** This method allows a dummy state to be set for the object*/

   public void setDummy(boolean state) {
       dummy_case = state;
     }



/** This method allows a deleted state to be set for the object*/

   public void setDeleted(boolean state) {
       deleted_case = state;
     }



/** This method allows a deleted state to be read for the object*/

   public boolean getDeleted() {
       return deleted_case; 
     }



/** This method allows a dummy state to be read for the object*/

   public boolean getDummy() {
       return dummy_case; 
     }



/** This method allows a copy of the object to be made*/

   public Record copy() {

      Equipment copy = new Equipment();
      copy.dummy_case = dummy_case;
      copy.deleted_case = deleted_case;

            
      copy.product_Code = product_Code.toString();
            
      copy.item_Name = item_Name.toString();
            
      copy.quantity = quantity;
            
      copy.cupboard_Number = cupboard_Number.toString();
            
      copy.hazard_Warning = hazard_Warning;
            
      copy.hazard_Type = hazard_Type.toString();
            
      copy.room = room.toString();
     return copy; 
  }



/** This method returns a key for the object*/

   public String getKey() {
       return ""+product_Code; 
  }



//  *********************** Random Access Methods ***********************

/** This method writes the object to a direct access file*/

   public    void writeRandom(RandomFile file, int position_in_file)    { 
      try {
         file.seek(position_in_file, SIZE_CASE);

         file.handle.writeBoolean(dummy_case);
         file.handle.writeBoolean(deleted_case);
         file.handle.writeUTF(product_Code);
         file.handle.writeUTF(item_Name);
         file.handle.writeInt(quantity);
         file.handle.writeUTF(cupboard_Number);
         file.handle.writeBoolean(hazard_Warning);
         file.handle.writeUTF(hazard_Type);
         file.handle.writeUTF(room);
      } catch (IOException e)  {
         System.err.println(e);
         return;
      };
 }




/** This method reads the object from a direct access file*/

   public    void readRandom(RandomFile file, int position_in_file)    { 
      try {
         file.seek(position_in_file, SIZE_CASE);

         dummy_case    =file.handle.readBoolean();
         deleted_case  = file.handle.readBoolean();
            product_Code = file.handle.readUTF();
            item_Name = file.handle.readUTF();
            quantity = file.handle.readInt();
            cupboard_Number = file.handle.readUTF();
            hazard_Warning = file.handle.readBoolean();
            hazard_Type = file.handle.readUTF();
            room = file.handle.readUTF();
      } catch (IOException e)  {
        System.err.println(e);
        return;
      };
 }




//  *********************** setFileName method ***********************

/** This method sets the filename to be used by this object*/

   public void setFileName(String fileName){
      DATA_FILE_NAME =fileName;
   };




//  *********************** getFileName method ***********************

/** This method gets the filename to be used by this object*/

   public String getFileName(){
      return DATA_FILE_NAME;
   };




//  ***************** Methods to look up choice items ********************


//  *********************** setStyle Method ***********************

/** This method sets the style to be used by the GUI*/

   public void setStyle(PC_Style style)  { 
      classStyle = style;
    }// End of setStyle method


//  *********************** toString method ***********************

/** This method creates a string representation of the object*/

   public String toString(){
      return " : product_Code ("+product_Code+")" + " : item_Name ("+item_Name+")" + " : quantity ("+quantity+")" + " : cupboard_Number ("+cupboard_Number+")" + " : hazard_Warning ("+hazard_Warning+")" + " : hazard_Type ("+hazard_Type+")" + " : room ("+room+")" + "";
   }
}
//End of Class



/* 
DO NOT CHANGE THE LINES BELOW
The following is a description of the entity written in XML
It is used by the CaseTool, Version 10 and greater, to read the entity properties
<?xml version="1.0" encoding="ISO-8859-1"?>
<entity>
   <entityData>
      <name> Equipment </name>
      <input> true </input>
      <output> true </output>
      <edit> true </edit>
      <serial> false </serial>
      <random> true </random>
   </entityData>
   <fieldList>
      <field>
         <fieldName> product_Code </fieldName>
         <fieldType> String </fieldType>
         <fieldLength> 20 </fieldLength>
         <fieldIsKey> true </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> true </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 20 </fieldMax>
         <fieldDefault>  </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> code for each product </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> item_Name </fieldName>
         <fieldType> String </fieldType>
         <fieldLength> 50 </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 99 </fieldMax>
         <fieldDefault>  </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> the full name of the reactant </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> quantity </fieldName>
         <fieldType> int </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> true </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 10000 </fieldMax>
         <fieldDefault> 0 </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> How many of the given item there are </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> cupboard_Number </fieldName>
         <fieldType> String </fieldType>
         <fieldLength> 50 </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 99 </fieldMax>
         <fieldDefault>  </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> a cupboard location for where the given item is stored </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> hazard_Warning </fieldName>
         <fieldType> boolean </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin>  </fieldMin>
         <fieldMax>  </fieldMax>
         <fieldDefault> false </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> is the item hazardous? </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> hazard_Type </fieldName>
         <fieldType> String </fieldType>
         <fieldLength> 50 </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 99 </fieldMax>
         <fieldDefault> None </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> a place to store the hazard type </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> room </fieldName>
         <fieldType> String </fieldType>
         <fieldLength> 50 </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 99 </fieldMax>
         <fieldDefault> Superlab </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> The room where the item is stored </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
   </fieldList>
</entity>
*/ 
