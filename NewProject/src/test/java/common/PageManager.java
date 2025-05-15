package common;

import driver.DriverFactory;
import pages.*;

public class PageManager {

    private KBPage kbPage;
    private Login login;
    private NewProject newProject;
    private ProjectSummary projectSummary;
    private MyProjects myProjects;
    private ProjectPage projectPage;
    private ProjectOverviewPage projectOverviewPage;

    public PageManager(){
        DriverFactory.initDriver();
    }

    public KBPage getKbPage() {
        if(kbPage==null){
            kbPage=new KBPage("/");
        }
        return kbPage;
    }

    public Login getLogin() {
        if (login==null){
            login=new Login("/");
        }
        return login;
    }

    public NewProject getNewProject() {
        if (newProject==null){
            newProject=new NewProject("/");
        }
        return newProject;
    }

    public ProjectSummary getProjectSummary() {
        if (projectSummary==null){
            projectSummary =new ProjectSummary("/");
        }
        return projectSummary;
    }

    public MyProjects getMyProjects() {
        if (myProjects==null){
            myProjects=new MyProjects("/");
        }
        return myProjects;
    }

    public ProjectPage getProjectPage(){
        if(projectPage==null){
            projectPage=new ProjectPage("/");
        }
        return projectPage;
    }

    public ProjectOverviewPage getProjectOverviewPage(){
        if (projectOverviewPage ==null){
            projectOverviewPage =new ProjectOverviewPage("/");
        }
        return projectOverviewPage;
    }


}
