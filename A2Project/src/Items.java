import java.awt.*;
import javax.swing.*;
import park.*;
import java.io.*;


// class Items Created by 11.00 on Fri Jan 12 12:14:23 GMT 2018

 public class Items  implements Record  {
   public static PC_Style classStyle = new PC_Style();

   Items(){
      initialiseArrays();
   }

   Items(PC_Style style){
      classStyle = style;
      initialiseArrays();
   }


//  *********************** Class constants ***********************
   public final int ITEMQUANTITY_ARRAY_LENGTH = 10;

//  ************************* Class fields *************************
   private static String DATA_FILE_NAME = "datafiles/Items.dat";// Filename for the data file for this class 

   public int            itemID = 000000000;     /** Foreign Key */
   public String         itemName = "";     /** */
   public float          itempH = (float) 0.0;     /** */
   public boolean        itemExplosive = false;     /** */
   public boolean        itemFlammable = false;     /** */
   public boolean        itemOxidising = false;     /** */
   public boolean        itemCorrosive = false;     /** */
   public boolean        itemToxicity = false;     /** */
   public boolean        itemEnvironment = false;     /** */
   public String         itemLocation = "";     /** */
   public int[]          itemQuantity = new int[10];/** */
   public String         comments = "";     /** */
   private boolean        dummy_case           = false;
   private boolean        deleted_case         = false;

// Calculate the storage requirements of the record in bytes
   private final int SIZE_CASE =  362;


//  *********************** initialise array fields ***********************

   public void initialiseArrays()  { 
      for (int i=0; i<ITEMQUANTITY_ARRAY_LENGTH;i++)
         itemQuantity[i] = 0;
    }// End of initialiseArrays method


//  *********************** Input Method ***********************

/** This method reads data in from a keyboard using a PC_Dialog*/

   public boolean input()  { 
       boolean finished = false;
      PC_Dialog dialog = new PC_Dialog("Entering Items Information", "Item I D, Item Name, Itemp H, Item Explosive*, Item Flammable*, Item Oxidising*, Item Corrosive*, Item Toxicity*, Item Environment*, Item Location, Comments", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, itemID);
      dialog.setField(2, itemName);
      dialog.setField(3, Vdu.format(itempH, 2));
      dialog.setField(4, itemExplosive);
      dialog.setField(5, itemFlammable);
      dialog.setField(6, itemOxidising);
      dialog.setField(7, itemCorrosive);
      dialog.setField(8, itemToxicity);
      dialog.setField(9, itemEnvironment);
      dialog.setField(10, itemLocation);
      dialog.setField(11, comments);
         finished = dialog.choice()==2;
         itemID              = dialog.getFieldInt(1);
         itemName            = dialog.getField(2);
         itempH              = (float) dialog.getFieldDouble(3);
         itemExplosive       = dialog.getFieldBoolean(4);
         itemFlammable       = dialog.getFieldBoolean(5);
         itemOxidising       = dialog.getFieldBoolean(6);
         itemCorrosive       = dialog.getFieldBoolean(7);
         itemToxicity        = dialog.getFieldBoolean(8);
         itemEnvironment     = dialog.getFieldBoolean(9);
         itemLocation        = dialog.getField(10);
         comments            = dialog.getField(11);
      dialog.setVisible(false); 
      deleted_case = false; 
      dummy_case = false;
      return !finished;
 }// End of input method


//  *********************** Output Method ***********************

/** This method displays and object in a PC_Dialog*/

   public int output()  { 
      PC_Dialog dialog = new PC_Dialog("Displaying Items Information", "Item I D, Item Name, Itemp H, Item Explosive*, Item Flammable*, Item Oxidising*, Item Corrosive*, Item Toxicity*, Item Environment*, Item Location, Comments", "OK");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setEditable(2, false);
      dialog.setEditable(3, false);
      dialog.setEditable(4, false);
      dialog.setEditable(5, false);
      dialog.setEditable(6, false);
      dialog.setEditable(7, false);
      dialog.setEditable(8, false);
      dialog.setEditable(9, false);
      dialog.setEditable(10, false);
      dialog.setEditable(12, false);
      dialog.setField(1, itemID);
      dialog.setField(2, itemName);
      dialog.setField(3, Vdu.format(itempH, 2));
      dialog.setField(4, itemExplosive);
      dialog.setField(5, itemFlammable);
      dialog.setField(6, itemOxidising);
      dialog.setField(7, itemCorrosive);
      dialog.setField(8, itemToxicity);
      dialog.setField(9, itemEnvironment);
      dialog.setField(10, itemLocation);
      dialog.setField(11, comments);
     return dialog.choice();
 }// End of output method


//  *********************** Edit Method ***********************

/** This method allows editing of the object*/

   public int edit()  { 
        int button = 0;
      PC_Dialog dialog = new PC_Dialog("Editing Items Information", "Item I D, Item Name, Itemp H, Item Explosive*, Item Flammable*, Item Oxidising*, Item Corrosive*, Item Toxicity*, Item Environment*, Item Location, Comments", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, itemID);
      dialog.setField(2, itemName);
      dialog.setField(3, Vdu.format(itempH, 2));
      dialog.setField(4, itemExplosive);
      dialog.setField(5, itemFlammable);
      dialog.setField(6, itemOxidising);
      dialog.setField(7, itemCorrosive);
      dialog.setField(8, itemToxicity);
      dialog.setField(9, itemEnvironment);
      dialog.setField(10, itemLocation);
      dialog.setField(11, comments);
         button = dialog.choice();
         if (button !=2){
            itemID              = dialog.getFieldInt(1);
            itemName            = dialog.getField(2);
            itempH              = (float) dialog.getFieldDouble(3);
            itemExplosive       = dialog.getFieldBoolean(4);
            itemFlammable       = dialog.getFieldBoolean(5);
            itemOxidising       = dialog.getFieldBoolean(6);
            itemCorrosive       = dialog.getFieldBoolean(7);
            itemToxicity        = dialog.getFieldBoolean(8);
            itemEnvironment     = dialog.getFieldBoolean(9);
            itemLocation        = dialog.getField(10);
            comments            = dialog.getField(11);
         }
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

      Items copy = new Items();
      copy.dummy_case = dummy_case;
      copy.deleted_case = deleted_case;

            
      copy.itemID = itemID;
            
      copy.itemName = itemName.toString();
            
      copy.itempH = itempH;
            
      copy.itemExplosive = itemExplosive;
            
      copy.itemFlammable = itemFlammable;
            
      copy.itemOxidising = itemOxidising;
            
      copy.itemCorrosive = itemCorrosive;
            
      copy.itemToxicity = itemToxicity;
            
      copy.itemEnvironment = itemEnvironment;
            
      copy.itemLocation = itemLocation.toString();
      copy.itemQuantity = new int[ITEMQUANTITY_ARRAY_LENGTH];
     for(int i=0; i<ITEMQUANTITY_ARRAY_LENGTH;i++){
         copy.itemQuantity[i] = itemQuantity[i];
      }
            
      copy.comments = comments.toString();
     return copy; 
  }



/** This method returns a key for the object*/

   public String getKey() {
       return ""; 
  }



//  *********************** Random Access Methods ***********************

/** This method writes the object to a direct access file*/

   public    void writeRandom(RandomFile file, int position_in_file)    { 
      try {
         file.seek(position_in_file, SIZE_CASE);

         file.handle.writeBoolean(dummy_case);
         file.handle.writeBoolean(deleted_case);
         file.handle.writeInt(itemID);
         file.handle.writeUTF(itemName);
         file.handle.writeFloat(itempH);
         file.handle.writeBoolean(itemExplosive);
         file.handle.writeBoolean(itemFlammable);
         file.handle.writeBoolean(itemOxidising);
         file.handle.writeBoolean(itemCorrosive);
         file.handle.writeBoolean(itemToxicity);
         file.handle.writeBoolean(itemEnvironment);
         file.handle.writeUTF(itemLocation);
         for(int i=0; i<ITEMQUANTITY_ARRAY_LENGTH;i++)
            file.handle.writeInt(itemQuantity[i]);
         file.handle.writeUTF(comments);
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
            itemID = file.handle.readInt();
            itemName = file.handle.readUTF();
            itempH = file.handle.readFloat();
            itemExplosive = file.handle.readBoolean();
            itemFlammable = file.handle.readBoolean();
            itemOxidising = file.handle.readBoolean();
            itemCorrosive = file.handle.readBoolean();
            itemToxicity = file.handle.readBoolean();
            itemEnvironment = file.handle.readBoolean();
            itemLocation = file.handle.readUTF();
         for(int i=0; i<ITEMQUANTITY_ARRAY_LENGTH;i++){
            itemQuantity[i] = file.handle.readInt();
         }
            comments = file.handle.readUTF();
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
      return " : itemID ("+itemID+")" + " : itemName ("+itemName+")" + " : itempH ("+itempH+")" + " : itemExplosive ("+itemExplosive+")" + " : itemFlammable ("+itemFlammable+")" + " : itemOxidising ("+itemOxidising+")" + " : itemCorrosive ("+itemCorrosive+")" + " : itemToxicity ("+itemToxicity+")" + " : itemEnvironment ("+itemEnvironment+")" + " : itemLocation ("+itemLocation+")" + " : itemQuantity ("+itemQuantity[0]+ ", "+itemQuantity[1]+ ", "+itemQuantity[2] +")" + " : comments ("+comments+")" + "";
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
      <name> Items </name>
      <input> true </input>
      <output> true </output>
      <edit> true </edit>
      <serial> false </serial>
      <random> true </random>
   </entityData>
   <fieldList>
      <field>
         <fieldName> itemID </fieldName>
         <fieldType> int </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 10000 </fieldMax>
         <fieldDefault> 000000000 </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemName </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itempH </fieldName>
         <fieldType> float </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP> 2 </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 10000.0 </fieldMax>
         <fieldDefault> 0.0 </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemExplosive </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemFlammable </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemOxidising </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemCorrosive </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemToxicity </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemEnvironment </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemLocation </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> itemQuantity </fieldName>
         <fieldType> int </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 10000 </fieldMax>
         <fieldDefault> 0 </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment>  </fieldComment>
         <fieldArraySize> 10 </fieldArraySize>
      </field>
      <field>
         <fieldName> comments </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
   </fieldList>
</entity>
*/ 
