package common;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue={"steps", "hooks"},
        plugin = {
                "pretty",
                "summary",
                "html:build/cucumber-reports/cucumber-pretty.html",
                //"html:build/cucumber-reports/CucumberTestReport.json",
                "html:build/cucumber-reports/rerun.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
 /*   @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
  */
}
