package org.soujava.demos.mongodb.document;

import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository {

    @Query("FROM Room")
    List<Room> findAll();

    @Save
    Room save(Room room);
    void deleteBy();

    Optional<Room> findByNumber(Integer number);
}
