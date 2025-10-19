Feature: Manage hotel rooms

  Scenario: Register a new room
    Given the hotel management system is operational
    When I register a room with number 101
    Then the room with number 101 should appear in the room list

  Scenario: Register multiple rooms
    Given the hotel management system is operational
    When I register the following rooms:
      | number | type      | status   | cleanStatus |
      | 101    | STANDARD  | FREE     | CLEAN       |
      | 102    | SUITE     | OCCUPIED | DIRTY       |
      | 103    | VIP       | FREE     | CLEAN       |
    Then there should be 3 rooms available in the system
