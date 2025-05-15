package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectSummary extends BasePage{
    public SelenideElement projectTitle=$("span.title");
    public SelenideElement kbButton=$("span.logo");
    public SelenideElement taskLimit=$("ul.panel>li:nth-child(6)");
    public SelenideElement summaryTitle=$("div.sidebar-content>div:nth-child(1)");
    public SelenideElement taskLimitCheckBox =$("ul.panel>li:nth-child(5)");
    public SelenideElement alertSuccessWarning =$("div[class='alert alert-success alert-fade-out']");
    public SelenideElement remove=$("div[class='sidebar'] li:nth-child(18) a");
    public SelenideElement removeProjectYes=$("button#modal-confirm-button");
    public ElementsCollection projectSummaryInformation=$$("ul.panel>li");
    public ProjectSummary(String pageUrl) {
        super(pageUrl);
    }

    public void removeProject(){
        clickWebElement(remove);
        clickWebElement(removeProjectYes);
    }
}
