package tipico.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/tipico/features", glue = { "tipico.stepDefinition" }, plugin = { "pretty",
		"html:target/cucumber-reports.html" }, monochrome = true)

public class TestRunner {

}
