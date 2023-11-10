Feature: Populate to WorldStoneMarket

  Scenario: Collect company information
    When Collect Company names,links, and build an excell
    Then Build a companyprofile and add products
