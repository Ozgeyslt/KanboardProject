package common;

import com.codeborne.selenide.Selenide;
import pages.*;
import utils.PropertyManager;

public class PageFactory {

    static PropertyManager propertyManager=new PropertyManager();

    public static Login buildLogin(){
        Selenide.open(propertyManager.getProperty("APP_URL"));
        return new Login("/");
    }

}
