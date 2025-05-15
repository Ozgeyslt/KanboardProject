package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    static Properties property;

    public PropertyManager(){
        Properties prop=new Properties();

        try{
            prop.load(new FileReader(ClassLoader.getSystemResource("config.properties").getPath()));
            property=prop;
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public static String getProperty(String key){
        Properties prop=property;
        if (property !=null){
            return prop.getProperty(key);
        }
        return null;
    }
}
