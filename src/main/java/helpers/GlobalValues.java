package helpers;



import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import java.util.*;


/**
 * Created by logovskoy
 * Class is used to store config and operate temporary value
 */
public class GlobalValues extends  Tools {
    public static  String URL;
    public static  String BROWSER = "CH";
    public static  String OS = "";
    public static String USERNAME = "";
    public static String PASSWORD ="";
    public static boolean isCacheDirty = false;


    static {
        /*
         * Read Configuration.xml file
         */
        XMLConfiguration config = null;
        try {
            config = new XMLConfiguration("Configuration.xml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        try{
            if(!System.getProperty("url").equals("")){
                URL = System.getProperty("url");
            }else {URL = config.getString("url");}
        }
        catch (Exception e){
            URL = config.getString("url");
        }

        try{
            if(!System.getProperty("login").equals("")){
                USERNAME = System.getProperty("login");
            }
        }
        catch (Exception e){
            USERNAME = "";
        }

        try{
            if(!System.getProperty("password").equals("")){
                PASSWORD = System.getProperty("password");
            }
        }
        catch (Exception e){
            PASSWORD ="";
        }

    }


    /**
     * reads data from configuration file
     */

    public static HashMap<String, String> read() {
        XMLConfiguration config = null;
        HashMap<String, String> map = new HashMap<>();
        try {

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }






}
