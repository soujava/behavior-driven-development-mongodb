package org.soujava.demos.mongodb;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import jakarta.enterprise.context.ApplicationScoped;
import org.assertj.core.api.Assertions;
import org.soujava.demos.mongodb.document.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HotelRoomSteps {

    @Inject
    private RoomRepository repository;

    @Before
    public void cleanDatabase() {
        repository.deleteBy();
    }

    @Given("the hotel management system is operational")
    public void theHotelManagementSystemIsOperational() {
        Assertions.assertThat(repository).as("RoomRepository should be initialized").isNotNull();
    }

    @When("I register a room with number {int}")
    public void iRegisterARoomWithNumber(Integer number) {
        Room room = Room.builder()
                .number(number)
                .type(RoomType.STANDARD)
                .status(RoomStatus.AVAILABLE)
                .cleanStatus(CleanStatus.CLEAN)
                .build();
        repository.save(room);
    }

    @Then("the room with number {int} should appear in the room list")
    public void theRoomWithNumberShouldAppearInTheRoomList(Integer number) {
        List<Room> rooms = repository.findAll();
        Assertions.assertThat(rooms)
                .extracting(Room::getNumber)
                .contains(number);
    }

    @When("I register the following rooms:")
    public void iRegisterTheFollowingRooms(List<Room> rooms) {
        rooms.forEach(repository::save);
    }

    @Then("there should be {int} rooms available in the system")
    public void thereShouldBeRoomsAvailableInTheSystem(int expectedCount) {
        List<Room> rooms = repository.findAll();
        Assertions.assertThat(rooms).hasSize(expectedCount);
    }

    @Given("a room with number {int} is registered as {word}")
    public void aRoomWithNumberIsRegisteredAs(Integer number, String statusName) {
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
    public void iMarkTheRoomAs(Integer number, String newStatusName) {
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
    public void theRoomShouldBeMarkedAs(Integer number, String expectedStatusName) {
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