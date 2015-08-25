package building.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "floor")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    private Long version;

    @Column(name = "floor_number")
    private int floorNumber;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;
    
    public Floor() {
    
    }

    public Floor(int floorNumber, Building building) {
        this.floorNumber = floorNumber;
        this.building = building;

        if(this.rooms == null) {
            this.rooms = new ArrayList<Room>();
        }
        
        addRoom();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    public void addRoom() {
        this.rooms.add(
            new Room(
                "",
                this
        ));
    }
    
    public void removeRoomByName(String name) {
        int size = rooms.size();
        
        if(size > 1) {
            for (int i = 0; i < size; i++) {
                if (rooms.get(i).getRoomName() == name)
                    rooms.remove(i);
            }
        }
    }
    
    public void removeRoomById(long id) {
        int size = rooms.size();
        
        if(size > 1) {
            for (int i = 0; i < size; i++) {
                if (rooms.get(i).getId() == id)
                    rooms.remove(i);
                    break;
            }
        }
    }
    
    public void removeRoomByIndex(int index) {
        rooms.remove(index);
    }
}