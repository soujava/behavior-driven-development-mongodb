Feature: Manage hotel rooms

  Scenario: Register a new room
    Given the hotel management system is operational
    When I register a room with number 203
    Then the room with number 203 should appear in the room list

  Scenario: Register multiple rooms
    Given the hotel management system is operational
    When I register the following rooms:
      | number | type      | status             | cleanStatus |
      | 101    | STANDARD  | AVAILABLE          | CLEAN       |
      | 102    | SUITE     | RESERVED           | DIRTY       |
      | 103    | VIP_SUITE | UNDER_MAINTENANCE  | CLEAN       |
    Then there should be 3 rooms available in the system

  Scenario: Change room status
    Given the hotel management system is operational
    And a room with number 101 is registered as AVAILABLE
    When I mark the room 101 as OUT_OF_SERVICE
    Then the room 101 should be marked as OUT_OF_SERVICE
