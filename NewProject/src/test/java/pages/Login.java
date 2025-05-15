package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Login extends BasePage{
    public SelenideElement username=$("input#form-username");
    public SelenideElement password=$("input#form-password");
    public SelenideElement rememberMeCheckBox=$("input[name='remember_me']");
    public SelenideElement signIn=$("button[class='btn btn-blue']");
    public Login(String pageUrl) {
        super(pageUrl);
    }
    public void fillTextBoxes(String Username, String Password){
        username.setValue(Username);
        password.setValue(Password);
        clickWebElement(rememberMeCheckBox);
        signIn.click();
    }



}

