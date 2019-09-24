package com.Jpmc.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.Test;


@CucumberOptions(
        features="src/test/resources/Features/verifyNews.feature",
        glue="com.Jpmc.step_definition")
public class VerifyNewsRunner {

    @Test
    public void runCukes() {

        new TestNGCucumberRunner(getClass()).runCukes();

    }
}