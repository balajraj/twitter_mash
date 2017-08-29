package config



import java.io.FileInputStream
import java.util.Properties

/**
 * The file will be used for getting the config params from the file. 
 */
class ConfigFile(file:String) {

   val input = new FileInputStream(file);
   val props = new Properties
   props.load(input);

   def getConfig() = (key:String) =>  {
     
     val value = props.getProperty(key)
     value
  } 
  
}

 
