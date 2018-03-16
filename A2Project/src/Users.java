import java.awt.*;
import javax.swing.*;
import park.*;
import java.io.*;


// class Users Created by 11.00 on Fri Jan 12 12:04:04 GMT 2018

 public class Users  implements Record  {
   public static PC_Style classStyle = new PC_Style();

   Users(){
   }

   Users(PC_Style style){
      classStyle = style;
   }


//  *********************** Class constants ***********************

//  ************************* Class fields *************************
   private static String DATA_FILE_NAME = "datafiles/Users.dat";// Filename for the data file for this class 

   public int            userID = 000000000;     /** Primary Key */
   public String         forename = "";     /** */
   public String         surname = "";     /** */
   public String         email = "";     /** */
   public String         password = "";     /** */
   public boolean        permission = false;     /** */
   public PC_Date        dateofbirth = new PC_Date();     /** */
   private boolean        dummy_case           = false;
   private boolean        deleted_case         = false;

// Calculate the storage requirements of the record in bytes
   private final int SIZE_CASE =  417;

//  *********************** Input Method ***********************

/** This method reads data in from a keyboard using a PC_Dialog*/

   public boolean input()  { 
       boolean finished = false;
      PC_Dialog dialog = new PC_Dialog("Entering Users Information", "User I D, Forename, Surname, Email, Password, Permission*, Dateofbirth+", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, userID);
      dialog.setField(2, forename);
      dialog.setField(3, surname);
      dialog.setField(4, email);
      dialog.setField(5, password);
      dialog.setField(6, permission);
      dialog.setField(7, dateofbirth);
         finished = dialog.choice()==2;
         userID              = dialog.getFieldInt(1);
         forename            = dialog.getField(2);
         surname             = dialog.getField(3);
         email               = dialog.getField(4);
         password            = dialog.getField(5);
         permission          = dialog.getFieldBoolean(6);
         dateofbirth         = dialog.getFieldDate(7);
      dialog.setVisible(false); 
      deleted_case = false; 
      dummy_case = false;
      return !finished;
 }// End of input method


//  *********************** Output Method ***********************

/** This method displays and object in a PC_Dialog*/

   public int output()  { 
      PC_Dialog dialog = new PC_Dialog("Displaying Users Information", "User I D, Forename, Surname, Email, Password, Permission*, Dateofbirth+", "OK");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setEditable(2, false);
      dialog.setEditable(3, false);
      dialog.setEditable(4, false);
      dialog.setEditable(5, false);
      dialog.setEditable(6, false);
      dialog.setEditable(7, false);
      dialog.setField(1, userID);
      dialog.setField(2, forename);
      dialog.setField(3, surname);
      dialog.setField(4, email);
      dialog.setField(5, password);
      dialog.setField(6, permission);
      dialog.setField(7, dateofbirth);
     return dialog.choice();
 }// End of output method


//  *********************** Edit Method ***********************

/** This method allows editing of the object*/

   public int edit()  { 
        int button = 0;
      PC_Dialog dialog = new PC_Dialog("Editing Users Information", "User I D, Forename, Surname, Email, Password, Permission*, Dateofbirth+", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setField(1, userID);
      dialog.setField(2, forename);
      dialog.setField(3, surname);
      dialog.setField(4, email);
      dialog.setField(5, password);
      dialog.setField(6, permission);
      dialog.setField(7, dateofbirth);
         button = dialog.choice();
         if (button !=2){
            userID              = dialog.getFieldInt(1);
            forename            = dialog.getField(2);
            surname             = dialog.getField(3);
            email               = dialog.getField(4);
            password            = dialog.getField(5);
            permission          = dialog.getFieldBoolean(6);
            dateofbirth         = dialog.getFieldDate(7);
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

      Users copy = new Users();
      copy.dummy_case = dummy_case;
      copy.deleted_case = deleted_case;

            
      copy.userID = userID;
            
      copy.forename = forename.toString();
            
      copy.surname = surname.toString();
            
      copy.email = email.toString();
            
      copy.password = password.toString();
            
      copy.permission = permission;
            dateofbirth = new PC_Date();
            dateofbirth.fromInt(dateofbirth.toInt());
     return copy; 
  }



/** This method returns a key for the object*/

   public String getKey() {
       return ""+userID; 
  }



//  *********************** Random Access Methods ***********************

/** This method writes the object to a direct access file*/

   public    void writeRandom(RandomFile file, int position_in_file)    { 
      try {
         file.seek(position_in_file, SIZE_CASE);

         file.handle.writeBoolean(dummy_case);
         file.handle.writeBoolean(deleted_case);
         file.handle.writeInt(userID);
         file.handle.writeUTF(forename);
         file.handle.writeUTF(surname);
         file.handle.writeUTF(email);
         file.handle.writeUTF(password);
         file.handle.writeBoolean(permission);
         file.handle.writeInt(dateofbirth.toInt());
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
            userID = file.handle.readInt();
            forename = file.handle.readUTF();
            surname = file.handle.readUTF();
            email = file.handle.readUTF();
            password = file.handle.readUTF();
            permission = file.handle.readBoolean();
            dateofbirth = new PC_Date();
         dateofbirth.fromInt(file.handle.readInt());
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
      return " : userID ("+userID+")" + " : forename ("+forename+")" + " : surname ("+surname+")" + " : email ("+email+")" + " : password ("+password+")" + " : permission ("+permission+")" + " : dateofbirth ("+dateofbirth+")" + "";
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
      <name> Users </name>
      <input> true </input>
      <output> true </output>
      <edit> true </edit>
      <serial> false </serial>
      <random> true </random>
   </entityData>
   <fieldList>
      <field>
         <fieldName> userID </fieldName>
         <fieldType> int </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> true </fieldIsKey>
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
         <fieldName> forename </fieldName>
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
         <fieldName> surname </fieldName>
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
         <fieldName> email </fieldName>
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
         <fieldName> password </fieldName>
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
         <fieldName> permission </fieldName>
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
         <fieldName> dateofbirth </fieldName>
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
