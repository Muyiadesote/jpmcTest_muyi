package com.Jpmc.step_definition;

import com.Jpmc.extent_listeners.ExtentManager;
import com.Jpmc.extent_listeners.ExtentTestManager;
import com.Jpmc.page_objects.VerifyNewsPage;
import com.aventstack.extentreports.Status;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


public class VerifyNewsTest extends BaseSteps {

    public VerifyNewsPage verifyNP;
    protected Scenario scenario;
    static String scenarioName;
    static int x = 0;
    @Before
    public synchronized void  before(Scenario scenario) {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        x = x + 1;
        this.scenario = scenario;
        scenarioName = scenario.getName();
        ExtentTestManager.startTest("Scenario No : " + x + " : " + scenario.getName());
        ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
        setUpFramework();
    }

    @After
    public void after(Scenario scenario) {

        if (scenario.isFailed()) {

            ExtentTestManager.logFail("Scenario Failed");
            ExtentTestManager.addScreenShotsOnFailure();
        } else {

            ExtentTestManager.scenarioPass();
        }

        ExtentManager.getReporter().flush();

        quit();

    }


    @Given("^launch browser '(.*?)'$")
    public void launchBrowser(String browser) throws Throwable {
        openBrowser(browser);
        ExtentTestManager.logInfo("Browser Opened : "+browser);
    }

    @When("^User navigate to the URL '(.*?)'$")
    public void userNavigateToTheURL(String URL)throws Throwable {
        verifyNP = new VerifyNewsPage().open(URL);
    }

    @And("^User click on the news link from the menu$")
    public void userClickOnTheNewsLinkFromTheMenu()throws Throwable {
        verifyNP.clickCookie();
        verifyNP.clickNews();
    }

    @And("^User select the headlines news$")
    public void userSelectTheHeadlinesNews() throws Throwable{
        verifyNP.clickHeadlineNews();
    }

    @Then("^User should be able to see the news page of that headline$")
    public void userShouldBeAbleToSeeTheNewsPageOfThatHeadline() throws Throwable{
//        verifyNP.closeGuardianSupport();
        System.out.println(verifyNP.getHeadline());
    }

    @And("^User should be able to copy and save to file the headline of the news$")
    public void userShouldBeAbleToCopyAndSaveToFileTheHeadlineOfTheNews() throws Throwable{
        verifyNP.saveTextToFile();
    }

    @And("^User search for the headline news from the guardian site$")
    public void userSearchForTheHeadlineNewsFromTheGuardianSite() throws Throwable{
        verifyNP.enterGoogleText();
    }

    @Then("^User should see a list of other sources with similar news details$")
    public void userShouldSeeAListOfOtherSourcesWithSimilarNewsDetails() throws Throwable{
        verifyNP.clickGoogleSearchBtn();
    }

    @And("^User am able to confirm that the news article Fake or not$")
    public void userAmAbleToConfirmThatTheNewsArticleFakeOrNot() throws Throwable{
        Assert.assertTrue(verifyNP.isResultCountAtLeast(5));
        System.out.println(verifyNP.isResultCountAtLeast(5));
    }


}
