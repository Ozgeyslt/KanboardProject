package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPage extends BasePage {


    public SelenideElement overview=$("ul.views>li:nth-child(1)");
    public SelenideElement backlogHeader =$(By.xpath("//*[contains(text(), 'Backlog ')]"));
    public SelenideElement backlogAddNewTask=$("a[href=\"/board/13/task/create/swimlane/13/column/49\"]");
    public SelenideElement readyHeader =$(By.xpath("//*[contains(text(), 'Ready ')]"));
    public SelenideElement workInProgressHeader=$(By.xpath("//*[contains(text(), 'Work in progress ')]"));
    public SelenideElement workInProgressTaskCount=$("th[data-column-id='51'] span[title='Number of visible tasks in this column and swimlane']");
    public SelenideElement workInProgressArea=$("tr[class='board-swimlane board-swimlane-tasks-13']>td:nth-child(3)");
    public SelenideElement workInProgressFirstTask=$("div[data-column-id='51'][data-position='1']");
    public SelenideElement doneHeader =$(By.xpath("//*[contains(text(), 'Done ')]"));
    public SelenideElement doneFirstTask=$("div[data-column-id='52'][data-position='1']");
    public SelenideElement doneArea=$("tr[class='board-swimlane board-swimlane-tasks-13']>td:nth-child(4)");
    public SelenideElement doneTaskCount=$("th[data-column-id='52'] span[title='Number of visible tasks in this column and swimlane']");
    public SelenideElement taskTitle=$("input#form-title");
    public SelenideElement taskSaveBtn=$("button[class=\"btn btn-blue\"]");
    public SelenideElement alert=$("div[class='alert alert-success alert-fade-out']");


    public ProjectPage(String pageUrl) {
        super(pageUrl);
    }
    public void goToOverview(){
        clickWebElement(overview);
    }

    public void addTaskToBacklog(String text){
        clickWebElement(backlogAddNewTask);
        sendText(taskTitle, text);
        clickWebElement(taskSaveBtn);
    }
}
