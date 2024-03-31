package org.defuni.infrastructure;

public class Room {
    private Building building;
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Getter
    public String getRoomNumber() {
        return roomNumber;
    }
}
