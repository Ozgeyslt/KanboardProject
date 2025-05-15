package pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BasePage {

    protected String pageUrl;

    public BasePage(String pageUrl){
        this.pageUrl=pageUrl;
    }

    public String getUrl(){
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public void clickWebElement(SelenideElement element){
        try{
            element.shouldBe(Condition.visible, Duration.ofSeconds(10))
                    .shouldBe(Condition.clickable)
                    .click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendText(SelenideElement element, Object text) {
        try {
            element.shouldBe(Condition.visible, Duration.ofSeconds(10))
                    .shouldBe(Condition.enabled)
                    .setValue(String.valueOf(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearText(SelenideElement element) {
        try {
            element.shouldBe(Condition.visible, Duration.ofSeconds(10))
                    .shouldBe(Condition.editable)
                    .clear();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void assertElementVisibleAndClickable(SelenideElement element){
        element.shouldBe(Condition.visible).shouldBe(Condition.clickable);
    }
    public void assertThatText(SelenideElement element, String text){
        assertThat(element.getText()).isEqualTo(text);
    }

    public void assertTextInCollection(ElementsCollection elements, String expectedTitle) {
        boolean found = elements.stream()
                .map(SelenideElement::getText)
                .anyMatch(text -> text.equalsIgnoreCase(expectedTitle));

        assertTrue(found, expectedTitle);
    }

    public void assertTextNotInCollection(ElementsCollection elements, String expectedTitle) {
        boolean found = elements.stream()
                .map(SelenideElement::getText)
                .anyMatch(text -> text.equalsIgnoreCase(expectedTitle));

        assertFalse(found, expectedTitle);
    }
    public void scrollToWebElement(SelenideElement element) {
        Selenide.actions().scrollToElement(element);
    }

    public void hoverOver(SelenideElement hoverTarget, SelenideElement tooltipElement){
        hoverTarget.hover();
        tooltipElement.shouldBe(Condition.visible);
    }

    public void dragAndDrop(SelenideElement element, SelenideElement element2){
        Selenide.actions()
                .clickAndHold(element)
                .moveToElement(element2)
                .release()
                .perform();
    }
    public void goBack() {
        Selenide.back();
    }

    public void goForward() {
        Selenide.forward();
    }

    public void increaseValueWithArrowKey(SelenideElement element, int times) {
        element.shouldBe(Condition.visible).click();
        for (int i = 0; i < times; i++) {
            element.sendKeys(Keys.ARROW_UP);
        }
    }

    public void decreaseValueWithArrowKey(SelenideElement element, int times) {
        element.shouldBe(Condition.visible).click();
        for (int i = 0; i < times; i++) {
            element.sendKeys(Keys.ARROW_DOWN);
        }
    }
}
