package org.soujava.demos.mongodb.document;

import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository {

    @Query("FROM Room")
    List<Room> findAll();
    @Query("WHERE type = 'VIP_SUITE' AND status = 'AVAILABLE' AND underMaintenance = false")
    List<Room> findVipRoomsReadyForGuests();

    @Query(" WHERE type <> 'VIP_SUITE' AND status = 'AVAILABLE' AND cleanStatus = 'CLEAN'")
    List<Room> findAvailableStandardRooms();

    @Query("WHERE cleanStatus <> 'CLEAN' AND status <> 'OUT_OF_SERVICE'")
    List<Room> findRoomsNeedingCleaning();

    @Query("WHERE smokingAllowed = true AND status = 'AVAILABLE'")
    List<Room> findAvailableSmokingRooms();

    @Save
    void save(List<Room> rooms);

    @Save
    Room save(Room room);
    void deleteBy();

    @Query("WHERE type = :type")
    List<Room> findByType(@Param("type") String type);

    Optional<Room> findByNumber(Integer number);
}
