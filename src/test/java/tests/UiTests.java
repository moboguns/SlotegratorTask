package tests;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"} ,
        glue = {"stepdef"},
        tags = {"@login"}
)
public class UiTests extends AbstractTestNGCucumberTests {
}
