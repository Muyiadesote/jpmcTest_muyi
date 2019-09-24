Feature: As a user, I want to validate news article from guardian news website
  on similar resources, so that I can confirm that the news article is not FAKE news

 # Background:
 #   Given launch browser 'chrome'

  Scenario: Launch guardian news website
    Given launch browser 'chrome'
    When User navigate to the URL 'https://www.theguardian.com/tone/news'
    And User click on the news link from the menu
    And User select the headlines news
    Then User should be able to see the news page of that headline
    And User should be able to copy and save to file the headline of the news

  Scenario: To validate the headline news retrieved from guardian news with other resources on google
    Given launch browser 'chrome'
    When User navigate to the URL 'https://www.google.com/'
    And User search for the headline news from the guardian site
    Then User should see a list of other sources with similar news details
    And User am able to confirm that the news article Fake or not