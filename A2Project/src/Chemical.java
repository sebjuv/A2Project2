import java.awt.*;
import javax.swing.*;
import park.*;
import java.io.*;


// class Chemical Created by 11.00 on Fri Mar 16 11:59:25 GMT 2018

 public class Chemical  implements Record  {
   public static PC_Style classStyle = new PC_Style();

   Chemical(){
   }

   Chemical(PC_Style style){
      classStyle = style;
   }


//  *********************** Class constants ***********************
   private final int minReference_Code = 0;  // Minimum value for reference_Code
   private final int maxReference_Code = 20;  // Maximum value for reference_Code

//  ************************* Class fields *************************
   private static String DATA_FILE_NAME = "datafiles/Chemical.dat";// Filename for the data file for this class 

   public String         reference_Code = "";     /** Primary Key */
   public String         reactant_Name = "";     /** the full name of the reactant*/
   public int            quantity = 0;     /** How many of the given item there are*/
   public String         location = "";     /** a location for where the given item is stored*/
   public boolean        hazard_Warning = false;     /** is the item hazardous?*/
   public String         hazard_Type = "None";     /** a place to store the hazard type*/
   public float          pH_level = (float) 5.5;     /** a measure for the pH level for each chemical*/
   private boolean        dummy_case           = false;
   private boolean        deleted_case         = false;

// Calculate the storage requirements of the record in bytes
   private final int SIZE_CASE =  338;



//  ********************* Validation Methods *********************

/** This method validates data entry to reference_Code*/
   private String validReference_Code() {
      if (reference_Code.length() >= minReference_Code && reference_Code.length() <= maxReference_Code)
         return "";
      else
         return "Reference_Code"+ " is out of range";
   }



//  *********************** Input Method ***********************

/** This method reads data in from a keyboard using a PC_Dialog*/

   public boolean input()  { 
       boolean finished = false;
      PC_Dialog dialog = new PC_Dialog("Entering Chemical Information", "Reference_ Code, Reactant_ Name, Quantity, Location, Hazard_ Warning*, Hazard_ Type, P H_level", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setField(1, reference_Code);
      dialog.setField(2, reactant_Name);
      dialog.setField(3, quantity);
      dialog.setField(4, location);
      dialog.setField(5, hazard_Warning);
      dialog.setField(6, hazard_Type);
      dialog.setField(7, Vdu.format(pH_level, 2));
      String error = "";
      do{ 
         finished = dialog.choice()==2;
         reference_Code      = dialog.getField(1);
         reactant_Name       = dialog.getField(2);
         quantity            = dialog.getFieldInt(3);
         location            = dialog.getField(4);
         hazard_Warning      = dialog.getFieldBoolean(5);
         hazard_Type         = dialog.getField(6);
         pH_level            = (float) dialog.getFieldDouble(7);
         error = validReference_Code();
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
      PC_Dialog dialog = new PC_Dialog("Displaying Chemical Information", "Reference_ Code, Reactant_ Name, Quantity, Location, Hazard_ Warning*, Hazard_ Type, P H_level", "OK");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setEditable(2, false);
      dialog.setEditable(3, false);
      dialog.setEditable(4, false);
      dialog.setEditable(5, false);
      dialog.setEditable(6, false);
      dialog.setEditable(7, false);
      dialog.setField(1, reference_Code);
      dialog.setField(2, reactant_Name);
      dialog.setField(3, quantity);
      dialog.setField(4, location);
      dialog.setField(5, hazard_Warning);
      dialog.setField(6, hazard_Type);
      dialog.setField(7, Vdu.format(pH_level, 2));
     return dialog.choice();
 }// End of output method


//  *********************** Edit Method ***********************

/** This method allows editing of the object*/

   public int edit()  { 
        int button = 0;
      PC_Dialog dialog = new PC_Dialog("Editing Chemical Information", "Reference_ Code, Reactant_ Name, Quantity, Location, Hazard_ Warning*, Hazard_ Type, P H_level", "OK, Cancel");

      dialog.setStyle(classStyle);

      dialog.setEditable(1, false);
      dialog.setField(1, reference_Code);
      dialog.setField(2, reactant_Name);
      dialog.setField(3, quantity);
      dialog.setField(4, location);
      dialog.setField(5, hazard_Warning);
      dialog.setField(6, hazard_Type);
      dialog.setField(7, Vdu.format(pH_level, 2));
      String error = "";
      do{ 
         button = dialog.choice();
         if (button !=2){
            reference_Code      = dialog.getField(1);
            reactant_Name       = dialog.getField(2);
            quantity            = dialog.getFieldInt(3);
            location            = dialog.getField(4);
            hazard_Warning      = dialog.getFieldBoolean(5);
            hazard_Type         = dialog.getField(6);
            pH_level            = (float) dialog.getFieldDouble(7);
         }
         error = validReference_Code();
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

      Chemical copy = new Chemical();
      copy.dummy_case = dummy_case;
      copy.deleted_case = deleted_case;

            
      copy.reference_Code = reference_Code.toString();
            
      copy.reactant_Name = reactant_Name.toString();
            
      copy.quantity = quantity;
            
      copy.location = location.toString();
            
      copy.hazard_Warning = hazard_Warning;
            
      copy.hazard_Type = hazard_Type.toString();
            
      copy.pH_level = pH_level;
     return copy; 
  }



/** This method returns a key for the object*/

   public String getKey() {
       return ""+reference_Code; 
  }



//  *********************** Random Access Methods ***********************

/** This method writes the object to a direct access file*/

   public    void writeRandom(RandomFile file, int position_in_file)    { 
      try {
         file.seek(position_in_file, SIZE_CASE);

         file.handle.writeBoolean(dummy_case);
         file.handle.writeBoolean(deleted_case);
         file.handle.writeUTF(reference_Code);
         file.handle.writeUTF(reactant_Name);
         file.handle.writeInt(quantity);
         file.handle.writeUTF(location);
         file.handle.writeBoolean(hazard_Warning);
         file.handle.writeUTF(hazard_Type);
         file.handle.writeFloat(pH_level);
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
            reference_Code = file.handle.readUTF();
            reactant_Name = file.handle.readUTF();
            quantity = file.handle.readInt();
            location = file.handle.readUTF();
            hazard_Warning = file.handle.readBoolean();
            hazard_Type = file.handle.readUTF();
            pH_level = file.handle.readFloat();
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
      return " : reference_Code ("+reference_Code+")" + " : reactant_Name ("+reactant_Name+")" + " : quantity ("+quantity+")" + " : location ("+location+")" + " : hazard_Warning ("+hazard_Warning+")" + " : hazard_Type ("+hazard_Type+")" + " : pH_level ("+pH_level+")" + "";
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
      <name> Chemical </name>
      <input> true </input>
      <output> true </output>
      <edit> true </edit>
      <serial> false </serial>
      <random> true </random>
   </entityData>
   <fieldList>
      <field>
         <fieldName> reference_Code </fieldName>
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
         <fieldComment>  </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
      <field>
         <fieldName> reactant_Name </fieldName>
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
         <fieldValidated> false </fieldValidated>
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
         <fieldName> location </fieldName>
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
         <fieldComment> a location for where the given item is stored </fieldComment>
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
         <fieldName> pH_level </fieldName>
         <fieldType> float </fieldType>
         <fieldLength>  </fieldLength>
         <fieldIsKey> false </fieldIsKey>
         <fieldIsAutonumbered> false </fieldIsAutonumbered>
         <fieldValidated> false </fieldValidated>
         <fieldDP> 2 </fieldDP>
         <fieldMin> 0 </fieldMin>
         <fieldMax> 10000.0 </fieldMax>
         <fieldDefault> 5.5 </fieldDefault>
         <fieldList>  </fieldList>
         <fieldTimeIncrement>  </fieldTimeIncrement>
         <fieldTimeType> 0 </fieldTimeType>
         <fieldComment> a measure for the pH level for each chemical </fieldComment>
         <fieldArraySize> 0 </fieldArraySize>
      </field>
   </fieldList>
</entity>
*/ 
