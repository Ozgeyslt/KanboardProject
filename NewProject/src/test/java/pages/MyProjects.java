package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyProjects extends BasePage {
    public SelenideElement kBBtn=$("a[title='Dashboard']");
    public SelenideElement pageTitle =$("span.title");
    public SelenideElement overviewBtnSidebar =$("div.sidebar li:nth-child(1)>a");
    public SelenideElement myProjectsBtnSidebar =$("div.sidebar li:nth-child(2)>a");
    public SelenideElement myTasksBtnSidebar =$("div.sidebar li:nth-child(3)>a");
    public SelenideElement mySubtasksBtnSidebar =$("div.sidebar li:nth-child(4)>a");
    public SelenideElement newProject=$("div.page-header li:nth-child(1)");
    public SelenideElement newPersonalProject=$("div.page-header li:nth-child(2)");
    public SelenideElement projectManagement=$("div.page-header li:nth-child(3)");
    public SelenideElement myActivityStream=$("div.page-header li:nth-child(4)");
    public SelenideElement searchBox=$("div.select-dropdown-input-container");
    public SelenideElement myProjectsHeader=$("div.page-header a[href='/dashboard/1/projects']");
    public SelenideElement numberOfProjects =$("div.table-list div.table-list-header-count");
    public SelenideElement sortBtn= $("div.table-list-header div.dropdown");
    public SelenideElement firstProjectTitle =$("div.table-list>div:nth-child(2) span.table-list-title");
    public SelenideElement firstProjectID =$("div.table-list>div:nth-child(2) div.dropdown");
    public SelenideElement lastProjectTitle =$("div.table-list>div:last-child span.table-list-title");
    public SelenideElement lastProjectID =$("div.table-list>div:last-child div.dropdown");
    public SelenideElement firstProjectDropdownMenu=$("div[class='table-list-row table-border-left']:nth-child(2) div.dropdown");
    public SelenideElement lastProjectDropdownMenu=$("div[class='table-list-row table-border-left']:last-child div.dropdown");
    public SelenideElement lastProjectDetails=$("div[class='table-list-row table-border-left']:last-child");
    public SelenideElement projectPageHeader =$("div#modal-box div.page-header");
    public SelenideElement successWarning=$("div[class='alert alert-success alert-fade-out']");
    public SelenideElement projectPageOverview =$("ul.views>li:nth-child(1)");
    public SelenideElement analyticsPageHeader=$("div#modal-content>div.page-header>h2");
    public ElementsCollection allProjectTitles = $$("div.table-list span.table-list-title");
    public ElementsCollection projectNames = $$("span.table-list-title ");
    public ElementsCollection projectIds = $$("div[class='table-list-row table-border-left'] div.dropdown");
    public ElementsCollection projectActivityDetails=$$("div#modal-content p.activity-title");
    public SelenideElement projectCount=$("div.table-list div.table-list-header-count");
    public MyProjects(String pageUrl) {
        super(pageUrl);
    }

    public SelenideElement sortDropdownMenu(int i){
        return $("ul.dropdown-submenu-open>li:nth-child(" +i+ ")");
    }

    public SelenideElement projectDropdownMenu(int i){
        return $("ul.dropdown-submenu-open li:nth-child("+i+")");
    }
    public void goToMyProjectsPage(){
       clickWebElement(myProjectsBtnSidebar);
    }

    public void printAllNamesDynamically() {
        for (int i = 0; i < allProjectTitles.size(); i++) {
            System.out.println("Project " + (i + 1) + ": " + allProjectTitles.get(i).getText());
        }
    }

    public void printProjectDetailsDynamically() {
        for (int i = 0; i < projectActivityDetails.size(); i++) {
            System.out.println("Activity " + ": " + projectActivityDetails.get(i).getText());
        }
    }

    public void sortIfNotAscending(ElementsCollection columnElements, String type) {
        if (type.equals("string")) {
            List<String> currentOrder = new ArrayList<>();
            for (SelenideElement element : columnElements) {
                currentOrder.add(element.getText().toLowerCase());
            }

            List<String> sortedOrder = new ArrayList<>(currentOrder);
            Collections.sort(sortedOrder);

            boolean isAlreadySorted = true;
            for (int i = 0; i < currentOrder.size(); i++) {
                if (!currentOrder.get(i).equals(sortedOrder.get(i))) {
                    isAlreadySorted = false;
                    break;
                }
            }
            if (!isAlreadySorted) {
                clickWebElement(sortDropdownMenu(2));
            }

        }


        if (type.equals("integer")) {
            List<Integer> currentOrder = new ArrayList<>();
            for (SelenideElement element : columnElements) {
                String text = element.getText().replaceAll("[^\\d]", "");
                currentOrder.add(Integer.parseInt(text));
            }

            List<Integer> sortedOrder = new ArrayList<>(currentOrder);
            Collections.sort(sortedOrder);

            boolean isAlreadySorted = true;
            for (int i = 0; i < currentOrder.size(); i++) {
                if (!currentOrder.get(i).equals(sortedOrder.get(i))) {
                    isAlreadySorted = false;
                    break;
                }
            }
            if (!isAlreadySorted) {
                clickWebElement(sortDropdownMenu(1));
            }
        }

    }

    public void addProject(){
        clickWebElement(newProject);
    }
    public void sortByProjectNameAscending(){
        clickWebElement(sortBtn);
        sortIfNotAscending(projectNames, "string");
    }

    public void sortByProjectIDAscending() {
        clickWebElement(sortBtn);
        sortIfNotAscending(projectIds, "integer");
    }

    public void goToProjectPage(String text){
        if (text.equals("1.TEST")){
            clickWebElement(firstProjectTitle);
        }
        else if (text.equals("4.TEST")){
            clickWebElement(lastProjectTitle);
        }
    }

    public void goToOverview(){
        clickWebElement(projectPageOverview);
    }

    public void selectOptionFromLastProjectDropdown(int index){
        clickWebElement(lastProjectDropdownMenu);
        clickWebElement(projectDropdownMenu(index));
    }

}
