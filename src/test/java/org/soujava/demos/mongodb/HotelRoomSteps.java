package org.soujava.demos.mongodb;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.soujava.demos.mongodb.document.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

public class HotelRoomSteps {

    @Inject
    private RoomRepository repository;

    @Before
    public void cleanDatabase() {
        repository.deleteBy();
    }

    @Given("the hotel management system is operational")
    public void the_hotel_management_system_is_operational() {
        Assertions.assertThat(repository).as("RoomRepository should be initialized").isNotNull();
    }

    @When("I register a room with number {int}")
    public void i_register_a_room_with_number(Integer number) {
        Room room = Room.builder()
                .number(number)
                .type(RoomType.STANDARD)
                .status(RoomStatus.AVAILABLE)
                .cleanStatus(CleanStatus.CLEAN)
                .build();
        repository.save(room);
    }

    @Then("the room with number {int} should appear in the room list")
    public void the_room_with_number_should_appear_in_the_room_list(Integer number) {
        List<Room> rooms = repository.findAll();
        Assertions.assertThat(rooms)
                .extracting(Room::getNumber)
                .contains(number);
    }

    @When("I register the following rooms:")
    public void i_register_the_following_rooms(List<Room> rooms) {
        rooms.forEach(repository::save);
    }

    @Then("there should be {int} rooms available in the system")
    public void there_should_be_rooms_available_in_the_system(int expectedCount) {
        List<Room> rooms = repository.findAll();
        Assertions.assertThat(rooms).hasSize(expectedCount);
    }

    @Given("a room with number {int} is registered as {word}")
    public void a_room_with_number_is_registered_as(Integer number, String statusName) {
        RoomStatus status = RoomStatus.valueOf(statusName);
        Room room = Room.builder()
                .number(number)
                .type(RoomType.STANDARD)
                .status(status)
                .cleanStatus(CleanStatus.CLEAN)
                .build();
        repository.save(room);
    }

    @When("I mark the room {int} as {word}")
    public void i_mark_the_room_as(Integer number, String newStatusName) {
        RoomStatus newStatus = RoomStatus.valueOf(newStatusName);
        Optional<Room> roomOpt = repository.findByNumber(number);

        Assertions.assertThat(roomOpt)
                .as("Room %s should exist", number)
                .isPresent();

        Room updatedRoom = roomOpt.orElseThrow();
        updatedRoom.update(newStatus);

        repository.save(updatedRoom);
    }

    @Then("the room {int} should be marked as {word}")
    public void the_room_should_be_marked_as(Integer number, String expectedStatusName) {
        RoomStatus expectedStatus = RoomStatus.valueOf(expectedStatusName);
        Optional<Room> roomOpt = repository.findByNumber(number);

        Assertions.assertThat(roomOpt)
                .as("Room %s should exist", number)
                .isPresent()
                .get()
                .extracting(Room::getStatus)
                .isEqualTo(expectedStatus);
    }
}