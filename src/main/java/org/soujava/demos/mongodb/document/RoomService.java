package org.soujava.demos.mongodb.document;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomService {

    private final RoomRepository repository;

    @Inject
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    /**
     * Default constructor for CDI.
     */
    RoomService() {
        this.repository = null;
    }

    public void newRoom(Room room) {
        repository.newRoom(room);
    }



}
