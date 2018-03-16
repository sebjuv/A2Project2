import java.awt.*;
import javax.swing.*;
import park.*;
import java.io.*;


// class Orders Created by 11.00 on Fri Jan 12 12:10:23 GMT 2018

 public class Orders  implements Record  {
   public static PC_Style classStyle = new PC_Style();

   Orders(){
   }

   Orders(PC_Style style){
      classStyle = style;
   }


//  *********************** Class constants ***********************

//  ************************* Class fields *************************
   private static String DATA_FILE_NAME = "datafiles/Orders.dat";// Filename for the data file for this class 

   public int            orderID = 000000000;     /** */
   public PC_Date        date = new PC_Date();     /** */
   public String         orderDate = "";     /** */
   public PC_Date        dateRequest = new PC_Date();     /** */
   private boolean        dummy_case           = false;
   private boolean        deleted_case         = false;

// Calculate the storage requirements of the record in bytes
   private final int SIZE_CASE =  120;

//  *********************** Input Method ***********************

/** This method reads data in from a keyboard using a PC_Dialog*/

   public boolean input()  { 
       boolean finished = false;
      PC_Dialog dialog = new PC_Dialog("Entering Orders Information", "Order I D, Date+, Order Date, Date Request+", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, orderID);
      dialog.setField(2, date);
      dialog.setField(3, orderDate);
      dialog.setField(4, dateRequest);
         finished = dialog.choice()==2;
         orderID             = dialog.getFieldInt(1);
         date                = dialog.getFieldDate(2);
         orderDate           = dialog.getField(3);
         dateRequest         = dialog.getFieldDate(4);
      dialog.setVisible(false); 
      deleted_case = false; 
      dummy_case = false;
      return !finished;
 }// End of input method


//  *********************** Output Method ***********************

/** This method displays and object in a PC_Dialog*/

   public int output()  { 
      PC_Dialog dialog = new PC_Dialog("Displaying Orders Information", "Order I D, Date+, Order Date, Date Request+", "OK");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setEditable(2, false);
      dialog.setEditable(3, false);
      dialog.setEditable(4, false);
      dialog.setField(1, orderID);
      dialog.setField(2, date);
      dialog.setField(3, orderDate);
      dialog.setField(4, dateRequest);
     return dialog.choice();
 }// End of output method


//  *********************** Edit Method ***********************

/** This method allows editing of the object*/

   public int edit()  { 
        int button = 0;
      PC_Dialog dialog = new PC_Dialog("Editing Orders Information", "Order I D, Date+, Order Date, Date Request+", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, orderID);
      dialog.setField(2, date);
      dialog.setField(3, orderDate);
      dialog.setField(4, dateRequest);
         button = dialog.choice();
         if (button !=2){
            orderID             = dialog.getFieldInt(1);
            date                = dialog.getFieldDate(2);
            orderDate           = dialog.getField(3);
            dateRequest         = dialog.getFieldDate(4);
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

      Orders copy = new Orders();
      copy.dummy_case = dummy_case;
      copy.deleted_case = deleted_case;

            
      copy.orderID = orderID;
            date = new PC_Date();
            date.fromInt(date.toInt());
            
      copy.orderDate = orderDate.toString();
            dateRequest = new PC_Date();
            dateRequest.fromInt(dateRequest.toInt());
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
         file.handle.writeInt(orderID);
         file.handle.writeInt(date.toInt());
         file.handle.writeUTF(orderDate);
         file.handle.writeInt(dateRequest.toInt());
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
            orderID = file.handle.readInt();
            date = new PC_Date();
         date.fromInt(file.handle.readInt());
            orderDate = file.handle.readUTF();
            dateRequest = new PC_Date();
         dateRequest.fromInt(file.handle.readInt());
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
      return " : orderID ("+orderID+")" + " : date ("+date+")" + " : orderDate ("+orderDate+")" + " : dateRequest ("+dateRequest+")" + "";
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
      <name> Orders </name>
      <input> true </input>
      <output> true </output>
      <edit> true </edit>
      <serial> false </serial>
      <random> true </random>
   </entityData>
   <fieldList>
      <field>
         <fieldName> orderID </fieldName>
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
         <fieldName> date </fieldName>
         <fieldType> PC_Date </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 7 </fieldMin>
         <fieldMax> 7 </fieldMax>
         <fieldDefault> Current date </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> orderDate </fieldName>
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
         <fieldName> dateRequest </fieldName>
         <fieldType> PC_Date </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP>  </fieldDP>
         <fieldMin> 7 </fieldMin>
         <fieldMax> 7 </fieldMax>
         <fieldDefault> Current date </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
   </fieldList>
</entity>
*/ 
