Feature: MongoDB demo

  Scenario: Insert and retrieve entity
    Given a MongoDB database is available
    When I insert a sample document
    Then I can retrieve it successfully