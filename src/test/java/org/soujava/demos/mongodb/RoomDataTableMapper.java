package org.soujava.demos.mongodb;

import io.cucumber.java.DataTableType;
import org.soujava.demos.mongodb.document.Room;

import java.util.Map;

public class RoomDataTableMapper {

    @DataTableType
    public Room roomEntry(Map<String, String> entry) {
        return Room.builder()
                .name(entry.get("name"))
                .type(RoomType.valueOf(entry.get("type")))
                .status(RoomStatus.valueOf(entry.get("status")))
                .cleanStatus(CleanStatus.valueOf(entry.get("cleanStatus")))
                .build();
    }
}