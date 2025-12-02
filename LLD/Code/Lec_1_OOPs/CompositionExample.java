package Lec_1_OOPs;

import java.util.ArrayList;
import java.util.List;

// Room class
class Room {
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// House class (Composition relationship)
class House {
    private List<Room> rooms;

    public House() {
        rooms = new ArrayList<>();
        rooms.add(new Room("Living Room"));
        rooms.add(new Room("Bedroom"));
    }

    public void showRooms() {
        for (Room room : rooms) {
            System.out.println(room.getName());
        }
    }
}



public class CompositionExample {
    public static void main(String[] args) {
        // Composition is a strong "has-a" relationship, where one class owns objects of another class. If the container object is destroyed, the contained objects are destroyed as well.
        House house = new House();
        // If the house is destroyed, the rooms are also destroye
        house.showRooms();
        // Output:
        // Living Room
        // Bedroom
    }
}