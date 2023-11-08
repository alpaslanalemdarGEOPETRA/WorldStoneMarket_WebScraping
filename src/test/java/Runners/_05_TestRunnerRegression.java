package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        //birden fazla tag/grup adı verilebilir
        tags = "@SmokeTest or @Regression",
        features = {"src/test/java/FeatureFiles"},
        glue={"StepDefinitions"}
        ,plugin= {"json:target/cucumber/cucumber.json"} // Jenkins için JSON report
)
public class _05_TestRunnerRegression extends AbstractTestNGCucumberTests {
}
