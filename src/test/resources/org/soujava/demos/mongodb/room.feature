Feature: Manage hotel rooms

  Scenario: Register a new room
    Given the hotel management system is operational
    When I register a room named "Blue Ocean"
    Then the room "Blue Ocean" should appear in the room list

  Scenario: Register multiple rooms
    Given the hotel management system is operational
    When I register the following rooms:
      | name         | type       | status   | cleanStatus |
      | Ocean View   | VIP        | FREE     | CLEAN       |
      | Garden View  | STANDARD   | OCCUPIED | DIRTY       |
      | City Light   | SUITE      | FREE     | CLEAN       |
    Then there should be 3 rooms available in the system
