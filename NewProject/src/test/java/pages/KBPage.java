package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;

public class KBPage extends BasePage {

    public SelenideElement title=$("span.title");
    public SelenideElement projectCount=$("div[class='sidebar-content']>div:nth-child(2) div[class='table-list-header-count']");
    public ElementsCollection projectTitles=$$("div[class='table-list-row table-border-left'] span.table-list-title ");

    public KBPage(String pageUrl) {
        super(pageUrl);
    }

    public static void assertProjectNameAppearsTwice(ElementsCollection titles, String expectedProjectName) {
        long count = titles.stream()
                .filter(el -> el.getText().equals(expectedProjectName))
                .count();

        assertEquals(2, count, "Expected project name to appear exactly twice: " + expectedProjectName);
    }

}
