package com.esa.ponline.appmobile.bundle;

import java.util.*;

import com.esa.ponline.appmobile.exceptions.CommonResourceException;

 
/**
*  DbResourceBundle is a concrete subclass of CommonResourceBundle that
*  manages resources for a locale using a set of strings from a database.
*
*  @author  Serguei Eremenko sergeremenko@yahoo.com
*  @version 1.0
*/
 
public class DummyResourceBundle extends CommonResourceBundle {
   /**
   *  Sets the resource bundle base names as an array
   */
   public DummyResourceBundle(String[] baseName){	  
      super(baseName);
      buildProperties();
   }
   /**
   *  Sets the resource bundle base names as an array from a string like:
   *  jdbc:oracle:thin:scott/tiger@mypc:1521:ORCL:table:ScottProp, etc
 * @throws CommonResourceException 
   */
   public DummyResourceBundle(String baseName) throws CommonResourceException{
      super(baseName);
      buildProperties();
   }
   /**
   *  Sets the resource bundle base names as an array from a string stored
   *  into system properties: db.resource.bundle.name
 * @throws CommonResourceException 
   */
   public DummyResourceBundle() throws CommonResourceException{ this("db.resource.bundle.name");}
   /**
   *  @return Enumeration of the keys
   */
   @SuppressWarnings("unchecked")
   public Enumeration<String> getKeys(){
      Enumeration<?> enumeration = null;
      if (properties != null){
    	  enumeration=properties.propertyNames();
      }
	return (Enumeration<String>) enumeration;	
   }
   /**
   *  Gets an object for the given key from this resource bundle and null if
   *  this resource bundle does not contain an object for the given key
   */
   protected Object handleGetObject(String key) {
      if (properties == null) return null;
      return properties.getProperty(key);
   }
   /** Fetches resources from a database */
   private void buildProperties(){
            
	   if (properties == null) {
		   properties= new Properties();
	   } else {
		   properties = new Properties(properties);
	   }
	   properties.setProperty("login.rut","Rut usuario del Dummy");
   }
   /** Collection of resource strings */
   private Properties properties;
}